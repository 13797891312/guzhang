package com.caiyun.guzhang;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.umeng.update.UmengUpdateAgent;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.utils.StringUtils;

/**
 * Created by Administrator on 2015/7/9.
 */
public class UpdataActivity extends BaseActivity {
    private Button updata;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        setTitle(R.id.title, "版本更新");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        updata = (Button) findViewById(R.id.button);
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UmengUpdateAgent.forceUpdate(UpdataActivity.this);
            }
        });
        tv = (TextView) findViewById(R.id.txt_version);
        tv.setText("版本号 "+ StringUtils.getCurrentVersion(this));
    }
}
