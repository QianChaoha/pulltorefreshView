package com.example.googleplay.base;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.example.googleplay.http.NetWorkResponseLoadMore;
import com.example.googleplay.util.MoreHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 具有加载更多的Adapter</br> T list中的实体类 ,E ViewHolder,Q服务器返回数据，需要从中获取list<T>用来加载更多
 * 
 * @author qianchao
 */
public abstract class BaseAdapterWithLoadMore<T, E> extends BaseCommenAdapter<T, E> {

	private final int DEFAULT_ITEM = 0;
	private final int MORE_ITEM = 1;
	private Gson mGson = new GsonBuilder().disableHtmlEscaping().create();

	/**
	 * @param context
	 * @param lists
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
				System.out.println("null");
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
	 * @param moreHolder 
	 */
	public void loadMore(final MoreHolder moreHolder) {
		NetWorkResponseLoadMore loadMore = new NetWorkResponseLoadMore(context, getMethod(), getUrl(), getParam(), null) {

			@Override
			public void onSuccess(String backData) {
				System.out.println("moreHolder==="+(moreHolder!=null));
				List<T> moreData = onLoadMore(mGson,backData);
				if (moreData == null) {
					moreHolder.setData(MoreHolder.LOAD_ERROR);
				} else if (moreData.size() == 0) {
					moreHolder.setData(MoreHolder.HAS_NO_MORE);
				} else {
					// 成功获取数据
					try {
						moreHolder.setData(MoreHolder.HAS_MORE);
					} catch (Exception e) {
						e.printStackTrace();
					}
					lists.addAll(moreData);// 给listView之前的集合添加一个新的集合
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
	 * 子类通过这个方法将服务器返回的数据处理，返回加载更多的数据
	 * 
	 * @param backData
	 * @return
	 */
	protected abstract List<T> onLoadMore(Gson mGson,String data);

}
