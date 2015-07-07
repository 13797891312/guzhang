package com.caiyun.guzhang;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.fragment.BaseFragment;
import com.caiyun.guzhang.fragment.Fragment1;
import com.caiyun.guzhang.fragment.Fragment3;
import com.caiyun.guzhang.fragment.Fragment2;
import com.caiyun.guzhang.util.Cantent;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.MyFragmentLayout;
import com.zhaojin.myviews.MyFragmentLayout.ChangeFragmentListener;

public class MainActivity extends BaseActivity {
    public static int screenWidth;
    public static int screenHeigth;
    public static float screenScale;// px dip比例
    public static RequestQueue mQueue;
    public MyFragmentLayout myFragmentLayout;
    public ArrayList<Fragment> fragBaseFragments = new ArrayList<Fragment>();
    private int tabImages[][] = {
            {R.drawable.tab1_active, R.drawable.tab1_normal},
            {R.drawable.tab2_active, R.drawable.tab2_normal},
            {R.drawable.tab3_active, R.drawable.tab3_normal}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取屏幕高/宽
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeigth = dm.heightPixels;
        screenScale = dm.scaledDensity;
        mQueue = Volley.newRequestQueue(this);
        if (savedInstanceState == null) {
        } else {
        }
        File file = new File(Cantent.CACHEPATH);
        if (!file.mkdirs()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        fragBaseFragments.add(new Fragment1());
        fragBaseFragments.add(new Fragment2());
        fragBaseFragments.add(new Fragment3());
        myFragmentLayout = (MyFragmentLayout) this.findViewById(R.id.myFragmentLayout);
        myFragmentLayout.setScorllToNext(false);
        myFragmentLayout.setScorll(false);
        myFragmentLayout.setWhereTab(0);
        myFragmentLayout
                .setOnChangeFragmentListener(new ChangeFragmentListener() {
                    @Override
                    public void change(int lastPosition, int positon,
                                       View lastTabView, View currentTabView) {
                        // TODO Auto-generated method stub
                        ((TextView) lastTabView.findViewById(R.id.tab_text))
                                .setTextColor(MainActivity.this.getResources().getColor(R.color.black_54));
                        ((ImageView) lastTabView.findViewById(R.id.tab_img))
                                .setImageResource(tabImages[lastPosition][1]);
                        ((TextView) currentTabView.findViewById(R.id.tab_text))
                                .setTextColor(MainActivity.this.getResources().getColor(R.color.red_bg));
                        ((ImageView) currentTabView.findViewById(R.id.tab_img))
                                .setImageResource(tabImages[positon][0]);
                        ((BaseFragment) fragBaseFragments.get(positon))
                                .onShow(lastPosition);
                    }
                });
        myFragmentLayout.setAdapter(fragBaseFragments, R.layout.tablayout,
                0x1000);
        myFragmentLayout.setCurrenItem(1);
        ((BaseFragment)(fragBaseFragments.get(1))).onShow(0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        // super.onSaveInstanceState(outState);
    }

    /**
     * 获取状态栏的高度
     *
     * @param activity
     * @return > 0 success; <= 0 fail
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass
                        .getField("status_bar_height").get(localObject)
                        .toString());
                statusHeight = activity.getResources()
                        .getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    boolean flag = false;

    @Override
    public void onBackPressed() {
//		if (myFragmentLayout.getCurrentPosition() != 0) {
//			myFragmentLayout.setCurrenItem(0);
//			return;
//		}
        if (!flag) {
            Toast toast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            flag = true;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    flag = false;
                }
            }, 2500);
        } else {
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mQueue = null;
    }
}
