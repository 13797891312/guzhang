package com.caiyun.guzhang;

import android.app.Dialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.futures.entity.TimesEntity;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.StockInfoTreeViewAdapter;
import com.caiyun.guzhang.fragment.BaseFragment;
import com.caiyun.guzhang.fragment.BigKChartsFragment;
import com.caiyun.guzhang.fragment.BigTimeLineFragment;
import com.caiyun.guzhang.fragment.SmallKChartsFragment;
import com.caiyun.guzhang.fragment.SmallTimeLineFragment;
import com.caiyun.guzhang.fragment.StockInfoNewsFragment;
import com.caiyun.guzhang.fragment.StockInfoZJJYFragment;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.caiyun.guzhang.view.IphoneTreeView;
import com.caiyun.guzhang.view.IphoneTreeView_stockinfo;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockInfoActivity extends BaseActivity implements View.OnClickListener {
    private IphoneTreeView_stockinfo treeView;
    private StockInfoTreeViewAdapter adapter;
    private View info_layout;
    private String stockCode="002353";
    public static  RequestQueue mQueue;
    private CustomProgressDialog dialog;
    private List<TimesEntity> timesList = new ArrayList<TimesEntity>();
    private ArrayList<BaseFragment> fragments1=new ArrayList<>();
    private ArrayList<BaseFragment> fragments2=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_info);
        mQueue = Volley.newRequestQueue(this);
        stockCode = getIntent().getStringExtra("code");
        findView();
        initTreeView();
        getData();
    }

    private void findView() {
        info_layout = findViewById(R.id.info_layout);
        info_layout.setOnClickListener(this);
        treeView = (IphoneTreeView_stockinfo) findViewById(R.id.iphone_tree_view);
    }

    private void initTreeView() {
        fragments1.add(new SmallTimeLineFragment());
        fragments1.add(new SmallKChartsFragment());
        fragments1.add(new SmallKChartsFragment());
        fragments1.add(new SmallKChartsFragment());
        fragments1.add(new SmallKChartsFragment());
        fragments2.add(new StockInfoZJJYFragment());
        fragments2.add(new StockInfoNewsFragment());
        fragments2.add(new StockInfoNewsFragment());
        fragments2.add(new StockInfoNewsFragment());
        fragments2.add(new StockInfoNewsFragment());
        treeView.setHeaderView(findViewById(R.id.header));
        treeView.setGroupIndicator(null);
        adapter = new StockInfoTreeViewAdapter(this, treeView, fragments1, fragments2);
        treeView.setAdapter(adapter);
        adapter.expandAll();
    }

    /**
     * 刷新*
     */
    public void reFreshClick(View v) {
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_layout:
                showDialog();
                break;
        }
    }

    /***弹出股票详细数据***/
    public void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.MyDialogStyle);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        View view = View.inflate(this, R.layout.pop_stockinfo_dialog, null);
        TextView tv = (TextView) view.findViewById(R.id.text);
        TextView closed = (TextView) view.findViewById(R.id.closed);
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    /***获取股票数据***/
    public void getData(){
        String url = "http://192.168.1.201/minute.ashx?code="+stockCode;
        if (timesList.isEmpty()) {
            dialog = CustomProgressDialog.startProgressDialog(dialog, this, "正在加载...");
        } else {
            dialog=null;
        }
        JsonObjectRequest request = new JsonObjectRequest(url, null, new VolleyListerner(this, dialog) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                timesList.clear();
                JSONArray array = response.getJSONObject("data").getJSONArray("fs");
                double zs = response.getJSONObject("data").getDouble("zs");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    if (i==0) {
                        timesList.add(new TimesEntity(object.getString("time"),zs, zs,0,0,0));
                    }
                    timesList.add(new TimesEntity(object.getString("time"), object
                            .getDouble("price"), object.getDouble("price"), object
                            .getInt("volume"), object.getInt("volume"), object.getInt("volume")));
                }
                ((SmallTimeLineFragment)(fragments1.get(0))).refrush();
            }
        }, new VolleyErrorListoner(this, dialog));
        mQueue.add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQueue=null;
    }

    public List<TimesEntity> getTimesList() {
        return timesList;
    }

    public void setTimesList(List<TimesEntity> timesList) {
        this.timesList = timesList;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}
