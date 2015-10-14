package com.example.googleplay.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.base.BaseCommenAdapter;
import com.example.googleplay.data.SubJectData;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetRequest;
import com.example.googleplay.view.CommenNetImageView;

public class SubjectInfoAdapter extends BaseCommenAdapter<SubJectData, ViewHolder> {


	/**
	 * @param context
	 * @param lists
	 * @param imageLoader
	 */
	public SubjectInfoAdapter(Context context, List<SubJectData> lists) {
		super(context, lists);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.item_subject;
	}

	@Override
	protected ViewHolder getHolder() {
		return new ViewHolder();
	}

	@Override
	protected void initViewByHolder(ViewHolder holder, View convertView) {
		holder.item_icon = (CommenNetImageView) convertView.findViewById(R.id.item_icon);
		holder.item_txt = (TextView) convertView.findViewById(R.id.item_txt);
	}

	@Override
	protected void initData(ViewHolder holder, SubJectData data, int position) {
		holder.item_icon.setImageUrl(HttpHelper.URL+"image?name="+data.getUrl(), NetRequest.getInstance(context.getApplicationContext()).getImageLoader());
		holder.item_txt.setText(data.getDes());
	}

}

class ViewHolder {
	CommenNetImageView item_icon;
	TextView item_txt;
}
