package com.org.ptr.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.org.ptr.R;
import com.org.ptr.bean.HomeBean;

import java.util.List;

/**
 * Created by guozhk on 2017/6/15.
 */

public class HomeAdapter extends BaseAdapter {

    private Context context;
    private List<HomeBean> mData;
    private LayoutInflater mInflater;

    public HomeAdapter(Context context, List<HomeBean> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }


    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HodlerView hodlerView = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_home, null);
            hodlerView = new HodlerView();
            hodlerView.tvDre = (TextView) convertView.findViewById(R.id.list_item_home_class);

            convertView.setTag(hodlerView);
        } else {
            hodlerView = (HodlerView) convertView.getTag();
        }


        hodlerView.tvDre.setText(mData.get(position).getDre() + "");
        return convertView;
    }

    static class HodlerView {

        public TextView tvDre;

    }

    public void toSkip(int pos) {
        Intent intent = new Intent(context, mData.get(pos).getCl());
        context.startActivity(intent);
    }
}
