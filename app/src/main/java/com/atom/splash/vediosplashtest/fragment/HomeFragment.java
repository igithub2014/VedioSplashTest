package com.atom.splash.vediosplashtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atom.splash.vediosplashtest.R;

/**
 * Created by User on 2016/10/12.
 */

public class HomeFragment extends Fragment {

    // 定义viewpager对象
    private ViewPager mViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index,container,false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        return view;
    }
}
