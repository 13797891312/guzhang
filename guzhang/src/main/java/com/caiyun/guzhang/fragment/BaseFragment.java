package com.caiyun.guzhang.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	public boolean isLoaded=false;
	public void onShow(int lastPosition){};
	public void notifyData(){};
	public Context context;
}
