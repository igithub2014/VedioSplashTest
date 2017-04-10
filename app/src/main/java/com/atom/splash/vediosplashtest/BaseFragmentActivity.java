package com.atom.splash.vediosplashtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;


/**
 * Created by atom on 2017/3/31.
 */

public abstract class BaseFragmentActivity extends FragmentActivity {

    protected abstract int getLayoutId();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

}
