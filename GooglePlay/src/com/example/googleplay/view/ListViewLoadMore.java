/**
 * 
 */
package com.example.googleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;

/**
 * Description: Company: guanghua
 * 
 * @author qianchao
 */
public class ListViewLoadMore extends BaseListView {

	/**
	 * @param context
	 */
	public ListViewLoadMore(Context context) {
		super(context);
		initView();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ListViewLoadMore(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ListViewLoadMore(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public void initView() {
		setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if (totalItemCount>visibleItemCount) {
//					addFooterView(v);
				}
			}
		});
	}
}
