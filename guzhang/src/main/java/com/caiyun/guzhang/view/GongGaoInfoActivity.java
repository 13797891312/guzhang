package com.caiyun.guzhang.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.AboutStockActivity;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.javabean.NewsInfoData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.TextLinkUtil;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.LogUtils;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/10. 公告正文
 */
public class GongGaoInfoActivity extends BaseActivity implements View.OnClickListener{
    private String id;
    private CustomProgressDialog dialog;
    private NewsInfoData data;
    private TextView title_txt,time_txt,text_txt;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gonggaoinfo);
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        setTitle(R.id.title, title);
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
        getNewsData();
    }

    private void findView() {
        title_txt = (TextView) findViewById(R.id.txt_title);
        time_txt = (TextView) findViewById(R.id.txt_time);
        text_txt = (TextView) findViewById(R.id.txt_text);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.button_about) {
            intent.setClass(this, AboutStockActivity.class);
            intent.putExtra("id",id);
            this.startActivity(intent);
            this.finish();
        }
    }

    /**
     * 获取新闻详情**
     *
     *
     */
    public void getNewsData() {
        dialog = CustomProgressDialog.startProgressDialog(dialog, this, "正在加载...");
        String function=title.equals("公告")?"Notice.showDetail":"Report.showDetail";
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl(function, new String[]{"id"}, new String[]{id}), null, new VolleyListerner(this,dialog ) {
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
        time_txt.setText("发布时间：" + data.getNewstime() + "");
        text_txt.setText(data.getNewstext());
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
