package com.example.googleplay.util;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.googleplay.R;
import com.example.googleplay.base.BaseAdapterWithLoadMore;

public class MoreHolder {
	public final static int HAS_NO_MORE = 0; // 没有额外数据了
	public final static int LOAD_ERROR = 1;// 加载失败
	public final static int HAS_MORE = 2;// 有额外数据

	private Context context;
	private RelativeLayout rl_more_loading, rl_more_error;
	private BaseAdapterWithLoadMore adapter;
	private View view;

	public MoreHolder(Context context, BaseAdapterWithLoadMore adapter) {
		this.context = context;
		this.adapter = adapter;
		view = View.inflate(context, R.layout.load_more, null);
		rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
		rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
	}

	/**
	 * 返回与MoreHolder关联的ContentView，并开始进行加载更多操作
	 * 
	 * @return
	 */
	public View getContentView() {
		adapter.loadMore(this);
		return view;
	}

	/**
	 * 根据数据做界面的修改
	 * 
	 * @param data
	 */
	public void setData(int data) {
		rl_more_error.setVisibility(data == LOAD_ERROR ? View.VISIBLE : View.GONE);
		rl_more_loading.setVisibility(data == HAS_MORE ? View.VISIBLE : View.GONE);
	}
}
