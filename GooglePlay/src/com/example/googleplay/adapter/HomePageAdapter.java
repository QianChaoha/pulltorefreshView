package com.example.googleplay.adapter;

import java.util.LinkedList;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.view.CommenNetImageView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class HomePageAdapter extends PagerAdapter {
	private Context context;
	private List<String> pictures;
	private ImageLoader imageLoader;
	private List<CommenNetImageView> commenNetImageViews=new LinkedList<CommenNetImageView>();

	public HomePageAdapter(Context context, List<String> pictures, ImageLoader imageLoader) {
		this.context = context;
		this.pictures = pictures;
		this.imageLoader = imageLoader;
	}

	@Override
	public int getCount() {
		return pictures.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		CommenNetImageView netImageView = new CommenNetImageView(context);
		netImageView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		netImageView.setScaleType(ScaleType.FIT_XY);
		netImageView.setImageUrl(HttpHelper.HOME_PICTURE_URL+pictures.get(position), imageLoader);
		commenNetImageViews.add(netImageView);
		((ViewPager) container).addView(netImageView);
		return netImageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager)container).removeView(commenNetImageViews.get(position));
	}
}
