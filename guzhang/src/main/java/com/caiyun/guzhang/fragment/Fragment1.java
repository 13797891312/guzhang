package com.caiyun.guzhang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.GetCodeActivity;
import com.caiyun.guzhang.LoginActivity;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.MyAccountActivity;
import com.caiyun.guzhang.MyFriendsActivity;
import com.caiyun.guzhang.MyStockActivity;
import com.caiyun.guzhang.NoticeActivity;
import com.caiyun.guzhang.QuZhuanQianActivity;
import com.caiyun.guzhang.RankActivity;
import com.caiyun.guzhang.SearchStockActivity;
import com.caiyun.guzhang.TransactionActivity;
import com.caiyun.guzhang.WarningListActivity;
import com.caiyun.guzhang.ZiXuanActivity;
import com.caiyun.guzhang.javabean.NewsData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.MyAPP;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.caiyun.guzhang.view.verticalviewpager.MyPagerAdapter;
import com.caiyun.guzhang.view.verticalviewpager.VerticalViewPager;

import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment1 extends BaseFragment implements OnClickListener {
    private ArrayList<NewsData> mList = new ArrayList();
    private Handler hd;
    private VerticalViewPager mViewPager;
    private MyPagerAdapter adapter;
    private View view;
    private Button button_quzhuanqian,button_login,button_regist;
    private View account;
    private TextView txt_news;
    private int ids[] = {R.id.include1, R.id.include2, R.id.include3, R.id.include4, R.id.include5, R.id.include6, R.id.include7, R.id.include8};
    private String str[] = {"自选股", "通知", "持仓/历史", "预警", "收益榜", "我的关注", "交易中心", "商城"};
    private int resIds[] = {R.drawable.zxg_icon, R.drawable.icon_tz, R.drawable.icon_cc, R.drawable.icon_yj, R.drawable.icon_syb, R.drawable.icon_gz, R.drawable.icon_jyzx, R.drawable.icon_sc};
    private Class myClass[] = {
            ZiXuanActivity.class, NoticeActivity.class, MyStockActivity.class, WarningListActivity.class, RankActivity.class, MyFriendsActivity.class, TransactionActivity.class, null};
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        context = this.getActivity();
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_2, container, false);
        hd = new Handler();
        findView();
        getData();
        return view;
    }

    /*****轮播消息****/
    public class MyRunnble implements Runnable{
        @Override
        public void run() {
            hd.postDelayed(this, 5000);
            if (mViewPager.getCurrentItem() >= adapter.getCount()-1) {
                mViewPager.setCurrentItem(0,true);
            } else {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1,true);
            }
        }
    }


    private void findView() {
//        ((AppCompatActivity)context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle((BaseActivity)context,mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
        view.findViewById(R.id.txt_all_news).setOnClickListener(this);
        mViewPager = (VerticalViewPager) view.findViewById(R.id.viewpager);
        button_login = (Button) view.findViewById(R.id.button_login);
        button_regist = (Button) view.findViewById(R.id.button_regist);
        button_quzhuanqian = (Button) view.findViewById(R.id.button_quzhuanqian);
        account = view.findViewById(R.id.account_layout);
        account.setOnClickListener(this);
        button_quzhuanqian.setOnClickListener(this);
        button_login.setOnClickListener(this);
        button_regist.setOnClickListener(this);

        view.findViewById(R.id.transaction).setOnClickListener(this);
        for (int i = 0; i < ids.length; i++) {
            ImageView iv = (ImageView) view.findViewById(ids[i]).findViewById(R.id.item_icon);
            TextView tv = (TextView) view.findViewById(ids[i]).findViewById(R.id.item_txt);
            tv.setText(str[i]);
            iv.setImageResource(resIds[i]);
            view.findViewById(ids[i]).setOnClickListener(new ItemClickListener(i));
        }
    }
    public class ItemClickListener implements OnClickListener {
        private int position;

        public ItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (myClass[position] == null) {
                return;
            }
            Intent intent = new Intent(context, myClass[position]);
            Fragment1.this.startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.transaction:
//                intent = new Intent(context, TransactionActivity.class);
//                this.startActivity(intent);
                break;
            case R.id.account_layout:
                intent = new Intent(context, MyAccountActivity.class);
                this.startActivity(intent);
                break;
            case R.id.txt_all_news:
                ( (MainActivity)context).myFragmentLayout.setCurrenItem(1);
                break;
            case R.id.button_quzhuanqian:
                intent = new Intent(context, QuZhuanQianActivity.class);
                this.startActivity(intent);
                break;
            case R.id.button_login:
                intent = new Intent(context, LoginActivity.class);
                this.startActivity(intent);
                break;
            case R.id.button_regist:
                intent = new Intent(context, GetCodeActivity.class);
                intent.putExtra("type", 1);
                this.startActivity(intent);
                break;
            case R.id.search_layout:
                intent = new Intent(context, SearchStockActivity.class);
                this.startActivity(intent);
                break;
        }
    }

    /***已经登录***/
    public void setIsLogin(){
        view.findViewById(R.id.noLoging_layout).setVisibility(View.GONE);
        view.findViewById(R.id.logined_layout).setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyAPP.token != null) {
            setIsLogin();
        }
    }

    /**
     * 获取内参数据**
     *
     *
     */
    public void getData() {
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("Neican.getList", new String[]{"page", "pagesize"}, new String[]{String.valueOf(0), "5"}), null, new VolleyListerner(context) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                JsonUtils.listFromJsonWithSubKey(response.getString("data"), NewsData.class, "neicanlist", mList);
                adapter = new MyPagerAdapter(context,mList);
                mViewPager.setAdapter(adapter);
                hd.postDelayed(new MyRunnble(), 5000);

            }
        }, new VolleyErrorListoner(context));

        MainActivity.mQueue.add(request);
    }
}
