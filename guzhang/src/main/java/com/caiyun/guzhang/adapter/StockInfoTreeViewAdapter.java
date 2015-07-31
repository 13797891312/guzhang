package com.caiyun.guzhang.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.KChartActivity;
import com.caiyun.guzhang.StockInfoActivity;
import com.caiyun.guzhang.fragment.BaseFragment;
import com.caiyun.guzhang.view.IphoneTreeView_stockinfo.IphoneTreeHeaderAdapter;
import com.caiyun.guzhang.view.IphoneTreeView_stockinfo;
import com.zhaojin.activity.BaseActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;

public class StockInfoTreeViewAdapter extends BaseExpandableListAdapter
		implements IphoneTreeHeaderAdapter {
	// Sample data set. children[i] contains the children (String[]) for
	// groups[i].
	private HashMap<Integer, Integer> groupStatusMap;

	private Context context;
	private IphoneTreeView_stockinfo iphoneTreeView;
	FragmentManager manager;
	/*** 第二个group项 ****/
	private LinearLayout tabView2,tabView1;
	/****第二个group当前选中项****/
	private int frame2position=0;
	/****第一个group当前选中项****/
	private int frame1position=0;

	private ArrayList<BaseFragment> fragments1;
	private ArrayList<BaseFragment> fragments2;

	public StockInfoTreeViewAdapter(Context context,
									IphoneTreeView_stockinfo iphoneTreeView,ArrayList<BaseFragment> fragments1,ArrayList<BaseFragment> fragments2) {
		// TODO Auto-generated constructor stub
		groupStatusMap = new HashMap<Integer, Integer>();
		this.context = context;
		this.iphoneTreeView = iphoneTreeView;
		manager = ((BaseActivity) context).getSupportFragmentManager();
		this.fragments1 = fragments1;
		this.fragments2 = fragments2;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	public Object getGroup(int groupPosition) {
		return null;
	}

	public int getGroupCount() {
		return 2;
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			switch (groupPosition) {
			case 0:
				convertView = View.inflate(context,
						R.layout.item1_stockinfo_treeview, null);
				tabView1=(LinearLayout) convertView.findViewById(R.id.tabView1);
				for (int i = 0; i < ((LinearLayout) tabView1).getChildCount(); i++) {
					((LinearLayout) tabView1).getChildAt(i)
							.setOnClickListener(new Mylistener1(i));
				}
				initFragment1(convertView);
				convertView.findViewById(R.id.frame1).setOnClickListener(new KLintClicklistener(frame1position));
				break;
			case 1:
				convertView = View.inflate(context,
						R.layout.item2_stokinfo_treeview, null);
				initFragment2(convertView);
				break;
			}
		}
		return convertView;
	}

	/**
	 * 
	 * @param convertView
	 *            初始化第一个fragment容器
	 */
	private void initFragment1(View convertView) {
		manager.beginTransaction()
		.replace(R.id.frame1, fragments1.get(0)).commit();
	}
	
	/**
	 * 切换第一个fragmeng容器内容
	 */
	
	public void setCurrentFrame1(int position) {
		for (int i = 0; i < tabView1.getChildCount(); i++) {
			if (i==position) {
				((LinearLayout)(tabView1.getChildAt(i))).getChildAt(0).setEnabled(true);
					manager.beginTransaction()
					.replace(R.id.frame1,fragments1.get(i)).commit();
			}else {
				((LinearLayout)(tabView1.getChildAt(i))).getChildAt(0).setEnabled(false);
			}
		}
	}

	/**
	 * 
	 * @param convertView
	 *            初始化第二个fragment容器
	 */
	private void initFragment2(View convertView) {
		manager.beginTransaction()
				.replace(R.id.frame, fragments2.get(0)).commit();
	}

	/**
	 * 切换第二个fragmeng容器内容
	 */

	public void setCurrentFrame2(int position) {
		for (int i = 0; i < tabView2.getChildCount(); i++) {
			if (i == position) {
				((LinearLayout) (tabView2.getChildAt(i))).getChildAt(0).setEnabled(true);
					manager.beginTransaction()
							.replace(R.id.frame, fragments2.get(i)).commit();
				((LinearLayout) (((LinearLayout) (iphoneTreeView.getHeaderView())).getChildAt(i))).getChildAt(0).setEnabled(true);
			} else {
				((LinearLayout) (tabView2.getChildAt(i))).getChildAt(0).setEnabled(false);
				((LinearLayout) (((LinearLayout) (iphoneTreeView.getHeaderView())).getChildAt(i))).getChildAt(0).setEnabled(false);
			}
		}
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView!=null) {
			return convertView;
		}
		switch (groupPosition) {
		case 0:
			convertView = View
					.inflate(context, R.layout.view_transparent, null);
			break;
		case 1:
			convertView = View.inflate(context,
					R.layout.tablayout_treeview_group, null);
			tabView2 = (LinearLayout) convertView;
			for (int i = 0; i < ((LinearLayout) tabView2).getChildCount(); i++) {
				((LinearLayout) tabView2).getChildAt(i)
						.setOnClickListener(new Mylistener(i));
			}
		}
		return convertView;
	}

	@Override
	public int getTreeHeaderState(int groupPosition, int childPosition) {
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1
				&& !iphoneTreeView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}
	}

	@Override
	public void configureTreeHeader(View header, int groupPosition,
			int childPosition, int alpha) {
		switch (groupPosition) {
		case 0:
			header.findViewById(R.id.tab1).setVisibility(View.INVISIBLE);
			header.findViewById(R.id.tab2).setVisibility(View.INVISIBLE);
			header.findViewById(R.id.tab3).setVisibility(View.INVISIBLE);
			header.findViewById(R.id.tab4).setVisibility(View.INVISIBLE);
			header.findViewById(R.id.tab5).setVisibility(View.INVISIBLE);
			header.setAlpha(0);
			header.setVisibility(View.INVISIBLE);
			break;
		case 1:
			header.findViewById(R.id.tab1).setVisibility(View.VISIBLE);
			header.findViewById(R.id.tab2).setVisibility(View.VISIBLE);
			header.findViewById(R.id.tab3).setVisibility(View.VISIBLE);
			header.findViewById(R.id.tab4).setVisibility(View.VISIBLE);
			header.findViewById(R.id.tab5).setVisibility(View.VISIBLE);
			header.setAlpha(255);
			header.setVisibility(View.VISIBLE);
			for (int i = 0; i < ((LinearLayout) header).getChildCount(); i++) {
				((LinearLayout) header).getChildAt(i)
						.setOnClickListener(new Mylistener(i));
			}
			break;
		}
		// ((TextView) header).setText(groups[groupPosition]);
	}

	@Override
	public void onHeadViewClick(int groupPosition, int status) {
		// TODO Auto-generated method stub
		groupStatusMap.put(groupPosition, status);
	}

	@Override
	public int getHeadViewClickStatus(int groupPosition) {
		if (groupStatusMap.containsKey(groupPosition)) {
			return groupStatusMap.get(groupPosition);
		} else {
			return 0;
		}
	}

	public void expandAll() {
		for (int i = 0; i < getGroupCount(); i++) {
			iphoneTreeView.expandGroup(i);
		}
	}
	public class Mylistener implements OnClickListener{
		int position;
		public Mylistener(int position) {
			this.position=position;
		}
		@Override
		public void onClick(View v) {
			if (frame2position==position) {
				return;
			}
			setCurrentFrame2(position);
			frame2position=position;
		}
	}
	
	public class Mylistener1 implements OnClickListener{
		int position;
		public Mylistener1(int position) {
			this.position=position;
		}
		@Override
		public void onClick(View v) {
			if (frame1position==position) {
				return;
			}
			setCurrentFrame1(position);
			frame1position=position;
		}
	}


	public class KLintClicklistener implements OnClickListener{
		int position;
		public KLintClicklistener(int position) {
			this.position=position;
		}
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(context, KChartActivity.class);
			intent.putExtra("position", frame1position);
			intent.putExtra("code", ((StockInfoActivity) context).getStockCode());
			context.startActivity(intent);
		}
	}


}