package com.atom.splash.vediosplashtest.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.atom.splash.vediosplashtest.R;

/**
 * Created by User on 2016/8/5.
 */

public class PopView extends PopupWindow implements View.OnClickListener {

    private final Context mCtx;
    private View convertView;
    private RelativeLayout live_layout;
    private RelativeLayout buy_layout;
    private RelativeLayout qr_layout;
    private RelativeLayout shopping_layout;
    private int w;

    public PopView(Context context){

        this.mCtx = context;
        convertView = LayoutInflater.from(mCtx).inflate(R.layout.pop_view,null);
        // 获取默认高度和宽度
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager)mCtx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        int h = dm.heightPixels;
        w = dm.widthPixels;
        // 添加视图
        this.setContentView(convertView);
        //  初始化控件
        initView();
        // 设置弹出宽度和高度
        this.setWidth(w/2-150);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置可点击
        this.setFocusable(true);
        // 设置外边框点击消失
        this.setOutsideTouchable(true);
        this.update();
        // 设置背景透明
        ColorDrawable colorDraw = new ColorDrawable(0000000000);
        this.setBackgroundDrawable(colorDraw);
        // 设置动画
        this.setAnimationStyle(R.style.popAnimation);
    }

    private void initView() {

        this.setOutsideTouchable(true);
        // 设置刷新状态
        live_layout = (RelativeLayout) convertView.findViewById(R.id.live_layout);
        buy_layout = (RelativeLayout) convertView.findViewById(R.id.buy_layout);
        qr_layout = (RelativeLayout) convertView.findViewById(R.id.qr_layout);
        shopping_layout = (RelativeLayout) convertView.findViewById(R.id.shopping_layout);
        // 内容设置监听
        live_layout.setOnClickListener(this);
        buy_layout.setOnClickListener(this);
        qr_layout.setOnClickListener(this);
        shopping_layout.setOnClickListener(this);
    }


    public void showPopWindows(View parent){

        if(!this.isShowing()){
            this.showAsDropDown(parent,w,5);
        }else{
            this.dismiss();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.live_layout: // 视频直播
                Toast.makeText(mCtx, "图书商城被点击", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.buy_layout: // 购物车
                Toast.makeText(mCtx, "图书商城被点击", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.qr_layout: // 扫一扫
                Toast.makeText(mCtx, "图书商城被点击", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.shopping_layout: // 图书商城按钮
                Toast.makeText(mCtx, "图书商城被点击", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
           default:
               break;
        }
    }
}
