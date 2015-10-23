package com.example.googleplay.http;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.googleplay.R;
import com.example.googleplay.util.FileUtils;
import com.example.volley.AuthFailureError;
import com.example.volley.NetworkError;
import com.example.volley.NetworkResponse;
import com.example.volley.ParseError;
import com.example.volley.Response;
import com.example.volley.VolleyError;

public abstract class NetWorkResponseLoadMore {
	private Context context;

	/**
	 * 加载更多时候的请求方法
	 * 
	 * @param mProgressDialog
	 * @param method
	 * @param url
	 * @param param
	 * @param clazz
	 * @param errorListener
	 */
	public NetWorkResponseLoadMore(final Context context, int method, final String url, final Map<String, String> param,
			Response.ErrorListener errorListener) {
		System.out.println("url" + url);
		this.context = context;
		if (errorListener == null) {
			errorListener = new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError volleyError) {
					errorResponce(url, volleyError);
				}
			};
		}
		StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String backResult) {
				successResponse(context, url, backResult);
			}

		}, errorListener) {
			@Override
			protected Response<String> parseNetworkResponse(NetworkResponse response) {
				try {
					return Response.success(new String(response.data, "utf-8"), HttpHeaderParser.parseCacheHeaders(response));
				} catch (UnsupportedEncodingException e) {
					return Response.error(new ParseError(e));
				}

			}

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				if (param != null) {
					return param;
				}
				return super.getParams();
			}
		};
		request.setTag(NetRequest.TAG);

		NetRequest.getInstance(context.getApplicationContext()).getRequestQueue().add(request);
	}

	private void successResponse(final Context context, final String url, String backResult) {
		// System.out.println(backResult);
		if (!TextUtils.isEmpty(backResult)) {
			// 本地缓存json数据
			FileUtils.saveLocal(backResult, url, context);
		}
		onSuccess(backResult);
	}

	/**
	 * 请求服务器失败的处理，从本地加载缓存数据
	 * 
	 * @param url
	 * @param volleyError
	 */
	private void errorResponce(final String url, VolleyError volleyError) {
		if (volleyError instanceof NetworkError) {
			Toast.makeText(context, R.string.network_error, Toast.LENGTH_SHORT).show();
		} else if (volleyError != null) {
			System.out.println(volleyError.getMessage() + "服务器异常");
		}
		// 加载本地数据
		System.out.println("加载缓存数据");
		String json = FileUtils.loadLocal(url, context);
		if (!TextUtils.isEmpty(json)) {
			try {
				onSuccess(json);
			} catch (Exception e) {
				if (e != null) {
					System.out.println(e.getMessage() + "程序异常");
				}
			}
		}
	}

	/**
	 * 成功获取服务器返回数据
	 * 
	 * @param backData
	 */
	public abstract void onSuccess(String backData);
}
