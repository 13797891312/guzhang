package com.caiyun.guzhang;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.SearchStock_adapter;
import com.caiyun.guzhang.javabean.ModelStockData;
import com.caiyun.guzhang.util.KeyboardUtil;
import com.caiyun.guzhang.util.SaveDate;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.utils.LogUtils;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2015/7/14. 搜索股票
 */
public class SearchStockActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    /**mListView为搜索历史记录，mListView1为当前搜索***/
    private ListView mListView,mListView1;
    private EditText search_edt;
    private List<ModelStockData> historyList,seachList;
    private SearchStock_adapter adapter,adapter1;
    private View historyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase db = Connector.getDatabase();
        setContentView(R.layout.activity_search);
        getHistory();
        if (!SaveDate.getInstence(this).isInsertData()) {
            insertData();
            SaveDate.getInstence(this).setIsInsertData(true);
        }
        findView();
    }

    private void findView() {
        mListView1 = (ListView) findViewById(R.id.mListView1);
        mListView1.setVisibility(View.GONE);
        historyLayout = findViewById(R.id.histoty_layout);
        search_edt = (EditText) findViewById(R.id.seach_edit);
        initSearchEdit();
        mListView = (ListView) findViewById(R.id.mListView);
        mListView1.setOnItemClickListener(this);
        mListView.setOnItemClickListener(this);
        TextView footer = (TextView) View.inflate(this, R.layout.foot_search_listview, null);
        footer.setClickable(true);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelStockData data = new ModelStockData();
                data.setToDefault("isHistory");
                data.updateAll();
                historyList.clear();
                adapter.notifyDataSetChanged();
                historyLayout.setVisibility(View.GONE);
            }
        });
        mListView.addFooterView(footer);
        mListView.setAdapter(adapter);
        if (historyList.isEmpty()) {
            historyLayout.setVisibility(View.GONE);
        } else {
            historyLayout.setVisibility(View.VISIBLE);
        }
    }

    /***初始化搜索编辑框弹出自定义键盘和监听文字变动***/
    public void initSearchEdit(){
        search_edt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int sdkInt = Build.VERSION.SDK_INT;
                if (sdkInt >= 11) {
                    Class<EditText> cls = EditText.class;
                    try {
                        Method setShowSoftInputOnFocus = cls.getMethod(
                                "setShowSoftInputOnFocus", boolean.class);
                        setShowSoftInputOnFocus.setAccessible(false);
                        setShowSoftInputOnFocus.invoke(search_edt, false);
                        setShowSoftInputOnFocus.invoke(search_edt, false);
                    } catch (Exception e) {
                    }
                } else {
                    search_edt.setInputType(InputType.TYPE_NULL);
                }
                new KeyboardUtil(SearchStockActivity.this, SearchStockActivity.this, search_edt).showKeyboard();
                return false;
            }
        });
        new KeyboardUtil(SearchStockActivity.this, SearchStockActivity.this, search_edt).showKeyboard();

        //监听编辑框文字变化
        search_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /******过去历史搜索记录*****/
    private void getHistory() {
        historyList = DataSupport.where("isHistory == ?", "1").limit(10).order("seachTime desc").find(ModelStockData.class);
        adapter = new SearchStock_adapter(this, historyList);
    }


    //从数据库获取股票数据
    private void getData(String str) {
        if (str.equals("") && seachList != null) {
            seachList.clear();
        } else {
            seachList = DataSupport.where("code like ? or singleName like ?", "%" + str + "%", "%" + str + "%").limit(10).find(ModelStockData.class);
        }
        adapter1 = new SearchStock_adapter(this, seachList);
        mListView1.setAdapter(adapter1);
        if (seachList.isEmpty()) {
            if (!str.equals("")) {
                Toast.makeText(this, "没有符合的数据", Toast.LENGTH_SHORT).show();
            }
            mListView1.setVisibility(View.GONE);
        } else {
            mListView1.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 模拟插入3000条数据
     */
    private void insertData() {
        for (int i = 0; i <3000 ; i++) {
            ModelStockData data = new ModelStockData();
            data.setCode(String.valueOf(600000 + i));
            data.setName("宁波海运");
            data.setSingleName("NBHY");
            data.setIsHistory(0);
            data.setIsZixuan(0);
            data.save();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ModelStockData data=null;
        if (parent.getId() == R.id.mListView) {
            data = historyList.get(position);
        } else if (parent.getId() == R.id.mListView1) {
            data = seachList.get(position);
        }
        data.setIsHistory(1);
        data.setSeachTime(System.currentTimeMillis());
        data.update(data.getId());
        Intent intent = new Intent(this, StockInfoActivity.class);
        intent.putExtra("code", data.getCode());
        this.startActivity(intent);
        this.finish();
    }
}
