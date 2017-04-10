package com.atom.splash.vediosplashtest.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.atom.splash.vediosplashtest.BaseActivity;

public class NetBroadReceiver extends BroadcastReceiver {
    NetEvent event = BaseActivity.event;
    public NetBroadReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            int netType = NetUtil.getConnectedType(context);
            event.onNetChange(netType);
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public interface NetEvent{
        void onNetChange(int netMobile);
    }
}
