package com.example.googleplay.fragment;

import java.util.List;
import android.view.View;
import com.android.volley.Request.Method;
import com.example.googleplay.R;
import com.example.googleplay.adapter.HomeContentAdapter;
import com.example.googleplay.base.BaseData;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.data.HomeData;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetWorkResponse;
import com.example.googleplay.view.BaseListView;

public class HomeFragment extends BaseFragment {
	private BaseListView listview;

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
		NetWorkResponse<HomeData> netWorkResponse = new NetWorkResponse<HomeData>(mProgressDialog,Method.GET,  HttpHelper.HOME_URL, null, null) {

			@Override
			public void onSuccess(HomeData backData) {
				listview.setAdapter(new HomeContentAdapter(getActivity(), backData.getList()));
			}
		};
	}
}
