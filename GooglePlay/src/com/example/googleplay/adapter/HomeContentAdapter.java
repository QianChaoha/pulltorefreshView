package com.example.googleplay.adapter;

import java.util.List;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.base.AdapterLoadMoreWithAnim;
import com.example.googleplay.data.AppInfo;
import com.example.googleplay.data.HomeData;
import com.example.googleplay.data.HomeViewHolder;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetRequest;
import com.example.googleplay.view.CommenNetImageView;

public abstract class HomeContentAdapter<T> extends AdapterLoadMoreWithAnim<AppInfo, HomeViewHolder,HomeData> {


	/**
	 * @param context
	 * @param lists
	 * @param imageLoader
	 * @param requestQueue
	 */
	public HomeContentAdapter(Context context, List<AppInfo> lists) {
		super(context, lists);
	}

	@Override
	protected HomeViewHolder getHolder() {
		return new HomeViewHolder();
	}

	@Override
	protected void initViewByHolder(HomeViewHolder holder, View convertView) {
		holder.item_icon = (CommenNetImageView) convertView.findViewById(R.id.item_icon);
		holder.item_title = (TextView) convertView.findViewById(R.id.item_title);
		holder.item_size = (TextView) convertView.findViewById(R.id.item_size);
		holder.item_bottom = (TextView) convertView.findViewById(R.id.item_bottom);
		holder.item_rating = (RatingBar) convertView.findViewById(R.id.item_rating);
	}

	@Override
	protected void initData(HomeViewHolder holder, AppInfo data, int position) {
		holder.item_title.setText(data.getName());// 设置应用程序的名字
		String size = Formatter.formatFileSize(context, data.getSize());
		holder.item_size.setText(size);
		holder.item_bottom.setText(data.getDes());
		float stars = data.getStars();
		holder.item_rating.setRating(stars); // 设置ratingBar的值
		String iconUrl = data.getIconUrl(); // http://127.0.0.1:8090/image?name=app/com.youyuan.yyhl/icon.jpg
		// 显示图片的控件
		holder.item_icon.setImageUrl(HttpHelper.URL + "image?name=" + iconUrl, NetRequest.getInstance(context).getImageLoader());
	}

	@Override
	protected int getLayoutId() {
		return R.layout.item_app;
	}
}
