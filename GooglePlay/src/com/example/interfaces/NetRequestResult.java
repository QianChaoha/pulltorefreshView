package com.example.interfaces;


import com.example.http.parser.BackResult;

/**
 * Created by QianChao on 2015/8/14.
 */
public interface NetRequestResult<T extends BackResult> {
    void onResponse(T result);
}
