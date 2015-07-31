package com.caiyun.guzhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.caiyun.app.guzhang.R;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/7/9.
 */
public class MyAccountActivity extends BaseActivity implements View.OnClickListener{
    private Button btn_record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        setTitle(R.id.title, "我的账户");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
    }

    private void findView() {
        btn_record = (Button) findViewById(R.id.button_record);
        btn_record.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_record:
                intent=new Intent(this,AccountRecordActivity.class);
                this.startActivity(intent);
                break;
        }
    }
}
