package com.example.googleplay.fragment;

import android.view.View;

import com.example.googleplay.R;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.base.BaseListView;

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
