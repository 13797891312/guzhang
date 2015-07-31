package com.caiyun.guzhang;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.TiCaiInfoListView_adapter;
import com.caiyun.guzhang.javabean.NewsData;
import com.caiyun.guzhang.javabean.StockData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Administrator on 2015/6/30.题材详情
 */
public class TiCaiInfoActivity extends BaseActivity implements View.OnClickListener ,AdapterView.OnItemClickListener{
    private PullToRefreshListView mListView;
    private ArrayList<StockData> mList = new ArrayList();
    private TiCaiInfoListView_adapter adapter;
    private RequestQueue mQueue;
    private String name,id;
    private CustomProgressDialog dialog;
    private View qudong_lin;
    private String qudong_text;
    private String qudong_info;
    private TextView qudong_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticaiinfo);
        name = getIntent().getStringExtra("name");
        setTitle(R.id.title,name);
        mQueue = Volley.newRequestQueue(this);
        id = getIntent().getStringExtra("id");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
        getData();
    }

    private void findView() {
        qudong_txt = (TextView) findViewById(R.id.qudong_txt);
        qudong_lin = findViewById(R.id.lin);
        qudong_lin.setOnClickListener(this);
        mListView = (PullToRefreshListView) findViewById(R.id.mListView);
        mListView.setOnItemClickListener(this);
        adapter = new TiCaiInfoListView_adapter(this, mList);
        mListView.setAdapter(adapter);
        mListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新");
        mListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        mListView.getLoadingLayoutProxy(true, false).setRefreshingLabel(
                "正在刷新");
        mListView.getLoadingLayoutProxy(false, true).setRefreshingLabel(
                "正在加载");
        mListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开刷新");
        mListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载");
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshView.setRefreshing(true);
                getData();
            }
        });
    }

    /**刷新按钮监听**/
    public void reFreshClick(View v){

    }

    /**
     * 获取股票列表**
     *
     *
     */
    public void getData() {
        if (mList.isEmpty()) {
            dialog = CustomProgressDialog.startProgressDialog(dialog, this, "正在加载...");
        }else{
            dialog=null;
        }
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("Concept.showDetail", new String[]{"id"}, new String[]{id}), null, new VolleyListerner(this,dialog) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                mList.clear();
                JsonUtils.listFromJsonWithSubKey(response.getString("data"), StockData.class, "stocks", mList);
                							Collections.sort(mList, new Comparator<StockData>() {
                                                @Override
                                                public int compare(StockData lhs, StockData rhs) {
                                                    // TODO Auto-generated method stub
                                                    return (rhs.getIslongtou())-lhs.getIslongtou();
                                                }
                                            });
                qudong_text=response.getJSONObject("data").getString("drive");
                qudong_info=response.getJSONObject("data").getString("info");
                adapter.notifyDataSetChanged();
                mListView.onRefreshComplete();
                setValues();
            }
        }, new VolleyErrorListoner(this,dialog) {
            @Override
            public void onError(VolleyError error) {
                mListView.onRefreshComplete();
            }
        });
        mQueue.add(request);
    }

    private void setValues() {
        qudong_lin.setVisibility(View.VISIBLE);
        qudong_txt.setText(qudong_text);
    }

    @Override
    public void onClick(View v) {
        final Dialog dialog = new Dialog(this, R.style.MyDialogStyle);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        View view = View.inflate(this, R.layout.pop_qudong_dialog, null);
        TextView tv = (TextView) view.findViewById(R.id.text);
        TextView closed = (TextView) view.findViewById(R.id.closed);
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv.setText(qudong_info);
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position -= 1;
        Intent intent = new Intent(this, StockInfoActivity.class);
        intent.putExtra("code", mList.get(position).getCode());
        this.startActivity(intent);
    }
}
