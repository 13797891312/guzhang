package com.caiyun.guzhang;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.javabean.NewsInfoData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.MyURLSpan;
import com.caiyun.guzhang.util.TextLinkUtil;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.LogUtils;

import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Created by Administrator on 2015/7/10. 内参正文
 */
public class NewsInfoActivity extends BaseActivity implements View.OnClickListener{
    private Button button_about;
    private String id;
    private CustomProgressDialog dialog;
    private NewsInfoData data;
    private TextView title_txt,time_txt,text_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsinfo);
        id = getIntent().getStringExtra("id");
        setTitle(R.id.title, "内参详情");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
        getNewsData();
    }

    private void findView() {
        title_txt = (TextView) findViewById(R.id.txt_title);
        time_txt = (TextView) findViewById(R.id.txt_time);
        text_txt = (TextView) findViewById(R.id.txt_text);
        button_about = (Button) findViewById(R.id.button_about);
        button_about.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.button_about) {
            intent.setClass(this, AboutStockActivity.class);
            intent.putExtra("id",id);
            this.startActivity(intent);
        }
    }

    /**
     * 获取新闻详情**
     *
     *
     */
    public void getNewsData() {
        dialog = CustomProgressDialog.startProgressDialog(dialog, this, "正在加载...");
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("Neican.showDetail", new String[]{"id"}, new String[]{id}), null, new VolleyListerner(this,dialog ) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                data = JsonUtils.objectFromJson(response.getString("data"), NewsInfoData.class);
                setValues();
                postCount();
            }
        }, new VolleyErrorListoner(this,dialog));

        MainActivity.mQueue.add(request);
    }

    public void setValues() {
        if (data == null) {
            return;
        }
        title_txt.setText(data.getTitle());
        time_txt.setText("发布时间：" + data.getNewstime() + "    [来源：" + data.getClassname() + "]");
        text_txt.setText(Html.fromHtml(data.getNewstext()));
        int end = text_txt.getText().length();
        Spannable sp = (Spannable) text_txt.getText();
        URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
        if (urls.length == 0) {
            button_about.setVisibility(View.GONE);
        } else {
            button_about.setVisibility(View.VISIBLE);
        }
        TextLinkUtil.setLinkClickIntercept(text_txt, this);
    }

    /**
     * 统计访问数
     */
    public void postCount(){
        String url = "http://www.guzhang.com/e/public/onclick/?enews=donews&classid="+data.getClassid()+"&id="+data.getId();
        LogUtils.e("url", url);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtils.e("访问成功",response+"访问成功");
            }
        }, new VolleyErrorListoner(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                return;
            }
        });
        MainActivity.mQueue.add(request);
    }

}
