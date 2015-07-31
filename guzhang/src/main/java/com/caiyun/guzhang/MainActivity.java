package com.caiyun.guzhang;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.caiyun.guzhang.fragment.Fragment2;
import com.caiyun.guzhang.fragment.Fragment3;
import com.caiyun.guzhang.util.Cantent;
import com.caiyun.guzhang.util.SaveDate;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.umeng.update.UmengUpdateAgent;
import com.zhaojin.myviews.MyFragmentLayout;
import com.zhaojin.myviews.MyFragmentLayout.ChangeFragmentListener;
import com.zhaojin.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView toggleMenu;
    private View search_layout;
    public static int screenWidth;
    public static int screenHeigth;
    public static float screenScale;// px dip比例
    public static RequestQueue mQueue;
    public MyFragmentLayout myFragmentLayout;
    public ArrayList<Fragment> fragBaseFragments = new ArrayList<Fragment>();
    public int tabImages[][] = {
            {R.drawable.tab1_active, R.drawable.tab1_normal},
            {R.drawable.tab2_active, R.drawable.tab2_normal},
            {R.drawable.tab3_active, R.drawable.tab3_normal}};
    public int menuItemId[] = {R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item6,};
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UmengUpdateAgent.update(this);
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
        if (!StringUtils.isNullOrBlanK(SaveDate.getInstence(this).getUid())) {//如果以前有登录过就自动登录
            LoginActivity.login(this, mQueue, SaveDate.getInstence(this).getUid(), SaveDate.getInstence(this).getPwd(), null);
        }
    }

    /**
     * 初始化布局
     */
    private void initView() {
        search_layout = findViewById(R.id.search_layout);
        search_layout.setOnClickListener(this);
        toggleMenu = (ImageView) findViewById(R.id.toggleMenu);
        ImageLoader.getInstance().displayImage("http://q.qlogo.cn/qqapp/1104707878/F4F9A9E2F51379AAEBB984E0E20E551F/100", toggleMenu, getOption());
        toggleMenu.setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); //打开手势滑动
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //关闭手势滑动
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//
//            }
//        });

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
                        if (positon == 0) {
                            findViewById(R.id.tab).setVisibility(View.VISIBLE);
                            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); //打开手势滑动
                        } else {
                            findViewById(R.id.tab).setVisibility(View.GONE);
                            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); //关闭手势滑动
                        }
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
        myFragmentLayout.setCurrenItem(0);
        ((BaseFragment) (fragBaseFragments.get(0))).onShow(0);
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

    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    /**
     * 打开菜单
     **/
    public void openMenu() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    /**
     * @param v 侧边菜单点击监听
     */
    public void menuClick(View v) {
        mDrawerLayout.closeDrawers();
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.item1:
                intent.setClass(this, ChangeImageActivity.class);
                break;
            case R.id.item2:
                intent.setClass(this, ChangeNickNameActivity.class);
                break;
            case R.id.item3:
                intent.setClass(this, ChangePWDActivity.class);
                break;
            case R.id.item4:
                intent.setClass(this, GetCodeActivity.class);
                intent.putExtra("type", 2);
                break;
            case R.id.item5:
                intent.setClass(this, UpdataActivity.class);
                break;
            case R.id.item6:
                intent.setClass(this, UpdataActivity.class);
                break;
            case R.id.item7:

                return;

        }
        this.startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.search_layout:
                intent = new Intent(this, SearchStockActivity.class);
                this.startActivity(intent);
                break;
        }
    }

    public DisplayImageOptions getOption() {
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
//				.showImageForEmptyUri(R.drawable.defalut_user_icon)
                // 地址为空时加载的图片
//				.showImageOnFail(R.drawable.defalut_user_icon)
                // 加载失败显示的图片
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)// 图片显示清晰度
                .cacheInMemory(true).cacheOnDisk(true).build();
        return displayImageOptions;
    }
}
