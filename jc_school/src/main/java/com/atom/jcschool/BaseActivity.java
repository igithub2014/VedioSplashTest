package com.atom.jcschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.atom.jcschool.utils.NetBroadReceiver;
import com.atom.jcschool.utils.NetUtil;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 定义抽象Activity类
 * 实现dp转px
 * 实现网络变化监听
 * Created by atom on 2017/3/28.
 */

public abstract class BaseActivity extends AutoLayoutActivity implements NetBroadReceiver.NetEvent {

    // 网络类型
    private int netType;
    // 定义网络监听广播类对象
    public static NetBroadReceiver.NetEvent event;
    // 最后一次点击时间
    private long lastClickTime = 0;
    public static final int CLICK_TIME = 1000;

    protected abstract int getLayoutId();

    protected abstract void findViewById();

    protected abstract void setOnClickListener();

    // 布局中的fragement id
    protected abstract int getFragmentContentId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = this;
        // 获取初始网络情况
        getInitNetInfo();
        getLayoutId();
        findViewById();
        setOnClickListener();
    }

    /**
     * 获取初始网络情况
     *
     * @return
     */
    public boolean getInitNetInfo() {
        this.netType = NetUtil.getConnectedType(this);
        return isNetConnected();
    }

    /**
     * 判断网络是否连接(0:流量,1:wifi,-1:无网络)
     *
     * @return
     */
    public boolean isNetConnected() {
        if (netType == 1 || netType == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onNetChange(int netMobile) {
        this.netType = netMobile;
        isNetConnected();
    }

    /**
     * 不带参数Activity跳转
     *
     * @param targetActivityClass
     */
    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    /**
     * 带参数Activity跳转
     *
     * @param targetActivity
     * @param bundle
     */
    public void openActivity(Class<?> targetActivity, Bundle bundle) {
        Intent it = new Intent(this, targetActivity);
        if (null != bundle) {
            it.putExtras(bundle);
        }
        startActivity(it);
    }

    /**
     * 不带参数Activity跳转并结束当前Activity
     *
     * @param targetActivityClass
     */
    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivityAndCloseThis(targetActivityClass, null);
        this.finish();
    }

    /**
     * 带参数Activity跳转并结束当前Activity
     *
     * @param targetActivity
     * @param bundle
     */
    public void openActivityAndCloseThis(Class<?> targetActivity, Bundle bundle) {
        openActivity(targetActivity, bundle);
        this.finish();
    }

    /**
     * 防止快速点击
     *
     * @return
     */
    public boolean verifyClickTime() {
        if (System.currentTimeMillis() - lastClickTime <= CLICK_TIME) {
            return false;
        }
        lastClickTime = System.currentTimeMillis();
        return true;
    }

    /**
     * 添加fragment
     *
     * @param baseFragment
     */
    public void addFragment(BaseFragment baseFragment) {
        if (null != baseFragment) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), baseFragment, baseFragment.getClass().getSimpleName())
                    .addToBackStack(baseFragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * 移除Fragment
     */
    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
