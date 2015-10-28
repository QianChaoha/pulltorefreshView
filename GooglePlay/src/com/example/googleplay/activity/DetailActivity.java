package com.example.googleplay.activity;

import java.util.List;

import android.text.TextUtils;
import android.widget.FrameLayout;

import com.android.volley.toolbox.StringRequest;
import com.example.googleplay.R;
import com.example.googleplay.activity.view.DetailInfoHolder;
import com.example.googleplay.activity.view.DetailSafeHolder;
import com.example.googleplay.adapter.HorizontalScrollViewAdapter;
import com.example.googleplay.base.BaseActivity;
import com.example.googleplay.data.AppInfo;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetWorkResponse;
import com.example.googleplay.view.MyHorizontalScrollView;
import com.example.volley.Request.Method;
import com.example.volley.RequestQueue;

public class DetailActivity extends BaseActivity {
	private FrameLayout bottom_layout, detail_info, detail_safe, detail_des;
	private DetailInfoHolder detailInfoView;
	private DetailSafeHolder detailSafeHolder;
	private int index = 0;
	private String packageName;
	private StringRequest request;
	private RequestQueue requestQueue;
	private MyHorizontalScrollView detail_screen_horizontal;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_detail;
	}

	@Override
	protected void initView() {
		// 增加返回按钮
		// ActionBar actionBar = getActionBar();
		// actionBar.setDisplayHomeAsUpEnabled(true);
		detail_screen_horizontal = (MyHorizontalScrollView) findViewById(R.id.detail_screen_horizontal);

		initFrameLayout();

	}

	private void initFrameLayout() {
		detail_info = (FrameLayout) findViewById(R.id.detail_info);
		detailInfoView = new DetailInfoHolder(this);
		detail_safe = (FrameLayout) findViewById(R.id.detail_safe);
		detailSafeHolder = new DetailSafeHolder(this);
	}

	@Override
	protected void initData() {
		packageName = getIntent().getStringExtra("packageName");
		if (!TextUtils.isEmpty(packageName)) {
			detail_info.addView(detailInfoView.getView());
			detail_safe.addView(detailSafeHolder.getView());
			NetWorkResponse<AppInfo> netWorkResponse = new NetWorkResponse<AppInfo>(this, Method.GET, HttpHelper.getDetailUrl(index, packageName),
					null) {

				@Override
				public void onSuccess(AppInfo backData) {
					detailInfoView.initData(backData);
					detailSafeHolder.initData(backData);
					initScreenPic(backData.getScreen());
				}
			};
		}
	}

	/**
	 * @param screen
	 */
	protected void initScreenPic(List<String> screen) {
		if (screen != null && screen.size() > 0) {
				detail_screen_horizontal.initDatas(new HorizontalScrollViewAdapter(this, screen));
		}

	}

}
