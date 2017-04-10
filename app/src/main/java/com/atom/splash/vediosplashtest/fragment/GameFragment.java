package com.atom.splash.vediosplashtest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by User on 2016/10/12.
 */

public class GameFragment extends Fragment {

    public static GameFragment newInstance(String content){
        GameFragment gameFragment = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        gameFragment.setArguments(bundle);
        return  gameFragment;
    }
}
