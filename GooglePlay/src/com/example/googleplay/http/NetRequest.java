package com.example.googleplay.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * volley网络框架的使用
 */
public class NetRequest {
	private static NetRequest mInstance;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private static Context mCtx;

	private NetRequest(Context context) {
		mCtx = context.getApplicationContext();
		mRequestQueue = getRequestQueue();
		mImageLoader = getImageLoader();
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
			mRequestQueue = Volley.newRequestQueue(mCtx);
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
					Log.d("LruCache", "put:" + url);
					imageCache.put(url, bitmap);
				}

				@Override
				public Bitmap getBitmap(String url) {
					Bitmap img = imageCache.get(url);
					Log.d("LruCache", "get:" + url + "  \r\ncache:" + (img != null));
					return img;
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
}
