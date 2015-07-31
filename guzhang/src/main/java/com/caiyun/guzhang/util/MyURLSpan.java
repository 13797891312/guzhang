package com.caiyun.guzhang.util;

import android.content.Context;
import android.content.Intent;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.caiyun.guzhang.StockInfoActivity;
import com.zhaojin.utils.LogUtils;

/**
     * 处理TextView中的链接点击事件
     * 链接的类型包括：url，号码，email，地图
     * 这里只拦截url，即 http:// 开头的URI
     */
    public class MyURLSpan extends ClickableSpan {
    private Context context;
        private String mUrl;                    // 当前点击的实际链接
        // 无论点击哪个都必须知道该TextView中的所有link，因此添加改变量
        public MyURLSpan(String url,Context context) {
            mUrl = url;
            this.context=context;
        }
        @Override
        public void onClick(View widget) {
            Intent intent = new Intent(context, StockInfoActivity.class);
            intent.putExtra("code", mUrl.substring(mUrl.length() - 6, mUrl.length()));
            context.startActivity(intent);
        }
    }