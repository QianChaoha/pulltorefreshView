package com.example.googleplay.fragment;

import java.util.List;
import java.util.Map;

import android.view.View;

import com.android.volley.Request.Method;
import com.example.googleplay.R;
import com.example.googleplay.adapter.HomeContentAdapter;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.data.AppInfo;
import com.example.googleplay.data.HomeData;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetWorkResponse;
import com.example.googleplay.view.BaseListView;
import com.google.gson.Gson;

public class HomeFragment extends BaseFragment {
	private BaseListView listview;
	private int index = 0;
	private HomeContentAdapter homeContentAdapter = null;
	private NetWorkResponse<HomeData> netWorkResponse;
	@Override
	protected int getLayoutId() {
		return R.layout.homefragment_layout;
	}

	@Override
	protected void initView(View view) {
		listview = (BaseListView) view.findViewById(R.id.listview);

	}

	@Override
	protected void initData() {
		netWorkResponse = new NetWorkResponse<HomeData>(mProgressDialog, Method.GET, HttpHelper.HOME_URL + index, null,
				null) {

			@Override
			public void onSuccess(HomeData backData) {
				index++;
				homeContentAdapter = new HomeContentAdapter(getActivity(), backData.getList()) {
					
					@Override
					protected String getUrl() {
						
						return HttpHelper.HOME_URL + index;
					}
					
					@Override
					protected Map<String, String> getParam() {
						return null;
					}
					
					@Override
					protected int getMethod() {
						return Method.GET;
					}

					@Override
					protected List<AppInfo> onLoadMore(Gson mGson, String data) {
						index++;
						HomeData homeData=mGson.fromJson(data, HomeData.class);
						System.out.println(data==null);
						if (homeData!=null) {
							return homeData.getList();
						}
						return null;
					}

				};
				listview.setAdapter(homeContentAdapter);
			}
		};
	}
}
