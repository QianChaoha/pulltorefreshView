package com.example.googleplay.fragment;

import com.example.googleplay.base.BaseFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SubjectFragment extends BaseFragment {

	@Override
	protected int getLayoutId() {
		return android.R.layout.simple_expandable_list_item_1;
	}

	@Override
	protected void initView(View view) {
		((TextView)view).setText("SubjectFragment");
	}

	@Override
	protected void initData() {
		
	}
}
