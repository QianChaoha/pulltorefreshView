package com.example.googleplay.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
    /**
     * volley网络框架的使用
     */
    public class NetRequest extends StringRequest {
        private String mCmd;
        public NetRequest(String url, String cmd, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(Method.POST, url, listener, errorListener);
            this.mCmd = cmd;
        }
        public NetRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(Method.GET, url, listener, errorListener);
            this.mCmd = null;
        }
        @Override
        public byte[] getBody() throws AuthFailureError {
            if (mCmd != null)
                return mCmd.getBytes();
            return super.getBody();
        }
        @Override
        public String getBodyContentType() {
            if (mCmd != null)
                return "multipart/form-data";
            return super.getBodyContentType();

        }
}
