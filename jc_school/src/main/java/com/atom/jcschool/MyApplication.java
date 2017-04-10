package com.atom.jcschool;

import android.app.Application;

import com.atom.jcschool.exception.CrashException;

import java.util.HashMap;

/**
 * Created by User on 2017/3/27.
 */

public class MyApplication extends Application {

    // 存放登录信息
    private HashMap<String, Object> sharePreferenceInfos;
    //创建MyApplication对象
    private static MyApplication application;
    // 获取实例
    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // 异常处理
        CrashException.getInstance().init(getApplicationContext());
    }

    public HashMap<String, Object> getSharePreferenceInfos() {
        return sharePreferenceInfos;
    }

    public void setSharePreferenceInfos(HashMap<String, Object> sharePreferenceInfos) {
        this.sharePreferenceInfos = sharePreferenceInfos;
    }
}
