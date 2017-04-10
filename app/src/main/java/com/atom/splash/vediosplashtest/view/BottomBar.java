package com.atom.splash.vediosplashtest.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.atom.splash.vediosplashtest.MainActivity;

/**
 * Created by User on 2016/10/10.
 */

public class BottomBar extends LinearLayout {

    private LinearLayout mTabLayout;
    private LayoutParams mTabParams;
    private int mCurrentPosition = 0;

    public BottomBar(Context context) {
        this(context,null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        mTabLayout = new LinearLayout(context);
        mTabLayout.setBackgroundColor(Color.parseColor("#485D62"));
        mTabLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mTabLayout.setLayoutParams(params);
        addView(mTabLayout);

        mTabParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        mTabParams.weight = 1;

    }

    public BottomBar addItem(final BottomBarTab tab){

        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = tab.getmTabPostion();
                if(mCurrentPosition == position){

                }else{
                    tab.setSelected(true);
                    mTabLayout.getChildAt(mCurrentPosition).setSelected(false);
                    mCurrentPosition = position;
                }
            }
        });

        tab.setmTabPostion(mTabLayout.getChildCount());
        tab.setLayoutParams(mTabParams);
        mTabLayout.addView(tab);
        return this;
    }

    public void setCurrentPosition(final int position) {
       mTabLayout.post(new Runnable() {
           @Override
           public void run() {
               mTabLayout.getChildAt(position).performClick();
           }
       });
    }
}
