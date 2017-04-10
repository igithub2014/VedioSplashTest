package com.atom.splash.vediosplashtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 2016/10/12.
 */

public class MoiveFragment extends Fragment {

    public  static MoiveFragment newInstance(String content){
        MoiveFragment moiveFragment = new MoiveFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        moiveFragment.setArguments(bundle);
        return  moiveFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
