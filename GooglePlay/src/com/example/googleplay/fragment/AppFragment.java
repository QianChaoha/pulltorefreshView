package com.example.googleplay.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.base.BaseFragment;

public class AppFragment extends BaseFragment {

	@Override
	protected int getLayoutId() {
		return android.R.layout.simple_expandable_list_item_1;
	}

	@Override
	protected void initView(View view) {
		((TextView)view).setText("AppFragment");
	}

	@Override
	protected void initData() {
		
	}
}
