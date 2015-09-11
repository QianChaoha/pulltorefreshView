package com.example.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by QianChao on 2015/8/17.
 */
public class MyApplication extends Application {
    /**
     * Volley的请求队列
     */
    protected RequestQueue mRequestQueue;
    /**
     * Volley的图片加载器
     */
    protected ImageLoader defaultImgLoader;

    /**
     * Volley获取用于网络请求的队列
     */
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * Volley获取用于加载图片的加载器
     */
    public ImageLoader getDefaultImgLoader() {
        return defaultImgLoader;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Volley通讯
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        // 加载默认的ImageLoader
        defaultImgLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
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

    /**
     * Volley用于全局的图片内存缓存
     */
    protected final LruCache<String, Bitmap> imageCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime()
            .maxMemory() / 1024 / 8)) {
        @SuppressLint("NewApi")
		@Override
        protected int sizeOf(String key, Bitmap bitmap) {
            // 重写此方法来衡量每张图片的大小，默认返回图片数量。
            int size = 0;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
                size = bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            } else {
                size = bitmap.getByteCount() / 1024;
            }
            Log.d("LruCache", size + "/" + imageCache.maxSize() + "/" + imageCache.size() + " maxsize:"
                    + Runtime.getRuntime().maxMemory() / 1024 / 8);
            return size;
        }

    };
    /**
     * Volley默认的错误处理程序
     */
    protected Response.ErrorListener defaultErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            if (volleyError instanceof NetworkError) {
                Toast.makeText(getApplicationContext(), "您的网络连接不稳定", Toast.LENGTH_SHORT).show();
            }
            if (volleyError != null) {
                volleyError.printStackTrace();
                volleyError.toString();
                System.out.println("```error" + volleyError.toString());
            }
        }

    };
}
