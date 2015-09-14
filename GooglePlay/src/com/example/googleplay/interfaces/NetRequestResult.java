package com.example.googleplay.interfaces;


import com.example.googleplay.http.parser.BackResult;

/**
 * Created by QianChao on 2015/8/14.
 */
public interface NetRequestResult<T extends BackResult> {
    void onResponse(T result);
}
