package com.caiyun.guzhang;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.util.Cantent;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.TagViewPager;
import com.zhaojin.myviews.TagViewPager.OnGetView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GuideActivity extends BaseActivity{
	private TagViewPager viewPager;
	private int imageIds[]={R.drawable.index1,R.drawable.index2,R.drawable.index3,R.drawable.index4};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		viewPager=(TagViewPager) findViewById(R.id.tagViewPager);
		viewPager.init(R.drawable.shape_photo_tag_select, R.drawable.shape_photo_tag_nomal, 16, 8, 2, 80);
		viewPager.setAutoNext(false, 0);
		viewPager.setId(Cantent.VIEWPAGER_ID_GUIDE);
		viewPager.setOnGetView(new OnGetView() {
			@Override
			public View getView(ViewGroup container, int position) {
				ImageView iv=new ImageView(GuideActivity.this);
				iv.setClickable(true);
				iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
				iv.setId(position);
				iv.setScaleType(ScaleType.CENTER_CROP);
				iv.setImageResource(imageIds[position]);
				container.addView(iv);
				if (position==imageIds.length-1) {
					iv.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(GuideActivity.this,
									MainActivity.class);
							GuideActivity.this.startActivity(intent);
							GuideActivity.this.finish();
						}
					});
				}
				return iv;
			}
		});
		viewPager.setAdapter(imageIds.length,0);
	}
}
