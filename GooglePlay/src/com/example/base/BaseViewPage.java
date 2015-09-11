package com.example.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.application.MyApplication;
import com.example.util.SharePreference;

/**
 * Created by QianChao on 2015/9/2.
 */
public abstract class BaseViewPage {
    protected Activity mActivity;
    protected View mRootView;// 布局对象
    private ProgressDialog mProgressDialog;

    /**
     * 获取自定义Application
     */
    public MyApplication getMyApplication() {
        return (MyApplication) mActivity.getApplication();
    }

    /**
     * 获取请求队列
     */
    public RequestQueue getRequests() {
        return getMyApplication().getRequestQueue();
    }

    /**
     * 获取默认的图片加载器
     */
    public ImageLoader getDefaultImageLoader() {
        return getMyApplication().getDefaultImgLoader();
    }

    /**
     * 配置文件操作
     */
    protected SharePreference spUtil;

    protected RequestQueue requestQueue;

    public BaseViewPage(Activity activity) {
        mActivity = activity;
        mProgressDialog = new ProgressDialog(mActivity, AlertDialog.THEME_HOLO_LIGHT);
        mProgressDialog.setMessage("获取数据中");
        mProgressDialog.setCancelable(false);
        mRootView = View.inflate(mActivity, getLayoutId(), null);
        initViews(mRootView);
        initData();
    }

    /**
     * 初始化布局
     */
    public abstract void initViews(View view);


    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 获取布局Id
     */
    public abstract int getLayoutId();

    public View getmRootView() {
        return mRootView;
    }

}
