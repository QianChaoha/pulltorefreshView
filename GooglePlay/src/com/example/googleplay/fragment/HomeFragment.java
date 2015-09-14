package com.example.googleplay.fragment;

import java.util.List;

import android.view.View;

import com.android.volley.Request.Method;
import com.example.googleplay.R;
import com.example.googleplay.adapter.HomeContentAdapter;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.data.HomeData;
import com.example.googleplay.data.HomeData.AppInfo;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetJsonRequest;
import com.example.googleplay.interfaces.NetRequestResult;
import com.example.googleplay.view.BaseListView;

public class HomeFragment extends BaseFragment {
	private BaseListView listview;
	private List<AppInfo> contents;

	@Override
	protected int getLayoutId() {
		return R.layout.homefragment_layout;
	}

	@Override
	protected void initView(View view) {
		listview = (BaseListView) view.findViewById(R.id.listview);
		NetJsonRequest<HomeData> netJsonRequest = new NetJsonRequest<HomeData>(mProgressDialog);
		netJsonRequest.netJsonRequest(Method.GET, HttpHelper.HOME_URL, null, HomeData.class, new NetRequestResult<HomeData>() {

			@Override
			public void onResponse(HomeData result) {
				if (result != null && result.getList() != null && result.getList().size() > 0) {
					contents=result.getList();
					listview.setAdapter(new HomeContentAdapter(getActivity(), contents));
				}
			}
		}, null);
	}

	@Override
	protected void initData() {

	}
}
