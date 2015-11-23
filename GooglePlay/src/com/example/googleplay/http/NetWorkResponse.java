package com.example.googleplay.http;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.googleplay.R;
import com.example.googleplay.util.FileUtils;
import com.example.volley.AuthFailureError;
import com.example.volley.NetworkError;
import com.example.volley.NetworkResponse;
import com.example.volley.ParseError;
import com.example.volley.RequestQueue;
import com.example.volley.Response;
import com.example.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class NetWorkResponse<E> {
	private ProgressDialog mProgressDialog;
	private Gson mGson = new GsonBuilder().disableHtmlEscaping().create();
	final String URL_TAG = "url";
	final String RESULT_TAG = "result";

	/**
	 * 发出一个请求,返回String,使用自定义的errorListener
	 * 
	 * @param mProgressDialog
	 * @param method
	 * @param url
	 * @param param
	 * @param errorListener
	 */
	// public NetWorkResponse(Context context, RequestQueue requestQueue, int
	// method, final String url, Map<String, String> param,
	// Response.ErrorListener errorListener) {
	// initProgressDialog(context);
	// doStringRequest(context, method, url, param, errorListener);
	// }

	/**
	 * 发出一个请求,返回String,使用默认的的errorListener
	 * 
	 * @param mProgressDialog
	 * @param method
	 * @param url
	 * @param param
	 */
	public NetWorkResponse(Context context, int method, final String url, Map<String, String> param) {
		initProgressDialog(context);
		doStringRequest(context, method, url, param, null);
	}

	public void doStringRequest(final Context context, int method, final String url, final Map<String, String> param,
			Response.ErrorListener errorListener) {
		Log.d(URL_TAG, url);
		if (mProgressDialog != null) {
			mProgressDialog.show();
		}
		if (errorListener == null) {
			errorListener = new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError volleyError) {
					errorResponce(context, url, volleyError);
				}

			};
		}
		StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String backResult) {
				successResponce(context, url, backResult);
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

	// public void doJsonRequest(final ProgressDialog mProgressDialog, int
	// method, final String url, JSONObject param,
	// Response.ErrorListener errorListener) {
	// if (mProgressDialog != null) {
	// mProgressDialog.show();
	// }
	// if (errorListener == null) {
	// errorListener = new Response.ErrorListener() {
	//
	// @Override
	// public void onErrorResponse(VolleyError volleyError) {
	// errorResponce(con, url, volleyError);
	// }
	// };
	// }
	// JsonObjectRequest request = new JsonObjectRequest(method, url, param, new
	// Response.Listener<JSONObject>() {
	//
	// @Override
	// public void onResponse(JSONObject response) {
	// successResponce(mProgressDialog, url, response.toString());
	// }
	// }, errorListener) {
	// @Override
	// protected Response<JSONObject> parseNetworkResponse(NetworkResponse
	// response) {
	// try {
	// JSONObject jsonObject = new JSONObject(new String(response.data,
	// "utf-8"));
	// return Response.success(jsonObject,
	// HttpHeaderParser.parseCacheHeaders(response));
	// } catch (UnsupportedEncodingException e) {
	// return Response.error(new ParseError(e));
	// } catch (JSONException e) {
	// return Response.error(new ParseError(e));
	// }
	//
	// }
	// };
	// NetRequest.getInstance(context.getApplicationContext()).getRequestQueue().add(request);
	// }

	/**
	 * 将服务器返回的数据缓存在本地并解析
	 * 
	 * @param url
	 *            缓存时用url的md5值作为文件名
	 * @param backResult
	 *            服务器返沪结果
	 */
	private void successResponce(Context context, final String url, String backResult) {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
		Log.d(RESULT_TAG, backResult);
		if (!TextUtils.isEmpty(backResult)) {
			// 本地缓存json数据
			FileUtils.saveLocal(backResult, url, context);
		}
		E e = null;
		try {
			e = createClassFromJson(backResult);
		} catch (Exception exception) {
			if (exception != null) {
				System.out.println(exception.getMessage() + "程序异常");
			}
		}
		onSuccess(e);
	}

	/**
	 * 请求服务器失败的处理，从本地加载缓存数据
	 * 
	 * @param url
	 * @param volleyError
	 */
	private void errorResponce(Context context, final String url, VolleyError volleyError) {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
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
				E e = createClassFromJson(json);
				onSuccess(e);
			} catch (Exception e) {
				if (e != null) {
					System.out.println(e.getMessage() + "json解析错误");
				}
			}
		}
	}

	/**
	 * 初始化ProgressDialog
	 * 
	 * @param context
	 */
	public void initProgressDialog(Context context) {
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setMessage("获取数据中...");
	}

	public ProgressDialog getProgressDialog() {
		return mProgressDialog;
	}

	/**
	 * 成功获取服务器返回数据
	 * 
	 * @param backData
	 */
	public abstract void onSuccess(E backData);

	/**
	 * 将字符串解析成实体类
	 * 
	 * @param jsonString
	 * @return
	 */
	public E createClassFromJson(String jsonString) {
		return (E) mGson.fromJson(jsonString, getEType());
	}

	public Type getEType() {
		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = pType.getActualTypeArguments();
		if (types.length > 1) {
			return types[1];
		} else {
			return types[0];
		}
	}

}
