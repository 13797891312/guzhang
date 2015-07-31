package com.caiyun.guzhang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.NewsInfoActivity;
import com.caiyun.guzhang.adapter.Fragment_gonggao_adapter;
import com.caiyun.guzhang.javabean.NewsData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.caiyun.guzhang.view.ListViewForScollView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * **资询***
 */
public class PeopleInfoFragment2 extends BaseFragment implements AdapterView.OnItemClickListener{
    View view;
    private ListViewForScollView mListView;
    private int page = 0;
    private ArrayList<NewsData> mList = new ArrayList();
    private Fragment_gonggao_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_stockinfo_news, container, false);
        findView();
        getData(0);
        return view;
    }

    private void findView() {
        mListView = (ListViewForScollView) view.findViewById(R.id.mListView);
        View view = View.inflate(context, R.layout.layout_foot_more, null);
        view.findViewById(R.id.txt_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "加载更多", Toast.LENGTH_SHORT).show();
            }
        });
        mListView.addFooterView(view);
        mListView.setOnItemClickListener(this);
        adapter = new Fragment_gonggao_adapter(context, mList);
        mListView.setAdapter(adapter);
    }

    /**
     * 获取热门题材数据**
     *
     * @param type 0为下拉刷新，1为上拉加载
     */
    public void getData(final int type) {
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("Neican.getList", new String[]{"page", "pagesize"}, new String[]{String.valueOf(page), "10"}), null, new VolleyListerner(context) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                if (type == 0) {
                    mList.clear();
                }
                JsonUtils.listFromJsonWithSubKey(response.getString("data"), NewsData.class, "neicanlist", mList);
                adapter.notifyDataSetChanged();
            }
        }, new VolleyErrorListoner(context){
            @Override
            public void onError(VolleyError error) {
            }
        });

        MainActivity.mQueue.add(request);
    }

    /**
     * 每次显示时调用的方法
     */
    public void onShow(int lastPosition) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, NewsInfoActivity.class);
        intent.putExtra("id", mList.get(position).getId());
        this.startActivity(intent);
    }
}
