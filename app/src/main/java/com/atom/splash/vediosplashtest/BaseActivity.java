package com.atom.splash.vediosplashtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.atom.splash.vediosplashtest.Util.NetBroadReceiver;
import com.atom.splash.vediosplashtest.Util.NetUtil;

import butterknife.ButterKnife;

/**
 * Created by atom on 2017/2/28.
 */

public abstract class BaseActivity extends AppCompatActivity implements NetBroadReceiver.NetEvent {

    // 定义网络类型
    private int netType;
    public static NetBroadReceiver.NetEvent event;
    // 最后一次点击时间
    private long lastClickTime = 0;
    public static final int CLICK_TIME = 1000;
    protected abstract int getLayoutId();
    protected abstract void findViewById();
    protected abstract void setonListener();
    // 布局fragment中id
    protected abstract int getFragmentContentId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = this;
        getInitNetInfo();
        getLayoutId();
        findViewById();
        setonListener();
    }

    @Override
    public void onNetChange(int netMobile) {
        this.netType = netMobile;
        isNetConnect();
    }

    /**
     * 获取初始网络连接情况并赋值给netType
     */
    public boolean getInitNetInfo() {
        // 获取网络连接类型并赋值给netType
        this.netType = NetUtil.getConnectedType(this);
        return  isNetConnect();
    }

    /**
     * 判断网络是否连接(0:移动网络,1:WIFI,-1:无网络)
     * @return
     */
    public boolean isNetConnect(){
       if(netType == 0 || netType == 1){
           return  true;
       }else{
           return false;
       }
    }

    /**
     * 无数据传递跳转
     * @param targetActivityClass
     */
    public void openActivity(Class<?> targetActivityClass){
        openActivity(targetActivityClass,null);
    }

    /**
     * 带参数Activity跳转
     * @param tartgetActivity
     * @param bundle
     */
    public void openActivity(Class<?> tartgetActivity,Bundle bundle){
        Intent it = new Intent(this,tartgetActivity);
        if(null != bundle){
            it.putExtras(bundle);
        }
        startActivity(it);
    }

    /**
     *不带参数Activity跳转并结束当前Activity
     * @param targetActivityClass
     */
    public void openActivityAndCloseThis(Class<?>targetActivityClass){
        openActivity(targetActivityClass,null);
        this.finish();
    }

    /**
     * 带参数的Activity跳转并结束当前Activity
     * @param targetActivityClass
     * @param bundle
     */
    public void openActivityAndCloseThis(Class<?> targetActivityClass,Bundle bundle){
        openActivity(targetActivityClass,bundle);
        this.finish();
    }

    /**
     * 防止快速点击
     * @return
     */
    public boolean verifyClickTime(){
        if(System.currentTimeMillis() - lastClickTime <= CLICK_TIME){
            return false;
        }
        lastClickTime = System.currentTimeMillis();
        return true;
    }

    /**
     * 添加fragment
     * @param baseFrament
     */
    protected  void addFragment(BaseFragment baseFrament){
        if(null != baseFrament){
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(),baseFrament,baseFrament.getClass().getSimpleName())
                    .addToBackStack(baseFrament.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * 移除fragment
     */
    protected void removeFragment(){
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        }else{
            finish();
        }
    }
}
