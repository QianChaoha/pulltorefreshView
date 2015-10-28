package com.example.googleplay.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplay.R;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetRequest;
import com.example.googleplay.view.CommenNetImageView;

public class HorizontalScrollViewAdapter {

	private Context mContext;
	private List<String> mDatas;

	public HorizontalScrollViewAdapter(Context context, List<String> mDatas) {
		this.mContext = context;
		this.mDatas = mDatas;
	}

	public int getCount() {
		return mDatas.size();
	}

	public Object getItem(int position) {
		return mDatas.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ScreenViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ScreenViewHolder();
			convertView=View.inflate(mContext, R.layout.net_imageview, null);
			viewHolder.netImageView = (CommenNetImageView) convertView.findViewById(R.id.net_imageview);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ScreenViewHolder) convertView.getTag();
		}
		viewHolder.netImageView.setImageUrl(HttpHelper.URL+"image?name="+mDatas.get(position), NetRequest.getInstance(mContext.getApplicationContext()).getImageLoader());
		return convertView;
	}

	private class ScreenViewHolder {
		CommenNetImageView netImageView;
	}

}
