package com.caiyun.guzhang.fragment;


import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.SearchStockActivity;
import com.caiyun.guzhang.TiCaiInfoActivity;
import com.caiyun.guzhang.adapter.Fragment2_gridView_adapter;
import com.caiyun.guzhang.javabean.DapanData;
import com.caiyun.guzhang.javabean.HotData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.MyAPP;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.caiyun.guzhang.view.GridViewForScollView;
import com.google.gson.JsonObject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.LogUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment3 extends BaseFragment implements AdapterView.OnItemClickListener,View.OnClickListener{
    private Context context;
    private View view,search_view;
    private GridViewForScollView mGridView;
    private List<HotData> mList = new ArrayList<HotData>();
    private Map<String, DapanData> map = new HashMap<>();
    private Fragment2_gridView_adapter adapter;
    private PullToRefreshScrollView mScrollView;
    private CustomProgressDialog dialog;
    private TextView txt_sh, txt_cy, txt_sz, txt_sh_price, txt_cy_price, txt_sz_price;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        context = this.getActivity();
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_1, container, false);
        findView();
        return view;
    }

    private void findView() {
        search_view = view.findViewById(R.id.search_layout);
        search_view.setOnClickListener(this);
        mScrollView = (PullToRefreshScrollView) view.findViewById(R.id.mScrollView);
        txt_sh = (TextView) view.findViewById(R.id.textView_shangzheng);
        txt_cy = (TextView) view.findViewById(R.id.textView_chuangye);
        txt_sz = (TextView) view.findViewById(R.id.textView_shenzhen);
        txt_sh_price = (TextView) view.findViewById(R.id.textView_shangzheng_price);
        txt_cy_price = (TextView) view.findViewById(R.id.textView_chuangye_price);
        txt_sz_price = (TextView) view.findViewById(R.id.textView_shenzhen_price);
        mGridView = (GridViewForScollView) view.findViewById(R.id.gridview);
        mScrollView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        mScrollView.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
        mScrollView.getLoadingLayoutProxy().setReleaseLabel("松开刷新");
        mGridView.setOnItemClickListener(this);
        adapter = new Fragment2_gridView_adapter(context, mList);
        mGridView.setAdapter(adapter);
        mGridView.setFocusable(false);
        mScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                refreshView.setRefreshing(true);
                getData();
            }
        });
    }

    /**
     * 获取热门题材数据**
     */
    public void getData() {
        if (mList.isEmpty()) {
            dialog = CustomProgressDialog.startProgressDialog(dialog, context, "正在加载...");
        } else {
            dialog=null;
        }
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("Concept.getHot", new String[]{"num"}, new String[]{"100"}), null, new VolleyListerner(context, dialog) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                mList.clear();
                JSONObject dpData = response.getJSONObject("data").getJSONObject("dp");
                map.put("sh", JsonUtils.objectFromJson(dpData.getString("sh"), DapanData.class));
                map.put("cy", JsonUtils.objectFromJson(dpData.getString("cy"), DapanData.class));
                map.put("sz", JsonUtils.objectFromJson(dpData.getString("sz"), DapanData.class));
                setValues();
                JsonUtils.listFromJsonWithSubKey(response.getJSONObject("data").toString(), HotData.class, "concept", mList);
                int i=mList.size()%3;
                for (int j = 0; j <i ; j++) {
                    mList.remove(mList.size() - 1);
                }
                mGridView.setAdapter(adapter);
                mScrollView.onRefreshComplete();
            }
        }, new VolleyErrorListoner(context, dialog) {
            @Override
            public void onError(VolleyError error) {
                mScrollView.onRefreshComplete();
            }
        });

        MainActivity.mQueue.add(request);
    }

    /**
     * 设置界面值*
     */
    private void setValues() {
        txt_sh.setText(String.valueOf(map.get("sh").getPrice()));
        txt_cy.setText(String.valueOf(map.get("cy").getPrice()));
        txt_sz.setText(String.valueOf(map.get("sz").getPrice()));
        txt_sh_price.setText(map.get("sh").getZd() + "    " + map.get("sh").getRise() + "%");
        txt_cy_price.setText(map.get("cy").getZd() + "    " + map.get("cy").getRise() + "%");
        txt_sz_price.setText(map.get("sz").getZd() + "    " + map.get("sz").getRise() + "%");
        if (map.get("sh").getRise() < 0) {
            txt_sh.setTextColor(MyAPP.green);
            txt_sh_price.setTextColor(MyAPP.green);
        } else {
            txt_sh.setTextColor(MyAPP.red);
            txt_sh_price.setTextColor(MyAPP.red);
        }
        if (map.get("cy").getRise() < 0) {
            txt_cy.setTextColor(MyAPP.green);
            txt_cy_price.setTextColor(MyAPP.green);
        } else {
            txt_cy.setTextColor(MyAPP.red);
            txt_cy_price.setTextColor(MyAPP.red);
        }
        if (map.get("sz").getRise() < 0) {
            txt_sz.setTextColor(MyAPP.green);
            txt_sz_price.setTextColor(MyAPP.green);
        } else {
            txt_sz.setTextColor(MyAPP.red);
            txt_sz_price.setTextColor(MyAPP.red);
        }
    }

    @Override
    public void onShow(int lastPosition) {
        if (!isLoaded) {
            getData();
            isLoaded = true;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(context, TiCaiInfoActivity.class);
        intent.putExtra("id", mList.get(position).getId());
        intent.putExtra("name", mList.get(position).getTitle());
        this.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_layout:
                Intent intent = new Intent(context, SearchStockActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
