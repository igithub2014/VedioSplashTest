package com.atom.splash.vediosplashtest.Util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.atom.splash.vediosplashtest.MyApplication;
import com.atom.splash.vediosplashtest.R;

/**
 * Toast管理类
 * Created by atom on 2017/3/30.
 */

public class ToastUtil {

    // 定义toast类对象
    private static Toast toast;
    private static Toast toast2;

    /**
     * 初始化Toast
     *
     * @param message
     * @param duration
     * @return
     */
    private static Toast initToast(CharSequence message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(MyApplication.getAppContext(), message, duration);
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return toast;
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        initToast(message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param strResId
     */
    public static void showShort(int strResId) {
        initToast(MyApplication.getAppContext().getResources().getText(strResId), Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        initToast(message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param strResId
     */
    public static void showLong(int strResId) {
        initToast(MyApplication.getAppContext().getResources().getText(strResId), Toast.LENGTH_LONG);
    }

    /**
     * 自定义Toast显示时间
     *
     * @param context
     * @param strResId
     * @param duration
     */
    public static void show(Context context, int strResId, int duration) {
        initToast(context.getResources().getText(strResId), duration).show();
    }

    /**
     * 显示有Image的Toast
     *
     * @param tvStr
     * @param imageResource
     * @return
     */
    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
        if (null == toast2) {
            toast2 = new Toast(MyApplication.getAppContext());
        }
        View view = LayoutInflater.from(MyApplication.getAppContext()).inflate(R.layout.toast_custom, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(null == tvStr ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        toast2.setView(view);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        toast2.show();
        return toast2;
    }
}
