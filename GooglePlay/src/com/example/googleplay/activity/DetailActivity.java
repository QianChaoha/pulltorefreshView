package com.example.googleplay.activity;

import android.text.TextUtils;
import android.widget.FrameLayout;

import com.android.volley.Request.Method;
import com.example.googleplay.R;
import com.example.googleplay.activity.view.DetailInfoView;
import com.example.googleplay.base.BaseActivity;
import com.example.googleplay.data.AppInfo;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetWorkResponse;

public class DetailActivity extends BaseActivity {
	private FrameLayout bottom_layout, detail_info, detail_safe, detail_des;
	private DetailInfoView detailInfoView;
	private int index = 0;
	private String packageName;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_detail;
	}

	@Override
	protected void initView() {
		// 增加返回按钮
		// ActionBar actionBar = getActionBar();
		// actionBar.setDisplayHomeAsUpEnabled(true);
		initFrameLayout();

	}

	private void initFrameLayout() {
		detail_info = (FrameLayout) findViewById(R.id.detail_info);
		detailInfoView = new DetailInfoView(this);
	}

	@Override
	protected void initData() {
		packageName = getIntent().getStringExtra("packageName");
		if (!TextUtils.isEmpty(packageName)) {
			detail_info.addView(detailInfoView.getView());
			netWorkResponse = new NetWorkResponse<AppInfo>(this, Method.GET, HttpHelper.getDetailUrl(index, packageName), null) {

				@Override
				public void onSuccess(AppInfo backData) {
					detailInfoView.initData(backData);
				}

			};
		}
	}
}
