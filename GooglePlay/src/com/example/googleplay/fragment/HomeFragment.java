package com.example.googleplay.fragment;

import java.util.List;
import java.util.Map;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;

import com.android.volley.Request.Method;
import com.example.googleplay.R;
import com.example.googleplay.adapter.HomeContentAdapter;
import com.example.googleplay.adapter.HomePageAdapter;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.data.AppInfo;
import com.example.googleplay.data.HomeData;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetWorkResponse;
import com.example.googleplay.util.ScreenUtil;
import com.example.googleplay.view.BaseListView;
import com.google.gson.Gson;

public class HomeFragment extends BaseFragment {
	private BaseListView listview;
	private int index = 0;
	private HomeContentAdapter homeContentAdapter = null;
	private NetWorkResponse<HomeData> netWorkResponse;
	private ViewPager viewPager;
	private List<String> pictures;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			System.out.println((viewPager.getCurrentItem() + 1) % pictures.size());
			viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % pictures.size());
			handler.sendEmptyMessageDelayed(0, 3000);
		};
	};

	@Override
	protected int getLayoutId() {
		return R.layout.homefragment_layout;
	}

	@Override
	protected void initView(View view) {
		listview = (BaseListView) view.findViewById(R.id.listview);
		viewPager = new ViewPager(getActivity());
		viewPager.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,ScreenUtil.dip2px(getActivity(), 200)));
		listview.addHeaderView(viewPager);
	}

	@Override
	protected void initData() {
		netWorkResponse = new NetWorkResponse<HomeData>(mProgressDialog, Method.GET, HttpHelper.HOME_URL + 0, null, null) {

			@Override
			public void onSuccess(HomeData backData) {
				pictures = backData.getPicture();
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
						HomeData homeData = mGson.fromJson(data, HomeData.class);
						System.out.println(data == null);
						if (homeData != null) {
							return homeData.getList();
						}
						return null;
					}

				};
				listview.setAdapter(homeContentAdapter);
				// 增加图片轮询
				viewPager.setAdapter(new HomePageAdapter(getActivity(), pictures, getDefaultImageLoader()));
				if (pictures != null && pictures.size() > 0) {
					handler.sendEmptyMessageDelayed(0, 3000);
				}
			}
		};
	}
}
