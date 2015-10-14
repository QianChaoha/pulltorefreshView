package com.example.googleplay.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;
import com.example.googleplay.http.NetRequest;
import com.example.googleplay.http.NetWorkResponse;
import com.example.googleplay.util.SharePreference;

public abstract class BaseActivity extends ActionBarActivity {
	protected NetWorkResponse netWorkResponse;
	/**
	 * 配置文件操作
	 */
	protected SharePreference spUtil;

	/**
	 * 布局ID
	 * 
	 * @return layoutID
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化布局
	 */
	protected abstract void initView();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("onCreate");
		spUtil = new SharePreference(this, "config");
		setContentView(getLayoutId());
		initView();
		initData();
	}

	/**
	 * Toast提醒
	 */
	protected void showToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Volley用于全局的图片内存缓存
	 */
	// protected final LruCache<String, Bitmap> imageCache = new
	// LruCache<String, Bitmap>((int) (Runtime.getRuntime()
	// .maxMemory() / 1024 / 8)) {
	// @SuppressLint("NewApi")
	// @Override
	// protected int sizeOf(String key, Bitmap bitmap) {
	// // 重写此方法来衡量每张图片的大小，默认返回图片数量。
	// int size = 0;
	// if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
	// size = bitmap.getRowBytes() * bitmap.getHeight() / 1024;
	// } else {
	// size = bitmap.getByteCount() / 1024;
	// }
	// Log.d("LruCache", size + "/" + imageCache.maxSize() + "/" +
	// imageCache.size() + " maxsize:"
	// + Runtime.getRuntime().maxMemory() / 1024 / 8);
	// return size;
	// }
	//
	// };
	@Override
	protected void onStop() {
		super.onStop();
		if (netWorkResponse != null && netWorkResponse.getProgressDialog() != null) {
			netWorkResponse.getProgressDialog().dismiss();
		}
		NetRequest.getInstance(getApplicationContext()).getRequestQueue().cancelAll(this);
	}
}
