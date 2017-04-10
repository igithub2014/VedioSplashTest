package com.atom.splash.vediosplashtest.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.atom.splash.vediosplashtest.APPManager;
import com.atom.splash.vediosplashtest.BaseFragment;
import com.atom.splash.vediosplashtest.R;
import com.atom.splash.vediosplashtest.Util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by User on 2017/3/31.
 */

public class NormalLoginFragment extends BaseFragment {

    private Unbinder unbinder;
    private Activity mCtx;
    // 用户名输入框
    @BindView(R.id.userName_txt) EditText userName_txt;
    // 用户名报错提示
    @BindView(R.id.erroruser_tap_txt) TextView erroruser_tap_txt;
    // 密码输入框
    @BindView(R.id.passWord_txt) EditText passWord_txt;
    // 密码输入错误提示
    @BindView(R.id.errorpsd_tap_txt) TextView errorpsd_tap_txt;
    // 提交按钮
    @BindView(R.id.login_Btn) Button login_Btn;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        return  view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null !=unbinder){
            unbinder.unbind();
        }
    }

    @OnClick({R.id.login_Btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_Btn: // 登录按钮
                ToastUtil.show(mCtx,R.string.action_settings,4000);
                break;
            default:
                break;
        }
    }
}
