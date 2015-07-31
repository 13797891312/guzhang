package com.caiyun.guzhang.adapter;

import java.util.List;

import com.android.volley.toolbox.JsonObjectRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.javabean.HotData;
import com.caiyun.guzhang.util.MyAPP;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.zhaojin.utils.LogUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONObject;

public class Fragment2_gridView_adapter extends BaseAdapter {
    private Context context;
    private List<HotData> mList;

    public Fragment2_gridView_adapter(Context context, List<HotData> mList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
//		return mList.size();
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        HolderView holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_fragment2_gridview, null);
            holder = new HolderView();
            holder.hotName = (TextView) convertView.findViewById(R.id.textView1);
            holder.hotRise = (TextView) convertView.findViewById(R.id.textView2);
            holder.longtouName = (TextView) convertView.findViewById(R.id.textView3);
            holder.longtouPrice = (TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }
        HotData item = mList.get(position);
        holder.hotName.setText(item.getTitle());
        holder.longtouName.setText(item.getLongtouname());
        holder.longtouPrice.setText(item.getPrice() + "    " + item.getCoderise() + "%");
        if (item.getHotrise() == 200) {
            holder.hotRise.setText("--");
        } else {
            holder.hotRise.setText(item.getHotrise() + "%");
        }
        if (item.getCoderise() < 0) {
            holder.longtouPrice.setTextColor(MyAPP.green);
        } else {
            holder.longtouPrice.setTextColor(MyAPP.red);
        }
        if (item.getHotrise() < 0) {
            holder.hotRise.setTextColor(MyAPP.green);
        } else {
            holder.hotRise.setTextColor(MyAPP.red);
        }
        if (!item.isLoading()) {
            getHotData(item, holder.hotRise);
        }

        return convertView;
    }

    static class HolderView {
        TextView longtouName, hotName, hotRise, longtouPrice;
    }

    /**
     * 获取热门涨幅数据（后台计算慢，所以单独异步请求）**
     */
    public void getHotData(final HotData item, final TextView v) {
        item.setIsLoading(true);
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("Concept.getConceptRise", new String[]{"id"}, new String[]{item.getId()}), null, new VolleyListerner(context) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                item.setHotrise(response.getDouble("data"));
                v.setText(item.getHotrise() + "%");
                if (item.getHotrise() < 0) {
                    v.setTextColor(MyAPP.green);
                } else {
                    v.setTextColor(MyAPP.red);
                }
            }
        }, new VolleyErrorListoner(context));
        MainActivity.mQueue.add(request);
    }
}
