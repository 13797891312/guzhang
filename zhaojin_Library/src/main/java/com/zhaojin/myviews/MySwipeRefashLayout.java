package com.zhaojin.myviews;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

public class MySwipeRefashLayout extends SwipeRefreshLayout {
	private View mScrollableChild;
	public MySwipeRefashLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MySwipeRefashLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void setChild(View view){
		mScrollableChild=view;
	}
	@Override
	public boolean canChildScrollUp() {
		// TODO Auto-generated method stub
		 if (android.os.Build.VERSION.SDK_INT < 14) {
		        if (mScrollableChild instanceof AbsListView) {
		            final AbsListView absListView = (AbsListView) mScrollableChild;
		            return absListView.getChildCount() > 0
		                    && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
		                    .getTop() < absListView.getPaddingTop());
		        } else {
		            return mScrollableChild.getScrollY() > 0;
		        }
		    } else {
		        return ViewCompat.canScrollVertically(mScrollableChild, -1);
		    }
	}
}
