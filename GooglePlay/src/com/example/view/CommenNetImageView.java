package com.example.view;

import android.content.Context;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;
import com.example.googleplay.R;

/**
 * Created by QianChao on 2015/9/2.
 */
public class CommenNetImageView extends NetworkImageView{
    public CommenNetImageView(Context context) {
        super(context);
    }

    public CommenNetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommenNetImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void init(){
        setErrorImageResId(R.drawable.ic_default);
        setDefaultImageResId(R.drawable.ic_default);
    }
}
