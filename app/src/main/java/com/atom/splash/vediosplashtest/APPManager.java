package com.atom.splash.vediosplashtest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Activity 管理类
 * 用于管理App所有Activity类
 * 包括增删App退出
 *
 * Created by atom on 2016/10/18.
 */

public class APPManager {

    // 定义ac栈对象
    private static Stack<Activity> activityStack ;
    // 创建单例模式用于保证App运行中只创建一个类管理器
    private static APPManager manager;
    public static APPManager getManager(){
        synchronized (APPManager.class){
            if(null == manager){
                manager = new APPManager();
            }
        }
        return manager;
    }

    /**
     * 添加activity 到堆栈
     * @param activity
     */
    public void addActivity(Activity activity){

        if(null == activityStack){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前栈顶activity
     * @return currentActivity (当前栈顶Activity)
     */
    public Activity  getCurrentActivity(){
        Activity currentActivity = activityStack.lastElement();
        return currentActivity;
    }

    /**
     * 结束当前栈顶activity
     */
    public void finishActivity(){
        Activity currentActivity = activityStack.lastElement();
        finishActivity(currentActivity);
    }

    /**
     * 结束指定activity
     * @param activity
     */
    public void finishActivity(Activity activity){

        if(null != activity){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 移除指定activity
     * @param activity
     */
    public void removeActivity(Activity activity){
        if(null != activity){
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     *  结束App中所有的Activity
     */
    public void finishAllActivity(){
        for(Activity ac : activityStack){
            if(null != ac){
                ac.finish();
            }
        }
        activityStack.clear();
    }

    /**
     *  退出程序结束所有activity
     * @param context
     * @param isBackground
     */
    public void AppExit(Context context,Boolean isBackground){

        try{
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 如果有后台操作，请不要支持此语句
            if(!isBackground){
                System.exit(0);
            }
        }
    }
}
