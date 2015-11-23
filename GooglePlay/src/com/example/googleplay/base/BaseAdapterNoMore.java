package com.example.googleplay.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.googleplay.http.NetWorkResponseLoadMore;
import com.example.googleplay.util.MoreHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * 具有加载更多的Adapter</br> T list中的实体类 ,E ViewHolder,需要从中获取list<T>用来加载更多
 * 
 * @author qianchao
 */
public abstract class BaseAdapterNoMore<T, E, Q> extends BaseCommenAdapter<T, E> {

	protected final int DEFAULT_ITEM = 0;
	protected final int OTHER_ITEM = 1;

	/**
	 * @param context
	 * @param lists
	 * @param imageLoader
	 */
	public BaseAdapterNoMore(Context context, List<T> lists) {
		super(context, lists);
	}

	@Override
	public int getCount() {
		if (lists != null && lists.size() > 0) {
			return lists.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		if (position < lists.size()) {
			return lists.get(position);
		}
		return null;
	}

	@Override
	public int getItemViewType(int position) { // 20
		if (isOtherItem(lists.get(position))) { // 其他条目
			return OTHER_ITEM;
		}
		return DEFAULT_ITEM; // 如果不是最后一个条目 返回默认类型
	}

	/**
	 * 当前ListView 有几种不同的条目类型
	 */
	@Override
	public int getViewTypeCount() {
		return super.getViewTypeCount() + 1; // 2 有两种不同的类型
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		E e = null;
		Q q = null;
		switch (getItemViewType(position)) {// 判断当前条目时什么类型
		case OTHER_ITEM:
			if (convertView == null) {
				q = getOtherHolder();
				convertView = View.inflate(context, getOtherLayoutId(), null);
				initViewByOtherHolder(q, convertView);
				convertView.setTag(q);
			} else {
				q = (Q) convertView.getTag();
			}
			T dataNoMore = lists.get(position);
			initDataNoMore(q, dataNoMore, position);
			break;

		case DEFAULT_ITEM:
			if (convertView == null) {
				e = getHolder();
				convertView = View.inflate(context, getLayoutId(), null);
				initViewByHolder(e, convertView);
				convertView.setTag(e);
			} else {
				e = (E) convertView.getTag();
			}
			if (position < lists.size()) {
				T data = lists.get(position);
				initData(e, data, position);
			}
			break;
		}
		return convertView;
	}

	/**
	 * @return
	 */
	protected abstract int getOtherLayoutId();

	/**
	 * @param q
	 * @param convertView
	 */
	protected abstract void initViewByOtherHolder(Q holder, View convertView);

	/**
	 * @return
	 */
	protected abstract Q getOtherHolder();

	/**
	 * 另外一种类型的item
	 * 
	 * @param t
	 */
	protected abstract boolean isOtherItem(T data);

	protected abstract void initDataNoMore(Q holder, T data, int position);
}
