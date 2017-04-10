package com.atom.splash.vediosplashtest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建fragment基类
 * Created by atom on 2017/3/3.
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity baseActivity;
    protected abstract void initView(View view, Bundle savedInstanceState);
    // 获取布局文件
    protected abstract int getLayoutId();
    // 获取宿主Activity
    protected BaseActivity getHoldingActivity(){
        return baseActivity;
    }

    /**
     * 设置Activity baseActivity基类赋值，
     * 防止低内存回收fragment造成空指针
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.baseActivity = (BaseActivity) context;
    }

    /**
     * 添加fragment
     * @param fragment
     */
    protected void addFragment(BaseFragment fragment){
        if(null != fragment){
            getHoldingActivity().addFragment(fragment);
        }
    }

    protected void removeFragment(){
        getHoldingActivity().removeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        initView(view,savedInstanceState);
        return view;
    }
}
