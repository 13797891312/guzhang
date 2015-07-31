package com.caiyun.guzhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.TransactionActivity;

import java.util.ArrayList;

public class Fragment_MyStock_Has_Listview extends BaseAdapter {
	private Context context;
	private ArrayList<String> mList;
	private View pop;
	PopupWindow window;
	public Fragment_MyStock_Has_Listview(Context context, ArrayList<String> mList) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mList=mList;
		pop = View.inflate(context, R.layout.pop_oval_menu, null);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
//		return mList.size();
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
//		return mList.get(position);
		return 0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HolderView holder;
		if (convertView==null) {
			convertView=View.inflate(context, R.layout.item_fragment_mystock_has_listview, null);
			holder=new HolderView();
			holder.menu_iv = (ImageView) convertView.findViewById(R.id.menu_iv);
			convertView.setTag(holder);
		}else {
			holder=(HolderView) convertView.getTag();
		}
		holder.menu_iv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenuPop(position,v);
			}
		});
		return convertView;
	}
	
	static class HolderView{
		ImageView menu_iv;
	}

	public void showMenuPop(int position,View v) {
		pop.findViewById(R.id.buy).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				window.dismiss();
				Intent intent = new Intent(context, TransactionActivity.class);
				context.startActivity(intent);
			}
		});
		pop.findViewById(R.id.sell).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				window.dismiss();
				Intent intent = new Intent(context, TransactionActivity.class);
				context.startActivity(intent);
			}
		});
		window = new PopupWindow(pop, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,true);
		window.setBackgroundDrawable(new ColorDrawable());
		window.setFocusable(true);
		window.setTouchable(true);
		window.showAsDropDown(v,(int)(-95*MainActivity.screenScale),(int)(-38*MainActivity.screenScale));
	}
}
