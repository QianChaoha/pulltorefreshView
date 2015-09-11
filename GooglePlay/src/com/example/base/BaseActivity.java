package com.example.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.example.application.MyApplication;
import com.example.http.NetJsonRequest;
import com.example.util.SharePreference;

public abstract class BaseActivity extends FragmentActivity  {
    private ProgressDialog mProgressDialog;

    /**
     * 获取自定义Application
     */
    public MyApplication getMyApplication() {
        return (MyApplication) getApplication();
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
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("获取数据中");
        mProgressDialog.setCancelable(false);

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


    @Override
    public void finish() {
        super.finish();
        //取消这个队列里的所有请求
        getRequests().cancelAll(this);
        mProgressDialog.dismiss();
    }
}
