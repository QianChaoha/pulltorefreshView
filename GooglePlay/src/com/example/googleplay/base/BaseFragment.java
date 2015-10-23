package com.example.googleplay.base;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.googleplay.http.NetWorkResponse;
import com.example.googleplay.util.SharePreference;

public abstract class BaseFragment extends Fragment {

	protected NetWorkResponse netWorkResponse;

	/**
	 * 配置文件操作
	 */
	protected SharePreference spUtil;
	protected View mRootView;
	/**
	 * 网络请求参数
	 */
	protected JSONObject jsonParam = new JSONObject();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mRootView == null) {
			mRootView = inflater.inflate(getLayoutId(), null);
			initView(mRootView);
		}else {
			if (mRootView.getParent() instanceof ViewGroup) {
				((ViewGroup)mRootView.getParent()).removeAllViews();
			}
		}
		return mRootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}

	/**
	 * 布局ID
	 * 
	 * @return layoutID
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化布局
	 */
	protected abstract void initView(View view);

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * Toast提醒
	 */
	protected void showToast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}

	protected void showToast(int resId) {
		Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 当解除与activity关联时 解决fragment嵌套fragment出现的问题：no activity
	 */
	// @Override
	// public void onDetach() {
	// super.onDetach();
	// try {
	// // 参数是固定写法
	// Field childFragmentManager =
	// Fragment.class.getDeclaredField("mChildFragmentManager");
	// childFragmentManager.setAccessible(true);
	// childFragmentManager.set(this, null);
	// } catch (NoSuchFieldException e) {
	// throw new RuntimeException(e);
	// } catch (IllegalAccessException e) {
	// throw new RuntimeException(e);
	// }
	// }

	protected View getRootView() {
		return mRootView;
	}

	@Override
	public void onStop() {
		super.onStop();
		if (netWorkResponse != null && netWorkResponse.getProgressDialog() != null) {
			netWorkResponse.getProgressDialog().dismiss();
		}
//		NetRequest.getInstance(getActivity().getApplicationContext()).getRequestQueue().cancelAll(getActivity());
	}
}
