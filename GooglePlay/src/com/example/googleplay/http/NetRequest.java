package com.example.googleplay.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.googleplay.util.DiskLruCache;
import com.example.googleplay.util.FileUtils;
import com.example.googleplay.util.Md5Utils;
import com.example.volley.Request;
import com.example.volley.RequestQueue;

/**
 * volley网络框架的使用
 */
public class NetRequest {
	private static NetRequest mInstance;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private static Context mCtx;
	public static final String TAG = "tag";
//	private DiskLruCache mDiskLruCache;

	// 获得应用version号码
	public int getAppVersion(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 1;
	}

	private NetRequest(Context context) {
		mCtx = context;
		mRequestQueue = getRequestQueue();
		mImageLoader = getImageLoader();
//		try {
//			mDiskLruCache = DiskLruCache.open(FileUtils.getCacheDir(mCtx), getAppVersion(mCtx), 1, 10 * 1024 * 1024);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * 
	 * @param context
	 *            使用getApplicationContext,否则会产生内存泄漏
	 * @return
	 */
	public static synchronized NetRequest getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new NetRequest(context);
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			// getApplicationContext()是关键, 它会避免
			// Activity或者BroadcastReceiver带来的缺点.
			mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

	public ImageLoader getImageLoader() {
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
				@Override
				public void putBitmap(String url, Bitmap bitmap) {
					imageCache.put(url, bitmap);
//					String key = Md5Utils.md5(url);
//					try {
//						if (null == mDiskLruCache.get(key)) {
//							DiskLruCache.Editor editor = mDiskLruCache.edit(key);
//							if (editor != null) {
//								OutputStream outputStream = editor.newOutputStream(0);
//								if (bitmap.compress(CompressFormat.JPEG, 100, outputStream)) {
//									editor.commit();
//								} else {
//									editor.abort();
//								}
//							}
//							mDiskLruCache.flush();
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
				}

				@Override
				public Bitmap getBitmap(String url) {
					Bitmap img = imageCache.get(url);
					Log.d("LruCache", "get:" + url + "  \r\ncache:" + (img != null));
//					if (img == null) {
//						String key = Md5Utils.md5(url);
//						try {
//							if (mDiskLruCache.get(key) == null) {
//								return null;
//							} else {
//								DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
//								Bitmap bitmap = null;
//								if (snapShot != null) {
//									InputStream is = snapShot.getInputStream(0);
//									bitmap = BitmapFactory.decodeStream(is);
//								}
//								return bitmap;
//							}
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					} else {
						return img;
//					}
//					return null;
				}
			});
		}
		return mImageLoader;
	}

	/**
	 * Volley用于全局的图片内存缓存
	 */
	protected final LruCache<String, Bitmap> imageCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 1024 / 8)) {
		@Override
		protected int sizeOf(String key, Bitmap bitmap) {
			// 重写此方法来衡量每张图片的大小，默认返回图片数量。
			int size = 0;
			size = bitmap.getRowBytes() * bitmap.getHeight() / 1024;
			Log.d("LruCache", size + "/" + imageCache.maxSize() + "/" + imageCache.size() + " maxsize:" + Runtime.getRuntime().maxMemory() / 1024 / 8);
			return size;
		}

	};
	// public void cancleAll(){
	// if (mRequestQueue!=null) {
	// mRequestQueue.cancelAll(con)
	// }
	// }
}
