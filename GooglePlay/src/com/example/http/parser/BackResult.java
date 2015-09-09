package com.example.http.parser;


/**
 * 用于定义返回结果集中共有的元素，比如result，code....之类
 */
public class BackResult<E> {

    private String success;
    private Error error;


    /* (non-Javadoc)
     * @see com.alipear.serverrequest.item.BaseResult#isSuccess()
     */
    public boolean isSuccess() {
        return success!= null && success.compareTo("true") ==0;
    }

    public String getError() {
        return error.getMessage();
    }


    public String getResponse() {
        // TODO Auto-generated method stub
        return null;
    }

}
