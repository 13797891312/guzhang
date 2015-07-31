package com.caiyun.guzhang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.futures.entity.TimesEntity;
import com.android.futures.view.TimesView;
import com.android.futures.view.TimesView_small;
import com.android.volley.toolbox.JsonObjectRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.KChartActivity;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.zhaojin.myviews.CustomProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BigTimeLineFragment extends BaseFragment{
	private TimesView mTimesView;
	private JSONArray mDatas;
	List<TimesEntity> timesList = new ArrayList<TimesEntity>();
	CustomProgressDialog dialog;
	View view;
	Context context;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		context=this.getActivity();
		if (view != null) {
			return view;
		}

		view = inflater.inflate(R.layout.fragment_big_times, container, false);
		findView();
		getData();
		return view;
	}
	private void findView() {
		mTimesView = (TimesView) view.findViewById(R.id.my_fenshi_view);
		mTimesView.setStart(0);
		mTimesView.setTimesList(timesList);
	}
	/**
	 * 每次显示时调用的方法
	 */
	public void onShow(int lastPosition) {

	}

	private void getData() {
		String url = "http://192.168.1.201/minute.ashx?code="+((KChartActivity)context).getCode();
		dialog= CustomProgressDialog.startProgressDialog(dialog, context, "正在加载...");
		JsonObjectRequest request = new JsonObjectRequest(url, null, new VolleyListerner(context, dialog) {
			@Override
			public void onSucess(JSONObject response) throws Exception {
				super.onSucess(response);
				timesList.clear();
				JSONArray array = response.getJSONObject("data").getJSONArray("fs");
				double zs = response.getJSONObject("data").getDouble("zs");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					if (i==0) {
						timesList.add(new TimesEntity(object.getString("time"),zs, zs,0,0,0));
					}
					timesList.add(new TimesEntity(object.getString("time"), object
							.getDouble("price"), object.getDouble("price"), object
							.getInt("volume"), object.getInt("volume"), object.getInt("volume")));
				}
				mTimesView.setTimesList(timesList);
			}
		}, new VolleyErrorListoner(context, dialog));
		MainActivity.mQueue.add(request);
	}
}
