package com.caiyun.guzhang;

import android.os.Bundle;
import android.view.View;

import com.caiyun.app.guzhang.R;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/7/9.
 */
public class ChangeNickNameActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_nickname);
        setTitle(R.id.title, "修改昵称");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
    }
}
