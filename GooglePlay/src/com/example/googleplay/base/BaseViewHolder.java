package com.example.googleplay.base;

import android.content.Context;
import android.view.View;

public abstract class BaseViewHolder<T> {
	protected Context context;

	public BaseViewHolder(Context context) {
		this.context = context;
	}
	public View getView(){
		View view=View.inflate(context, getLayoutId(), null);
		initView(view);
		return view;
	};
	public abstract int getLayoutId();
	public abstract void initView(View view);
	public abstract void initData(T data);
}
