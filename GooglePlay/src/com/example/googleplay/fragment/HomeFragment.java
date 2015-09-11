package com.example.googleplay.fragment;

import org.json.JSONException;

import android.view.View;
import android.widget.ListView;

import com.android.volley.Request.Method;
import com.example.base.BaseFragment;
import com.example.data.HomeData;
import com.example.googleplay.R;
import com.example.http.HttpHelper;
import com.example.http.NetJsonRequest;
import com.example.interfaces.NetRequestResult;

public class HomeFragment extends BaseFragment {
	private ListView listview;

	@Override
	protected int getLayoutId() {
		return R.layout.homefragment_layout;
	}

	@Override
	protected void initView(View view) {
		listview=(ListView) view.findViewById(R.id.listview);
		try {
			jsonParam.put("index", 0);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		NetJsonRequest<HomeData> netJsonRequest=new NetJsonRequest<HomeData>(mProgressDialog);
		netJsonRequest.netJsonRequest(Method.GET, HttpHelper.HOME_URL, jsonParam, HomeData.class,new NetRequestResult<HomeData>() {
			
			@Override
			public void onResponse(HomeData result) {
				  				
			}
		},null);
	}

	@Override
	protected void initData() {
		
	}
}
