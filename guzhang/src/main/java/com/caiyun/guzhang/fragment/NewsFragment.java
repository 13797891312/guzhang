package com.caiyun.guzhang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.Fragment_MyStock_Record_Tree;
import com.caiyun.guzhang.adapter.Fragment_news_adapter;
import com.caiyun.guzhang.adapter.TrendsActivity_adapter;
import com.caiyun.guzhang.view.IphoneTreeView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

/*****资询****/
public class NewsFragment extends BaseFragment implements ExpandableListView.OnChildClickListener {
    private IphoneTreeView treeView;
    View view;
    private PullToRefreshListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_news, container, false);
        findView();
        return view;
    }

    private void findView() {
        mListView = (PullToRefreshListView) view.findViewById(R.id.mListView);
        mListView.setAdapter(new Fragment_news_adapter(context,null));
    }

    /**
     * 每次显示时调用的方法
     */
    public void onShow(int lastPosition) {

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }
}
