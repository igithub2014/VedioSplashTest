package com.atom.splash.vediosplashtest.exception;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;

import com.atom.splash.vediosplashtest.R;
import com.atom.splash.vediosplashtest.Util.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常捕获类
 * Created by atom on 2017/3/1.
 */

public class CrashException implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashException";
    // 创建单利模式，保证唯一性
    private static CrashException crashException;
    public synchronized static CrashException getInstance(){
     if(null == crashException){
             crashException = new CrashException();
     }
        return  crashException;
    }
    // 系统默认UncaughtExceptionHandler 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // 上下文环境
    private Context mContext;
    // 存儲设备信息和异常信息
    private HashMap<String,String> infos = new HashMap<String,String>();
     // 格式化时间用于日志记录的一部分
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 初始化
     * @param context
     */
    public void init(Context context){
        mContext = context;
        // 获取系统异常处理类对象
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置CrashException为默认异常处理类
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     *  出现异常处理
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!handleException(ex) && mDefaultHandler != null){
            //用户没有处理情况，调用系统自带异常处理
            mDefaultHandler.uncaughtException(thread,ex);
        } else{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     *自定义异常处理，并收集错误信息方法
     * @param ex
     * @return
     */
    private boolean handleException(Throwable ex ){
        if(null == ex){
            return  false;
        }
        // 使用Toast来显示异常处理信息
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                ToastUtil.show(mContext, R.string.crash_toast_txt,400);
                Looper.loop();
            }
        }.start();
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存日志文件
        saveCrashInfoToFile(ex);
        // TODO 上传错误信息到后台
        return true;
    }

    /**
     * 收集错误信息写入手机指定SD卡目录
     * @param ex
     * @return
     */
    private String saveCrashInfoToFile(Throwable ex) {
        // 创建缓存区
        StringBuffer buffer = new StringBuffer();
        // 遍历hashMap 获取版本信息
       for(Map.Entry<String,String> entry : infos.entrySet()){
           String key = entry.getKey();
           String value = entry.getValue();
           // 获取的版本信息添加到缓存区中
           buffer.append(key +"="+value+"\n");
       }
        // 创建字符写入对象
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null){
            cause.printStackTrace();
            cause.getCause();
        }
        printWriter.close();
        String  result = writer.toString();
        buffer.append(result);
        long tempTime = System.currentTimeMillis();
        String time = format.format(new Date());
        String fileName = "crash-"+time+"-"+tempTime+".text";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            String path = "/sdcard/crash/";
            File dir = new File(path);
            if(!dir.exists()){  // 判断路径下是否存在该文件夹，不存在则创建
                 dir.mkdirs();
            }
            try {
                FileOutputStream fos = new FileOutputStream(path+fileName);
                fos.write(buffer.toString().getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    /**
     *  收集设备参数信息
     * @param ctx
     */
    private void collectDeviceInfo(Context ctx) {
        //  获取包管理器对象
        PackageManager packageMgr = ctx.getPackageManager();
        try {
            // 获取当前活动包信息
            PackageInfo packageInfo = packageMgr.getPackageInfo(ctx.getPackageName(),PackageManager.GET_ACTIVITIES);
            if(null != packageInfo){
                String versionName = packageInfo.versionName == null ? "null": packageInfo.versionName;
                String versionCode = packageInfo.versionCode + "";
                infos.put("versionName",versionName);
                infos.put("versionCode",versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // 通过反射获取所有Build类属性
        Field[]  fields  = Build.class.getDeclaredFields();
        for(Field field :fields){
            // 遍历反射并设置属性权限
            field.setAccessible(true);
            try {
                infos.put(field.getName(),field.get(null).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
