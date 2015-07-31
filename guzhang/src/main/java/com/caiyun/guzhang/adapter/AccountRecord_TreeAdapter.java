package com.caiyun.guzhang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.view.IphoneTreeView;

import java.util.ArrayList;
import java.util.HashMap;

public class AccountRecord_TreeAdapter extends BaseExpandableListAdapter implements
		IphoneTreeView.IphoneTreeHeaderAdapter {
	// Sample data set. children[i] contains the children (String[]) for
	// groups[i].
	private HashMap<Integer, Integer> groupStatusMap;
	private ArrayList<String> groups ;
	private HashMap<String, ArrayList<String>> mMap;

	private Context context;
	private IphoneTreeView iphoneTreeView;
	public AccountRecord_TreeAdapter(Context context, IphoneTreeView iphoneTreeView, ArrayList<String> groups, HashMap<String, ArrayList<String>> mMap) {
		// TODO Auto-generated constructor stub
		groupStatusMap = new HashMap<Integer, Integer>();
		this.context=context;
		this.iphoneTreeView=iphoneTreeView;
		this.groups=groups;
		this.mMap=mMap;

	}

	public Object getChild(int groupPosition, int childPosition) {
		return mMap.get(groups.get(groupPosition)).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
//		return groupPosition==-1?0:mMap.get(groups.get(groupPosition)).size();
		return 5;
	}


	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	public int getGroupCount() {
//		return groups.size();
		return 5;
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
		// TODO Auto-generated method stub
			convertView = View.inflate(context,
					R.layout.item_accountrecord, null);
		TextView txt_content = (TextView) convertView.findViewById(R.id.txt_content);
		TextView txt_time = (TextView) convertView.findViewById(R.id.txt_time);
		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			convertView = View.inflate(context,R.layout.header_tree_contact, null);
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
		// TODO Auto-generated method stub
//		((TextView) header)
//				.setText(groups.get(groupPosition));
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
	
	public void expandAll(){
		for (int i = 0; i < getGroupCount(); i++) {
			iphoneTreeView.expandGroup(i);
		}
	}
}