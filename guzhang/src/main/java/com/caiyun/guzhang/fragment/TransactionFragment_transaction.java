package com.caiyun.guzhang.fragment;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.Fragment_transaction_listview;
import com.caiyun.guzhang.util.KeyboardUtil;
import com.caiyun.guzhang.util.UrlUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.utils.LogUtils;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Method;

public class TransactionFragment_transaction extends BaseFragment implements View.OnClickListener {
    View view;
    Context context;
    /**
     * 0表示买入，1表示卖出**
     */
    private int type = 0;
    private PullToRefreshListView mListView;
    private Button submit, reduce, add;
    private View sell1, sell2, sell3, sell4, sell5, buy1, buy2, buy3, buy4, buy5;
    private LinearLayout layout_buy, layout_sell;
    private EditText price;

    /**
     * 0表示买入，1表示卖出**
     */
    public TransactionFragment_transaction(int type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = this.getActivity();
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_transaction, container, false);
        findView();
        return view;
    }

    private void findView() {
        layout_buy = (LinearLayout) view.findViewById(R.id.layout_buy);
        layout_sell = (LinearLayout) view.findViewById(R.id.layout_sell);
        price = (EditText) view.findViewById(R.id.textView_price);
        sell1 = view.findViewById(R.id.sell1);
        sell2 = view.findViewById(R.id.sell2);
        sell3 = view.findViewById(R.id.sell3);
        sell4 = view.findViewById(R.id.sell4);
        sell5 = view.findViewById(R.id.sell5);
        buy1 = view.findViewById(R.id.buy1);
        buy2 = view.findViewById(R.id.buy2);
        buy3 = view.findViewById(R.id.buy3);
        buy4 = view.findViewById(R.id.buy4);
        buy5 = view.findViewById(R.id.buy5);
        reduce = (Button) view.findViewById(R.id.button_reduce);
        add = (Button) view.findViewById(R.id.button_add);
        reduce.setOnClickListener(this);
        add.setOnClickListener(this);
        submit = (Button) view.findViewById(R.id.button_submit);
        mListView = (PullToRefreshListView) view.findViewById(R.id.mListView);
        mListView.setAdapter(new Fragment_transaction_listview(this.getActivity(), null));
        setViewValues();
        for (int i = 0; i < layout_buy.getChildCount(); i++) {
            layout_buy.getChildAt(i).setOnClickListener(new MyLisener(i,0));
            layout_sell.getChildAt(i).setOnClickListener(new MyLisener(i,1));
        }
    }

    public void setViewValues() {
        ((TextView) (sell1.findViewById(R.id.title))).setText("卖1");
        ((TextView) (sell2.findViewById(R.id.title))).setText("卖2");
        ((TextView) (sell3.findViewById(R.id.title))).setText("卖3");
        ((TextView) (sell4.findViewById(R.id.title))).setText("卖4");
        ((TextView) (sell5.findViewById(R.id.title))).setText("卖5");
        ((TextView) (buy1.findViewById(R.id.title))).setText("买1");
        ((TextView) (buy2.findViewById(R.id.title))).setText("买2");
        ((TextView) (buy3.findViewById(R.id.title))).setText("买3");
        ((TextView) (buy4.findViewById(R.id.title))).setText("买4");
        ((TextView) (buy5.findViewById(R.id.title))).setText("买5");
        submit.setText(type == 0 ? "买入" : "卖出");
        submit.setBackgroundResource(type == 0 ? R.drawable.select_red_button : R.drawable.select_blue_button);
    }

    /**
     * 每次显示时调用的方法
     */
    public void onShow(int lastPosition) {

    }

    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {
                case R.id.button_reduce:
                    if (Float.parseFloat(price.getText().toString()) > 0.01) {
                        price.setText(UrlUtils.dFormat.format(Float.parseFloat(price.getText().toString()) - 0.01));
                    } else {
                        price.setText("0");
                    }
                    break;
                case R.id.button_add:
                    price.setText(UrlUtils.dFormat.format(Float.parseFloat(price.getText().toString()) + 0.01));
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    class MyLisener implements View.OnClickListener {
        private int position;
        /*****0为买，1为卖****/
        private int type;
        public MyLisener(int position,int type) {
            this.position = position;
            this.type=type;
        }
        @Override
        public void onClick(View v) {
            String txt;
            switch (type) {
                case 0:
                    txt=((TextView)(layout_buy.getChildAt(position).findViewById(R.id.price))).getText().toString();
                    if (txt.equals("--")) {
                        return;
                    }
                    price.setText(txt);
                    break;
                case 1:
                    txt=((TextView)(layout_sell.getChildAt(position).findViewById(R.id.price))).getText().toString();
                    if (txt.equals("--")) {
                        return;
                    }
                    price.setText(txt);
                    break;
            }
        }
    }
}
