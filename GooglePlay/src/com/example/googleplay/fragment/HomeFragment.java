package com.example.googleplay.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.googleplay.R;

public class HomeFragment extends BaseFragment {
	public static final int STATE_UNKOWN = 0;
	public static final int STATE_LOADING = 1;
	public static final int STATE_ERROR = 2;
	public static final int STATE_EMPTY = 3;
	public static final int STATE_SUCCESS = 4;
	public static int state = STATE_UNKOWN;

	@Override
	protected int getLayoutId() {
		return android.R.layout.simple_expandable_list_item_1;
	}

	@Override
	protected void initView(View view) {
		((TextView) view).setText("HomeFragment");
	}

	@Override
	protected void initData() {

	}

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		if (frameLayout == null) { // ֮ǰ��frameLayout �Ѿ���¼��һ������
//									// ����֮ǰ��ViewPager
//			System.out.println();
//			frameLayout = new FrameLayout(getActivity());
//			init(); // ��FrameLayout�� ���4�ֲ�ͬ�Ľ���
//		} else {
//			// ViewUtils.removeParent(frameLayout);// �Ƴ�frameLayout֮ǰ�ĵ�
//		}
//		show();// ��ݷ���������� �л�״̬
//		// �ȸɵ�֮ǰ�ĵ�
//
//		return frameLayout; // �õ���ǰviewPager ������framelayout
//	}
//
//	private View loadingView;// �����еĽ���
//	private View errorView;// �������
//	private View emptyView;// �ս���
//	private View successView;// ���سɹ��Ľ���
//	private FrameLayout frameLayout;
//
//	// ��FrameLayout�� ��Ӽ��ֲ�ͬ�Ľ���
//	private void init() {
//		// loadingView = createLoadingView(); // �����˼����еĽ���
//		// if (loadingView != null) {
//		// frameLayout.addView(loadingView, new FrameLayout.LayoutParams(
//		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		// }
//		// errorView = createErrorView(); // ���ش������
//		// if (errorView != null) {
//		// frameLayout.addView(errorView, new FrameLayout.LayoutParams(
//		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		// }
//		// emptyView = createEmptyView(); // ���ؿյĽ���
//		// if (emptyView != null) {
//		// frameLayout.addView(emptyView, new FrameLayout.LayoutParams(
//		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//		// }
//		// showPage();// ��ݲ�ͬ��״̬��ʾ��ͬ�Ľ���
//	}
//
//	// ��ݲ�ͬ��״̬��ʾ��ͬ�Ľ���
//	private void showPage() {
//		if (loadingView != null) {
//			loadingView.setVisibility(state == STATE_UNKOWN || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
//		}
//		if (errorView != null) {
//			errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
//		}
//		if (emptyView != null) {
//			emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
//		}
//		if (state == STATE_SUCCESS) {
//			successView = createSuccessView();
//			if (successView != null) {
//				frameLayout.addView(successView, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//				successView.setVisibility(View.VISIBLE);
//			}
//		}
//	}
//
//	private View createSuccessView() {
//		TextView tv = new TextView(getActivity());
//		tv.setText("���سɹ���....");
//		tv.setTextSize(30);
//		return tv;
//	}
//
//	public enum LoadResult {
//		error(2), empty(3), success(4);
//
//		int value;
//
//		LoadResult(int value) {
//			this.value = value;
//		}
//
//		public int getValue() {
//			return value;
//		}
//
//	}
//
//	// ��ݷ���������� �л�״̬
//	private void show() {
//		if (state == STATE_ERROR || state == STATE_EMPTY) {
//			state = STATE_LOADING;
//		}
//		// ��������� ��ȡ����������� �����ж�
//		// ��������� ����һ�����
//		new Thread() {
//			public void run() {
//				SystemClock.sleep(2000);
//				final LoadResult result = load();
//				if (getActivity() != null) {
//					getActivity().runOnUiThread(new Runnable() {
//
//						@Override
//						public void run() {
//							if (result != null) {
//								state = result.getValue();
//								showPage(); // ״̬�ı���,�����жϵ�ǰӦ����ʾ�ĸ�����
//							}
//						}
//					});
//				}
//			};
//		}.start();
//		showPage();
//
//	}
//
//	private LoadResult load() {
//
//		return LoadResult.success;
//	}
//
//	/* �����˿յĽ��� */
//	// private View createEmptyView() {
//	// View view = View.inflate(getActivity(), R.layout.loadpage_empty, null);
//	// return view;
//	// }
//
//	/* �����˴������ */
//	// private View createErrorView() {
//	// View view = View.inflate(getActivity(), R.layout.loadpage_error, null);
//	// Button page_bt = (Button) view.findViewById(R.id.page_bt);
//	// page_bt.setOnClickListener(new OnClickListener() {
//	//
//	// @Override
//	// public void onClick(View v) {
//	// show();
//	// }
//	// });
//	// return view;
//	// }
//
//	// private View createLoadingView() {
//	// View view = View
//	// .inflate(getActivity(), R.layout.loadpage_loading, null);
//	// return view;
//	// }

}
