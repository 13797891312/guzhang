package com.caiyun.guzhang;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.util.SaveDate;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.utils.StringUtils;

public class StartActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		if (!SaveDate.getInstence(this.getApplicationContext()).getVersion().equals(StringUtils.getCurrentVersion(this))) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					StartActivity.this.startActivity(new Intent(StartActivity.this, GuideActivity.class));
					StartActivity.this.finish();
					SaveDate.getInstence(StartActivity.this.getApplicationContext()).setVersion(StringUtils.getCurrentVersion(StartActivity.this));
				}
			}, 2000);
		}else {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					StartActivity.this.startActivity(new Intent(StartActivity.this, Activitys.class));
					StartActivity.this.finish();
				}
			}, 2000);
		}
		
	}
	
}
