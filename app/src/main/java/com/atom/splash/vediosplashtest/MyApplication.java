package com.atom.splash.vediosplashtest;

import android.app.Application;
import android.content.Context;

import com.atom.splash.vediosplashtest.exception.CrashException;

import java.util.HashMap;


/**
 * Created by atom on 2016/7/29.
 */
public class MyApplication extends Application {

    // 用来存放登录信息
    private HashMap<String ,Object> sharedPreferenceInfos;
    private static MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        // 注册异常处理
        CrashException.getInstance().init(getApplicationContext());
    }

    public static Context getAppContext(){
        return application;
    }
    public HashMap<String, Object> getSharedPreferenceInfos() {
        return sharedPreferenceInfos;
    }

    public void setSharedPreferenceInfos(HashMap<String, Object> sharedPreferenceInfos) {
        this.sharedPreferenceInfos = sharedPreferenceInfos;
    }
}
