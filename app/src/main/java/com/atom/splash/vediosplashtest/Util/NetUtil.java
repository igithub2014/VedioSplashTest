package com.atom.splash.vediosplashtest.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by atom on 2017/2/28.
 */

public class NetUtil {

    public  enum netType{
        WIFI,CMNET,CMWAP,NONENET
    }

    /**
     * 判断网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connMgr.getAllNetworkInfo();
       if(null != networkInfos){
           for (int i = 0; i < networkInfos.length; i++){
               if(networkInfos[i].getState() == NetworkInfo.State.CONNECTED){
                    return true;
               }
           }
       }
        return false;
    }

    /**
     * 判断是否有网络连接
     * @param context
     * @return
     */
    public static boolean isNetworkConneted(Context context){
        if(null != context){
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if(null != networkInfo){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断Wifi网络是否可用
     * @param context
     * @return
     */
    public static boolean isWIFIConnected(Context context){
        if(null != context){
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(null != networkInfo){
                return networkInfo.isAvailable();
            }
        }
        return  false;
    }

    /**
     *  判断Mobile网络是否可用
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context){
        if(null != context){
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(null != networkInfo){
                return  networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取当前网络类型
     * @param context
     * @return
     */
    public static int getConnectedType(Context context){
        if(null != context){
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if(null != networkInfo && networkInfo.isAvailable()){
                return networkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * 获取网络类型
     * @param context
     * @return
     */
    public static netType getAppNetType(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(null == networkInfo){ // 无网络状态
            return netType.NONENET;
        }
        int nType = networkInfo.getType();
        if(nType == ConnectivityManager.TYPE_MOBILE){
            if("cment".equals(networkInfo.getExtraInfo().toLowerCase())){
                return netType.CMNET;

            }else{
                return netType.CMWAP;
            }
        }else if(nType == ConnectivityManager.TYPE_WIFI){
            return  netType.WIFI;
        }
        return netType.NONENET;
    }
}
