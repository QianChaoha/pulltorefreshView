package com.example.googleplay.activity.view;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.example.googleplay.R;
import com.example.googleplay.data.AppInfo;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetRequest;
import com.example.googleplay.view.CommenNetImageView;

public class DetailInfoView {

	private Context context;
	private View detailInfoView;
	private CommenNetImageView item_icon;
	private TextView item_title, item_download, item_version, item_date, item_size;
	private RatingBar item_rating;

	public DetailInfoView(Context context) {
		this.context = context;
	}

	public View getView() {
		detailInfoView = View.inflate(context, R.layout.detail_app_info, null);
		item_icon=(CommenNetImageView) detailInfoView.findViewById(R.id.item_icon);
		item_title=(TextView) detailInfoView.findViewById(R.id.item_title);
		item_rating=(RatingBar) detailInfoView.findViewById(R.id.item_rating);
		item_download=(TextView) detailInfoView.findViewById(R.id.item_download);
		item_version=(TextView) detailInfoView.findViewById(R.id.item_version);
		item_date=(TextView) detailInfoView.findViewById(R.id.item_date);
		item_size=(TextView) detailInfoView.findViewById(R.id.item_size);
		return detailInfoView;
	}

	/**
	 * @param imageLoader
	 * @param backData
	 */
	public void initData(AppInfo data) {
		item_icon.setImageUrl(HttpHelper.URL + "image?name=" + data.getIconUrl(), NetRequest.getInstance(context.getApplicationContext()).getImageLoader());
		item_title.setText(data.getName());
		item_rating.setRating(data.getStars());
		item_download.setText("下载:" + data.getDownloadNum());
		item_version.setText("版本:" + data.getVersion());
		item_date.setText("时间:" + data.getDate());
		item_size.setText("大小:" + Formatter.formatFileSize(context, data.getSize()));
	}
}
