package com.caiyun.guzhang.adapter;

import java.util.HashMap;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.fragment.Fragment1;
import com.caiyun.guzhang.fragment.Fragment3;
import com.caiyun.guzhang.fragment.GoodPeopleRecordFragment;
import com.caiyun.guzhang.fragment.TimeLineFragment_small;
import com.caiyun.guzhang.view.IphoneTreeView;
import com.caiyun.guzhang.view.IphoneTreeView.IphoneTreeHeaderAdapter;
import com.zhaojin.activity.BaseActivity;

import android.content.Context;
import android.support.v4.app.Fragment;
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
	private IphoneTreeView iphoneTreeView;
	FragmentManager manager;
	/*** 第二个group项 ****/
	private LinearLayout tabView2,tabView1;
	/****第二个group当前选中项****/
	private int frame2position=0;
	/****第一个group当前选中项****/
	private int frame1position=0;

	public StockInfoTreeViewAdapter(Context context,
			IphoneTreeView iphoneTreeView) {
		// TODO Auto-generated constructor stub
		groupStatusMap = new HashMap<Integer, Integer>();
		this.context = context;
		this.iphoneTreeView = iphoneTreeView;
		manager = ((BaseActivity) context).getSupportFragmentManager();
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
		.replace(R.id.frame1, new TimeLineFragment_small()).commit();
	}
	
	/**
	 * 切换第一个fragmeng容器内容
	 */
	
	private Class[] fragments1={TimeLineFragment_small.class,Fragment1.class,TimeLineFragment_small.class,Fragment1.class,TimeLineFragment_small.class};
	public void setCurrentFrame1(int position) {
		for (int i = 0; i < tabView1.getChildCount(); i++) {
			if (i==position) {
				((LinearLayout)(tabView1.getChildAt(i))).getChildAt(0).setEnabled(true);
				try {
					manager.beginTransaction()
					.replace(R.id.frame1, (Fragment) (fragments1[i].newInstance())).commit();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				.replace(R.id.frame, new GoodPeopleRecordFragment()).commit();
	}

	/**
	 * 切换第二个fragmeng容器内容
	 */
	
	private Class[] fragments={GoodPeopleRecordFragment.class,Fragment1.class,Fragment1.class,Fragment3.class,Fragment1.class};
	public void setCurrentFrame2(int position) {
		for (int i = 0; i < tabView2.getChildCount(); i++) {
			if (i==position) {
				((LinearLayout)(tabView2.getChildAt(i))).getChildAt(0).setEnabled(true);
				try {
					manager.beginTransaction()
					.replace(R.id.frame, (Fragment) (fragments[i].newInstance())).commit();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				((LinearLayout)(((LinearLayout)(iphoneTreeView.getHeaderView())).getChildAt(i))).getChildAt(0).setEnabled(true);
			}else {
				((LinearLayout)(tabView2.getChildAt(i))).getChildAt(0).setEnabled(false);
				((LinearLayout)(((LinearLayout)(iphoneTreeView.getHeaderView())).getChildAt(i))).getChildAt(0).setEnabled(false);
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
			header.setAlpha(0);
			break;
		case 1:
			header.setAlpha(255);
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
}