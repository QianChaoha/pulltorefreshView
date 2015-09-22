package com.example.googleplay.interfaces;



/**
 * Created by QianChao on 2015/8/14.
 */
public interface NetRequestResult<E> {
    void onResponse(E result);
}
