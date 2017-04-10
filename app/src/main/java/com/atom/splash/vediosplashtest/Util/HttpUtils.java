package com.atom.splash.vediosplashtest.Util;


/**
 * 网络请求类
 * Created by atom on 2017/3/6.
 */

public class HttpUtils {
    private static HttpUtils httpUtils;
     public  static HttpUtils getInstance(){
       return httpUtils != null ? httpUtils : new HttpUtils();
    }

}
