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
public abstract class BaseAdapterWithLoadMore<T, E, Q> extends BaseCommenAdapter<T, E> {

	protected final int DEFAULT_ITEM = 0;
	protected final int MORE_ITEM = 1;
	protected Gson mGson = new GsonBuilder().disableHtmlEscaping().create();

	/**
	 * @param context
	 * @param lists
	 * @param imageLoader
	 */
	public BaseAdapterWithLoadMore(Context context, List<T> lists) {
		super(context, lists);
	}

	@Override
	public int getCount() {
		if (lists != null && lists.size() > 0) {
			return lists.size() + 1;
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
		if (position == lists.size()) { // 当前是最后一个条目
			return MORE_ITEM;
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
		MoreHolder moreHolder = null;
		switch (getItemViewType(position)) {// 判断当前条目时什么类型
		case MORE_ITEM:
			if (convertView == null) {
				moreHolder = new MoreHolder(context, this);
				convertView = moreHolder.getContentView();
				convertView.setTag(moreHolder);
			} else {
				moreHolder = (MoreHolder) convertView.getTag();
				moreHolder.getContentView();
			}
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
	 * 加载更多数据
	 * 
	 * @param moreHolder
	 */
	public void loadMore(final MoreHolder moreHolder) {
		NetWorkResponseLoadMore loadMore = new NetWorkResponseLoadMore(context, getMethod(), getUrl(), getParam(), null) {

			@Override
			public void onSuccess(String backData) {
				List<T> moreData = null;
				if (onLoadMore(mGson, backData) != null) {
					moreData = onLoadMore(mGson, backData);
				} else {
					try {
						moreData = mGson.fromJson(backData, getEType());
					} catch (JsonSyntaxException e) {
						System.out.println("解析泛型失败");
						e.printStackTrace();
					}
				}
				if (moreData == null) {
					moreHolder.setData(MoreHolder.LOAD_ERROR);
					size(0, lists.size());
				} else if (moreData.size() == 0) {
					moreHolder.setData(MoreHolder.HAS_NO_MORE);
					size(0, lists.size());
				} else {
					// 成功获取数据
					try {
						moreHolder.setData(MoreHolder.HAS_MORE);
					} catch (Exception e) {
						e.printStackTrace();
					}
					lists.addAll(moreData);// 给listView之前的集合添加一个新的集合
					size(moreData.size(), lists.size());
					notifyDataSetChanged();// 刷新界面
				}
			}
		};
	}

	/**
	 * 子类通过这个方法返回请求服务器时需要的url
	 * 
	 * @return
	 */
	protected abstract String getUrl();

	/**
	 * 子类通过这个方法返回请求服务器时的请求方式
	 * 
	 * @return
	 */
	protected abstract int getMethod();

	/**
	 * 子类通过这个方法返回请求服务器时的参数
	 * 
	 * @return
	 */
	protected abstract Map<String, String> getParam();

	/**
	 * 返回本次连接服务器获取的数据size和数据的总size
	 * 
	 * @param currentSize
	 * @param totalSize
	 * @return
	 */
	protected abstract void size(int currentSize, int totalSize);

	/**
	 * 子类通过这个方法将服务器返回的数据处理，返回加载更多的数据,也可以返回空值，那么需要添加泛型
	 * 
	 * @param backData
	 * @return
	 */
	protected abstract List<T> onLoadMore(Gson mGson, String data);

	public Type getEType() {
		System.out.println(this.getClass());
		System.out.println(this.getClass().getGenericSuperclass());
		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = pType.getActualTypeArguments();
		if (types.length > 1) {
			return types[types.length - 1];
		} else {
			return types[0];
		}
	}

}
