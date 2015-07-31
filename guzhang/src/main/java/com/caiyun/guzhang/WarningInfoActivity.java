package com.caiyun.guzhang;

import android.os.Bundle;
import android.widget.CompoundButton;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.view.CheckSwitchButton;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/7/1.
 */
public class WarningInfoActivity extends BaseActivity {
    private CheckSwitchButton mCheckSwithcButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warninginfo);
        findView();
    }

    private void findView() {
        mCheckSwithcButton = (CheckSwitchButton)findViewById(R.id.mCheckSwithcButton);
        mCheckSwithcButton.setChecked(true);
        mCheckSwithcButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {

                } else {
                }
            }
        });
    }
}
