package com.caiyun.guzhang.fragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.futures.entity.TimesEntity;
import com.android.futures.view.TimesView_small;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.StockInfoActivity;
import com.caiyun.guzhang.javabean.StockData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.google.gson.JsonObject;
import com.zhaojin.myviews.CustomProgressDialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SmallTimeLineFragment extends BaseFragment{
	private TimesView_small mTimesView;
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

		view = inflater.inflate(R.layout.fragment_small_times, container, false);
		findView();
		return view;
	}
	private void findView() {
		mTimesView = (TimesView_small) view.findViewById(R.id.my_fenshi_view);
		mTimesView.setStart(0);
	}
	/**
	 * 每次显示时调用的方法
	 */
	public void onShow(int lastPosition) {

	}
	/***刷新**/
	public void refrush() {
		try {
			mTimesView.setTimesList(((StockInfoActivity) context).getTimesList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
