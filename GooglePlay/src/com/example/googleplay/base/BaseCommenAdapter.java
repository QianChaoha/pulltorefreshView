package com.example.googleplay.base;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.example.googleplay.application.MyApplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * T list中的实体类
 * E ViewHolder
 *@author qianchao
 */
public abstract class BaseCommenAdapter<T, E> extends BaseAdapter {
	protected List<T> lists;
	protected Context context;

	/**
	 * @param backResult
	 * @param context
	 */
	public BaseCommenAdapter(Context context, List<T> lists) {
		this.lists = lists;
		this.context = context;
	}

	/**
	 * 获取ImgLoader
	 * 
	 * @return
	 */
	protected ImageLoader getImageLoader() {
		return ((MyApplication) context.getApplicationContext()).getDefaultImgLoader();
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
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		E e = null;
		if (convertView == null) {
			e = getHolder();
			convertView = View.inflate(context, getLayoutId(), null);
			initViewByHolder(e, convertView);
			convertView.setTag(e);
		} else {
			e = (E) convertView.getTag();
		}
		T data = lists.get(position);
		initData(e, data, position);
		return convertView;
	}
	/**
	 * 获取一个ViewHolder
	 * 
	 * @return
	 */
	protected abstract E getHolder();

	/**
	 * 对ViewHolder里的成员进行findViewById()操作
	 * 
	 * @param baseViewHolder
	 * @param convertView
	 * @return
	 */
	protected abstract void initViewByHolder(E holder, View convertView);

	/**
	 * 用list中的实体类完成对ViewHolder成员的初始化操作
	 * 
	 * @param baseViewHolder
	 * @param lists
	 * @param position
	 */
	protected abstract void initData(E holder, T data, int position);

	/**
	 * 实例化convertView时传入的layoutId
	 * 
	 * @return
	 */
	protected abstract int getLayoutId();

}
