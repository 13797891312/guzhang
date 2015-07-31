package com.caiyun.guzhang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.fragment.BaseFragment;
import com.caiyun.guzhang.fragment.RankFragment;
import com.caiyun.guzhang.fragment.RankFragment_Root;
import com.caiyun.guzhang.util.Cantent;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.MyFragmentLayout_line;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/6/29.排行榜
 */
public class RankActivity extends BaseActivity implements View.OnClickListener {
    private FragmentManager manager;
    private Button button_shouyi, button_caifu;
    /***0为收益榜，1为财富榜**/
    private int type=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        button_caifu = (Button) findViewById(R.id.button_caifu);
        button_shouyi = (Button) findViewById(R.id.button_shouyi);
        button_caifu.setOnClickListener(this);
        button_shouyi.setOnClickListener(this);
        manager = this.getSupportFragmentManager();
        manager.beginTransaction().add(R.id.root, new RankFragment_Root()).commit();
        button_shouyi.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_caifu:
                if (type==0) {
                    manager.beginTransaction().replace(R.id.root, new RankFragment_Root()).commit();
                    button_caifu.setSelected(true);
                    button_shouyi.setSelected(false);
                    type=1;
                }
                break;
            case R.id.button_shouyi:
                if (type==1) {
                    manager.beginTransaction().replace(R.id.root, new RankFragment_Root()).commit();
                    button_caifu.setSelected(false);
                    button_shouyi.setSelected(true);
                    type=0;
                }
                break;
        }
    }
}
