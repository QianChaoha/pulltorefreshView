package com.example.googleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by QianChao on 2015/9/7.
 */
public class TouchLazyViewPager extends LazyViewPager {
    public TouchLazyViewPager(Context context) {
        super(context);
    }

    public TouchLazyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (getCurrentItem()==2){
            return false;
        }else {
            return super.onTouchEvent(arg0);
        }

    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (getCurrentItem()==2){
            return false;
        }else {
            return super.onInterceptTouchEvent(arg0);
        }
    }
}
