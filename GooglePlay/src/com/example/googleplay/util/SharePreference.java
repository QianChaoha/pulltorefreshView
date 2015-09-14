package com.example.googleplay.util;


import android.content.Context;
import android.content.SharedPreferences;
/**
 *配置文件缓存类
 * 使用者根据应用需求仿照示例增加相应的方法即可。
 * */
public class SharePreference {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreference(Context context, String file) {
        sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    public void clean() {
        editor.clear().commit();
    }

    /**
     * 示例代码，首次登陆
     * */
    public int getFristLogin() {
        return sp.getInt("fristLogin", 0);
    }

    public void setFristLogin(int port) {
        editor.putInt("fristLogin", port);
        editor.commit();
    }

}
