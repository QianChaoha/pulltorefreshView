/**
 * 
 */
package com.example.googleplay.adapter;

import java.util.List;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.googleplay.R;
import com.example.googleplay.application.MyApplication;
import com.example.googleplay.data.HomeData.AppInfo;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.view.CommenNetImageView;

public class HomeContentAdapter extends BaseAdapter {
	private Context context;
	private List<AppInfo> contents;

	/**
	 * @param context
	 * @param contents
	 */
	public HomeContentAdapter(Context context, List<AppInfo> contents) {
		this.context = context;
		this.contents = contents;
	}

	@Override
	public int getCount() {
		return contents.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		if (convertView == null) {
			view = View.inflate(context, R.layout.item_app, null);
			holder = new ViewHolder();
			holder.item_icon = (CommenNetImageView) view.findViewById(R.id.item_icon);
			holder.item_title = (TextView) view.findViewById(R.id.item_title);
			holder.item_size = (TextView) view.findViewById(R.id.item_size);
			holder.item_bottom = (TextView) view.findViewById(R.id.item_bottom);
			holder.item_rating = (RatingBar) view.findViewById(R.id.item_rating);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}
		AppInfo appInfo = contents.get(position);
		holder.item_title.setText(appInfo.getName());// 设置应用程序的名字
		String size = Formatter.formatFileSize(context, appInfo.getSize());
		holder.item_size.setText(size);
		holder.item_bottom.setText(appInfo.getDes());
		float stars = appInfo.getStars();
		holder.item_rating.setRating(stars); // 设置ratingBar的值
		String iconUrl = appInfo.getIconUrl(); // http://127.0.0.1:8090/image?name=app/com.youyuan.yyhl/icon.jpg

		// 显示图片的控件
		holder.item_icon.setImageUrl(HttpHelper.URL + "image?name=" + iconUrl,
				((MyApplication) context.getApplicationContext()).getDefaultImgLoader());
		return view;
	}

	static class ViewHolder {
		CommenNetImageView item_icon;
		TextView item_title, item_size, item_bottom;
		RatingBar item_rating;
	}
}
