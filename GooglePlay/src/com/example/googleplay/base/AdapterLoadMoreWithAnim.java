package com.example.googleplay.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.googleplay.util.MoreHolder;

/**
 * 具有加载更多，子控件带有动画的Adapter</br> T list中的实体类 ,E ViewHolder,需要从中获取list<T>用来加载更多
 * 
 * @author qianchao
 */
public abstract class AdapterLoadMoreWithAnim<T, E> extends BaseAdapterWithLoadMore<T, E> {
	/**
	 * @param context
	 * @param lists
	 * @param imageLoader
	 * @param requestQueue
	 */
	public AdapterLoadMoreWithAnim(Context context, List<T> lists) {
		super(context, lists);
		InitAnima();
		isFrist = new HashMap<Integer, Boolean>();
	}

	private TranslateAnimation taLeft, taRight, taTop, taBlow;
	private TranslateAnimation[] animations = null;
	private final int time = 2000;
	private Random ran = new Random();
	private Map<Integer, Boolean> isFrist;



	private void InitAnima() {
		// TODO Auto-generated method stub
		taLeft = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		taRight = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		taTop = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		taBlow = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		taLeft.setDuration(time);
		taRight.setDuration(time);
		taTop.setDuration(time);
		taBlow.setDuration(time);
		animations = new TranslateAnimation[] { taLeft };
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
			// 如果是第一次加载该view，则使用动画
//			if (isFrist.get(position) == null || isFrist.get(position)) {
//				int rand = ran.nextInt(animations.length);
//				isFrist.put(position, false);
//				convertView.startAnimation(animations[rand]);
//			}
			break;
		}

		return convertView;
	}
}
