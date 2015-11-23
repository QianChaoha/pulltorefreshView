package com.example.googleplay.fragment;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import android.view.View;

import com.example.googleplay.R;
import com.example.googleplay.adapter.SubjectInfoAdapter;
import com.example.googleplay.base.BaseFragment;
import com.example.googleplay.base.BaseListView;
import com.example.googleplay.data.SubJectData;
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.NetRequest;
import com.example.googleplay.http.NetWorkResponse;
import com.example.volley.Request.Method;
import com.google.gson.Gson;

public class SubjectFragment extends BaseFragment {
	private BaseListView baseListView;
	private int index = 0;

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
		 NetWorkResponse<List<SubJectData>> netWorkResponse = new NetWorkResponse<List<SubJectData>>(getActivity(), Method.GET,
				HttpHelper.SUBJECT_URL+index, null) {

			@Override
			public void onSuccess(List<SubJectData> backData) {
				baseListView.setAdapter(new SubjectInfoAdapter<List<SubJectData>>(getActivity(), backData) {

					@Override
					protected String getUrl() {
						return HttpHelper.SUBJECT_URL+index;
					}

					@Override
					protected int getMethod() {
						return Method.GET;
					}

					@Override
					protected Map<String, String> getParam() {
						return null;
					}

					@Override
					protected List<SubJectData> onLoadMore(Gson mGson, String data) {
						return null;
					}

					@Override
					protected void size(int currentSize, int totalSize) {
						index=totalSize;
					}

				});
			}
		};
	}
}
