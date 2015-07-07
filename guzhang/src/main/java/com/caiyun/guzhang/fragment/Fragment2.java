package com.caiyun.guzhang.fragment;

import com.caiyun.guzhang.MyFriendsActivity;
import com.caiyun.guzhang.MyStockActivity;
import com.caiyun.guzhang.RankActivity;
import com.caiyun.guzhang.TiCaiInfoActivity;
import com.caiyun.guzhang.WarningListActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.TransactionActivity;
import com.caiyun.guzhang.util.ImageUtils;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment2 extends BaseFragment implements OnClickListener {
    private View view;
    private ImageView toggleMenu;
    private int ids[] = {R.id.include1, R.id.include2, R.id.include3, R.id.include4, R.id.include5, R.id.include6, R.id.include7, R.id.include8};
    private String str[] = {"自选股", "通知", "持仓/历史", "预警", "收益榜", "我的关注", "交易中心", "商城"};
    private int resIds[] = {R.drawable.zxg_icon, R.drawable.icon_tz, R.drawable.icon_cc, R.drawable.icon_yj, R.drawable.icon_syb, R.drawable.icon_gz, R.drawable.icon_jyzx, R.drawable.icon_sc};
    private Class myClass[] = {
            TiCaiInfoActivity.class,null, MyStockActivity.class, WarningListActivity.class, RankActivity.class, MyFriendsActivity.class,TransactionActivity.class,null};

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        context = this.getActivity();
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_2, container, false);
        findView();
        return view;
    }

    private void findView() {
        toggleMenu = (ImageView) view.findViewById(R.id.toggleMenu);
        ImageLoader.getInstance().displayImage("http://q.qlogo.cn/qqapp/1104707878/F4F9A9E2F51379AAEBB984E0E20E551F/100", toggleMenu, getOption());
        toggleMenu.setOnClickListener(this);

//		iv_bg.setImageBitmap(ImageUtils.blurBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher), context));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, (int) ((MainActivity.screenWidth - 16 * MainActivity.screenScale) / 4 + 40));
//		view.findViewById(R.id.line1).setLayoutParams(params);
//		view.findViewById(R.id.line2).setLayoutParams(params);
        view.findViewById(R.id.transaction).setOnClickListener(this);
        for (int i = 0; i < ids.length; i++) {
            ImageView iv = (ImageView) view.findViewById(ids[i]).findViewById(R.id.item_icon);
            TextView tv = (TextView) view.findViewById(ids[i]).findViewById(R.id.item_txt);
            tv.setText(str[i]);
            iv.setImageResource(resIds[i]);
            view.findViewById(ids[i]).setOnClickListener(new ItemClickListener(i));
        }
    }

    public DisplayImageOptions getOption() {
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
//				.showImageForEmptyUri(R.drawable.defalut_user_icon)
                // 地址为空时加载的图片
//				.showImageOnFail(R.drawable.defalut_user_icon)
                // 加载失败显示的图片
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Config.RGB_565)// 图片显示清晰度
                .displayer(new RoundedBitmapDisplayer((int) (20 * MainActivity.screenScale)))//是否设置为圆角，弧度为多少
                .cacheInMemory(true).cacheOnDisk(true).build();
        return displayImageOptions;
    }

    public class ItemClickListener implements OnClickListener {
        private int position;

        public ItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (myClass[position]==null) {
                return;
            }
            Intent intent = new Intent(context, myClass[position]);
            Fragment2.this.startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.transaction:
                intent = new Intent(context, TransactionActivity.class);
                this.startActivity(intent);
                break;
            case R.id.toggleMenu:
                ((MainActivity) context).openMenu();
                break;
        }
    }
}
