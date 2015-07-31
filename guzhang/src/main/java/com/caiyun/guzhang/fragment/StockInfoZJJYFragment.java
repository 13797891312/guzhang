package com.caiyun.guzhang.fragment;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.Fragmeng_zuijinjiaoyi_listview_adapter;
import com.caiyun.guzhang.adapter.Fragment_transaction_cancel_listview;
import com.caiyun.guzhang.view.ListViewForScollView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class StockInfoZJJYFragment extends BaseFragment{
    View view;
    Context context;
    private ListViewForScollView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=this.getActivity();
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_zuijinjiaoyi, container, false);
        findView();
        return view;
    }
    private void findView() {
        mListView=(ListViewForScollView) view.findViewById(R.id.mListView);
        View view = View.inflate(context, R.layout.layout_foot_more, null);
        view.findViewById(R.id.txt_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "加载更多", Toast.LENGTH_SHORT).show();
            }
        });
        mListView.addFooterView(view);
        mListView.setAdapter(new Fragmeng_zuijinjiaoyi_listview_adapter(this.getActivity(), null));
    }
}
