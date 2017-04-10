package com.atom.splash.vediosplashtest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by User on 2016/10/12.
 */

public class MusicFragment extends Fragment {

    public static MusicFragment newInstance(String content){
        MusicFragment musicFragment = new MusicFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        musicFragment.setArguments(bundle);
        return musicFragment;
    }
}
