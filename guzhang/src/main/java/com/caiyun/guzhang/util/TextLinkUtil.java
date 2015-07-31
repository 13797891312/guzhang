package com.caiyun.guzhang.util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.zhaojin.utils.LogUtils;

/**
 * Created by Administrator on 2015/7/16.
 */
public class TextLinkUtil {
    // 直接拷贝这些代码到你希望的位置，然后在TextView设置了文本之后调用就ok了
    public static void setLinkClickIntercept(TextView tv,Context context) {
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) tv.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            if (urls.length == 0) {
                return;
            }

            SpannableStringBuilder spannable = new SpannableStringBuilder(text.toString());
            // 只拦截 http:// URI
            //循环把链接发过去
            for (URLSpan uri : urls) {
                String uriString = uri.getURL();
                if (uriString.indexOf("http://") == 0) {
                    MyURLSpan myURLSpan = new MyURLSpan(uriString,context);
                    spannable.setSpan(myURLSpan, sp.getSpanStart(uri),
                            sp.getSpanEnd(uri), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            tv.setText(spannable);
        }
    }
}
