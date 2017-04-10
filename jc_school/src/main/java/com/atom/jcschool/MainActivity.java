package com.atom.jcschool;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.atom.myapplication.R;

public class MainActivity extends BaseActivity{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setOnClickListener() {

    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置视图
        setContentView(getLayoutId());
    }
}
