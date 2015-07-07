package com.caiyun.guzhang.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.Fragment_MyStock_Has_Listview;
import com.caiyun.guzhang.adapter.Fragment_MyStock_Record_Tree;
import com.caiyun.guzhang.view.IphoneTreeView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyStockFragment_record extends BaseFragment implements ExpandableListView.OnChildClickListener {
    private IphoneTreeView treeView;
    View view;
    Context context;
    private PullToRefreshListView mListView;
    private Fragment_MyStock_Record_Tree adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = this.getActivity();
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_mystock_record, container, false);
        findView();
        initTreeView();
        return view;
    }

    private void initTreeView() {
        treeView.setHeaderView(View.inflate(this.getActivity(),
                R.layout.header_tree_contact, null));
//		treeView.setHeaderView(View.inflate( this.getActivity(),
//				R.layout.header_tree, treeView));
        treeView.setGroupIndicator(null);
        adapter = new Fragment_MyStock_Record_Tree(this.getActivity(), treeView, new ArrayList(), new HashMap());
        treeView.setAdapter(adapter);
        adapter.expandAll();
        treeView.setOnChildClickListener(this);
        treeView.setLoadMoreListener(new IphoneTreeView.LoadMoreListener() {
            @Override
            public void LoadMore() {
                Toast.makeText(context, "加载更多", Toast.LENGTH_LONG).show();
                treeView.loadMoreFinish();
            }
        });
    }

    private void findView() {
        treeView=(IphoneTreeView) view.findViewById(R.id.iphone_tree_view);
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
