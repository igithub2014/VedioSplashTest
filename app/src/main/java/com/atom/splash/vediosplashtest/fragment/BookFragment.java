package com.atom.splash.vediosplashtest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by User on 2016/10/12.
 */

public class BookFragment extends Fragment {

    public static BookFragment newInstance(String content){
        BookFragment bookFragment = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        bookFragment.setArguments(bundle);
        return bookFragment;
    }
}
