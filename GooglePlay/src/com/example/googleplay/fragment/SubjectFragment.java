package com.example.googleplay.fragment;

import java.util.List;

import android.view.View;

import com.android.volley.Request.Method;
import com.example.googleplay.R;
import com.example.googleplay.adapter.SubjectInfoAdapter;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.base.BaseListView;
import com.example.googleplay.data.SubJectData;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetWorkResponse;

public class SubjectFragment extends BaseFragment {
	private BaseListView baseListView;

	@Override
	protected int getLayoutId() {
		return R.layout.baselistview;
	}

	@Override
	protected void initView(View view) {
		baseListView = (BaseListView) view.findViewById(R.id.listview);
	}

	@Override
	protected void initData() {
//		NetWorkResponse<List<SubJectData>> netWorkResponse = new NetWorkResponse<List<SubJectData>>(mProgressDialog, mRequestQueue,Method.GET,
//				HttpHelper.SUBJECT_URL, null, null) {
//
//			@Override
//			public void onSuccess(List<SubJectData> backData) {
//				baseListView.setAdapter(new SubjectInfoAdapter(getActivity(), backData,defaultImgLoader));
//			}
//		};
	}
}
