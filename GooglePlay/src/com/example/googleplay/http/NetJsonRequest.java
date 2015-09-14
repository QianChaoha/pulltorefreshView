package com.example.googleplay.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.googleplay.R;
import com.example.googleplay.application.MyApplication;
import com.example.googleplay.http.parser.BackResult;
import com.example.googleplay.interfaces.NetRequestResult;
import com.example.googleplay.util.FileUtils;
import com.example.googleplay.util.Md5Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by QianChao on 2015/8/14.
 */
public class NetJsonRequest<T extends BackResult> {
	Gson mGson = new GsonBuilder().disableHtmlEscaping().create();
	private ProgressDialog mProgressDialog;
	private RequestQueue mRequestQueue;

	public NetJsonRequest(ProgressDialog mProgressDialog) {
		this.mProgressDialog = mProgressDialog;
		this.mRequestQueue = ((MyApplication) mProgressDialog.getContext().getApplicationContext()).getRequestQueue();
	}

	public void netJsonRequest(int method, final String url, JSONObject param, final Class<T> mClass, final NetRequestResult<T> mRequestResult,
			Response.ErrorListener errorListener) {
		mProgressDialog.show();
		if (errorListener == null) {
			// errorListener为null，使用默认的errorListener
			errorListener = new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError volleyError) {
					mProgressDialog.dismiss();
					if (!isConnected(mProgressDialog.getContext())) {
						Toast.makeText(mProgressDialog.getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
					} else if (volleyError != null) {
						System.out.println(volleyError.getMessage() + "");
					}
					// 加载本地数据
					System.out.println("加载缓存数据");
					String json = loadLocal(url);
					if (!TextUtils.isEmpty(json)) {
						T t = mGson.fromJson(json, mClass);
						mRequestResult.onResponse(t);
					}
				}
			};
		}
		JsonObjectRequest request = new JsonObjectRequest(method, url, param, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				mProgressDialog.dismiss();
				String json = response.toString();
				System.out.println("response" + json);
				if (!TextUtils.isEmpty(json)) {
					// 本地缓存json数据
					saveLocal(json, url);
				}
				try {
					T t = mGson.fromJson(json, mClass);
					mRequestResult.onResponse(t);
				} catch (Exception e) {
					if (e != null) {
						System.out.println(e.getMessage() + "");
					}
				}
			}
		}, errorListener) {
			@Override
			protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
				try {
					JSONObject jsonObject = new JSONObject(new String(response.data, "utf-8"));
					return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
				} catch (UnsupportedEncodingException e) {
					return Response.error(new ParseError(e));
				} catch (JSONException e) {
					return Response.error(new ParseError(e));
				}

			}
		};
		mRequestQueue.add(request);
	}

	/**
	 * @param url
	 * @return
	 */
	private String loadLocal(String url) {
		File dir = FileUtils.getCacheDir(mProgressDialog.getContext());// 获取缓存所在的文件夹
		File file = new File(dir, Md5Utils.md5(url));
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String str = null;
			StringBuilder builder = new StringBuilder();
			while ((str = br.readLine()) != null) {
				builder.append(str);
			}
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param json
	 */
	private void saveLocal(String json, String url) {
		BufferedWriter bw = null;
		try {
			File dir = FileUtils.getCacheDir(mProgressDialog.getContext());
			// 在第一行写一个过期时间
			File file = new File(dir, Md5Utils.md5(url)); // /mnt/sdcard/googlePlay/cache/home_0
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(json);// 把整个json文件保存起来
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()) {
			return true;
		}
		return false;
	}
}
