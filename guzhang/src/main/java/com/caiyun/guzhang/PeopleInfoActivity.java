package com.caiyun.guzhang;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.fragment.PeopleInfoFragment1;
import com.caiyun.guzhang.util.ImageUtils;
import com.caiyun.guzhang.util.ReflectionUtils;
import com.caiyun.guzhang.view.CircleImageView;
import com.caiyun.guzhang.view.ObservableScrollView;
import com.nineoldandroids.animation.ValueAnimator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.zhaojin.activity.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/14.  个人信息
 */
public class PeopleInfoActivity extends BaseActivity implements View.OnClickListener{
    private ImageView header_bg,back_image;
    private CircleImageView header_iv,toggleMenu;
    public ArrayList<Fragment> fragBaseFragments = new ArrayList<Fragment>();
    private TextView dangqian_txt,lishi_txt;//当前持仓，历史持仓按钮
    private FragmentManager manager;
    private int position=0;
    private ObservableScrollView mScrollView;
    private RelativeLayout header_layout;
    private View back_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = this.getSupportFragmentManager();
        setContentView(R.layout.activity_peopleinfo);
        //		iv_bg.setImageBitmap(ImageUtils.blurBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher), context));
        findView();

    }

    private void findView() {
        back_image = (ImageView) findViewById(R.id.back_image);
        back_layout = findViewById(R.id.back_layout);
        back_layout.setAlpha(0);
        header_layout = (RelativeLayout) findViewById(R.id.header_layout);
        mScrollView = (ObservableScrollView) findViewById(R.id.mScrollView);
        dangqian_txt = (TextView) findViewById(R.id.dangqian_txt);
        lishi_txt = (TextView) findViewById(R.id.lishi_txt);
        findViewById(R.id.dangqian_layout).setOnClickListener(this);
        findViewById(R.id.lishi_layout).setOnClickListener(this);
        header_bg = (ImageView) findViewById(R.id.iv_blu);
        header_iv = (CircleImageView) findViewById(R.id.iv_head);
        toggleMenu = (CircleImageView) findViewById(R.id.toggleMenu);
        ImageLoader.getInstance().displayImage("http://q.qlogo.cn/qqapp/1104707878/F4F9A9E2F51379AAEBB984E0E20E551F/100", header_iv, getOption(), new SimpleImageLoadingListener() {
            //        ImageLoader.getInstance().displayImage("http://q.qlogo.cn/qqapp/1104707878/3FA006FB45AA15F4FBCAA0ECBA39BB56/100", header_iv, getOption(),new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                header_bg.setImageBitmap(ImageUtils.blurBitmap(loadedImage.copy(Bitmap.Config.ARGB_8888, true), PeopleInfoActivity.this));
            }
        });
        fragBaseFragments.add(new PeopleInfoFragment1());
        fragBaseFragments.add(new PeopleInfoFragment1());
        manager.beginTransaction().add(R.id.frame, fragBaseFragments.get(0), "dangqian").commit();
        initScrollView();
    }


    float startX=-1;
    float endX = -1;
    float startY=-1;
    float endY=-1;
    OverScroller mScroller = null;
    Handler hd = new Handler();
    boolean isRun=false;
    private void initScrollView() {
        mScrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (mScroller == null) {
                    mScroller = (OverScroller) ReflectionUtils.getFieldValue(mScrollView, "mScroller");
                }
                if (startX == -1) {
                    startX = header_iv.getX();
                    endX = toggleMenu.getX();
                    startY = header_iv.getY();
                    endY = toggleMenu.getY();
                }
                ViewGroup.LayoutParams params = header_layout.getLayoutParams();
                float scoll = oldy / (float) (params.height - back_layout.getLayoutParams().height);
                if (scoll > 1) {
                    scoll = 1;
                }
                if (scoll <= 0.1) {
                    back_image.setVisibility(View.VISIBLE);
                    scoll = 0;
                } else {
                    back_image.setVisibility(View.INVISIBLE);
                }
                header_bg.setAlpha(1 - scoll);
                back_layout.setAlpha(scoll);
                header_iv.setX(startX - (startX - endX + 28 * MainActivity.screenScale / 2) * scoll);
                header_iv.setY(startY - (startY - endY + 28 * MainActivity.screenScale / 2) * scoll);
                float f = 1 - scoll;
                if (f < 0.6f) {
                    f = 0.6f;
                }
                header_iv.setScaleX(f);
                header_iv.setScaleY(f);
                if ((!mScroller.isFinished() && !isRun)) {
                    isRun = true;
                    hd.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!mScroller.isFinished()) {
                                hd.postDelayed(this, 200);
                            } else {
                                if (mScrollView.getScrollY() > header_layout.getHeight() / 2 && mScrollView.getScrollY() < header_layout.getHeight()) {
                                    scrollTo(mScrollView.getScrollY(), header_layout.getHeight() - back_layout.getHeight() + 10);
                                } else if (mScrollView.getScrollY() <= header_layout.getHeight() / 2 && mScrollView.getScrollY() > 0) {
                                    scrollTo(mScrollView.getScrollY(), 0);
                                }
                                isRun = false;
                            }
                        }
                    }, 200);
                }
            }
        });
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    hd.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mScroller.isFinished()) {
                                if (mScrollView.getScrollY() > header_layout.getHeight() / 2 && mScrollView.getScrollY() < header_layout.getHeight()) {
                                    scrollTo(mScrollView.getScrollY(), header_layout.getHeight() - back_layout.getHeight() + 10);
                                } else if (mScrollView.getScrollY() <= header_layout.getHeight() / 2 && mScrollView.getScrollY() > 0) {
                                    scrollTo(mScrollView.getScrollY(), 0);
                                }
                            }
                        }
                    },50);
                }
                return false;
            }
        });

    }

    public void  scrollTo(int start, final int end){
        ValueAnimator animation = ValueAnimator.ofInt(start,end);
        animation.setDuration(200);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mScrollView.setScrollY((Integer) animation.getAnimatedValue());
                if (Math.abs((Integer) animation.getAnimatedValue() -end) <=20) {
                    mScrollView.setIsAnim(false);
                } else {
                    mScrollView.setIsAnim(true);
                }
            }
        });
        animation.setInterpolator(new LinearInterpolator());
        animation.start();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dangqian_layout:
                if (position==1) {
                    manager.beginTransaction().replace(R.id.frame, fragBaseFragments.get(0), "dangqian").commit();
                    position = 0;
                    dangqian_txt.setTextColor(this.getResources().getColor(R.color.red_bg));
                    dangqian_txt.setBackgroundResource(R.drawable.redbg);
                    lishi_txt.setTextColor(this.getResources().getColor(R.color.black_26));
                    lishi_txt.setBackgroundResource(R.color.Transparent);
                }
                break;
            case R.id.lishi_layout:
                if (position==0) {
                    manager.beginTransaction().replace(R.id.frame, fragBaseFragments.get(1), "lishi").commit();
                    position = 1;
                    dangqian_txt.setTextColor(this.getResources().getColor(R.color.black_26));
                    lishi_txt.setTextColor(this.getResources().getColor(R.color.red_bg));
                    dangqian_txt.setBackgroundResource(R.color.Transparent);
                    lishi_txt.setBackgroundResource(R.drawable.redbg);
                }
                break;

        }
    }
}
