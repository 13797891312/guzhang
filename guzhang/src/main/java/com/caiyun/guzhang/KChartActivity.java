package com.caiyun.guzhang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.fragment.BaseFragment;
import com.caiyun.guzhang.fragment.BigKChartsFragment;
import com.caiyun.guzhang.fragment.BigTimeLineFragment;
import com.caiyun.guzhang.fragment.SmallKChartsFragment;
import com.caiyun.guzhang.fragment.SmallTimeLineFragment;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.utils.LogUtils;

/**
 * Created by Administrator on 2015/7/21. 横屏K线
 */
public class KChartActivity extends BaseActivity {
    LinearLayout tabView1;
    private FragmentManager manager;
    private String code;
    /**
     * *当前选中项***
     */
    private int frameposition = 0;
    private BaseFragment fragments[] = new BaseFragment[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kchart);
        code = getIntent().getStringExtra("code");
        frameposition = getIntent().getIntExtra("position", 0);
        manager = this.getSupportFragmentManager();
        findView();
        initFragment();
    }

    private void findView() {
        tabView1 = (LinearLayout) findViewById(R.id.tabView1);
        for (int i = 0; i < ((LinearLayout) tabView1).getChildCount(); i++) {
            ((LinearLayout) tabView1).getChildAt(i)
                    .setOnClickListener(new Mylistener(i));
        }
    }

    public class Mylistener implements View.OnClickListener {
        int position;

        public Mylistener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (frameposition == position) {
                return;
            }
            setCurrentFrame(position);
            frameposition = position;
        }
    }


    public void setCurrentFrame(int position) {
        for (int i = 0; i < tabView1.getChildCount(); i++) {
            if (i == position) {
                ((LinearLayout) (tabView1.getChildAt(i))).getChildAt(0).setEnabled(true);
                    manager.beginTransaction()
                            .replace(R.id.frame,fragments[i]).commit();
            } else {
                ((LinearLayout) (tabView1.getChildAt(i))).getChildAt(0).setEnabled(false);
            }
        }
    }

    /**
     * 初始化fragment容器
     */
    private void initFragment() {
        fragments[0] = new BigTimeLineFragment();
        fragments[1] = new BigKChartsFragment();
        fragments[2] = new BigKChartsFragment();
        fragments[3] = new BigKChartsFragment();
        fragments[4] = new BigKChartsFragment();
       setCurrentFrame(frameposition);
        LogUtils.e("**",frameposition+"");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
