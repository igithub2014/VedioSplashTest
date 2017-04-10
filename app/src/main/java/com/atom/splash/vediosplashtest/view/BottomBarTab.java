package com.atom.splash.vediosplashtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.atom.splash.vediosplashtest.R;

/**
 * Created by User on 2016/10/10.
 */

public class BottomBarTab extends FrameLayout {

    private Context mContext;
    private ImageView mIcon;
    private int mTabPostion = -1;

    public BottomBarTab(Context context) {
        super(context);
    }

    public BottomBarTab(Context context, @DrawableRes int icon) {
        this(context, null, icon);
    }

    public BottomBarTab(Context context, AttributeSet attrs, @DrawableRes int icon) {
        this(context, attrs, 0, icon);
    }

    public BottomBarTab(Context context, AttributeSet attrs, int defStyleAttr, @DrawableRes int icon) {
        super(context, attrs, defStyleAttr);
        init(context, icon);
    }



    private void init(Context context, int icon) {

        this.mContext = context;
        TypedArray typeArray = mContext.obtainStyledAttributes(
                new int[]{R.attr.selectableItemBackgroundBorderless});
        Drawable drawable = typeArray.getDrawable(0);
        setBackgroundDrawable(drawable);
        typeArray.recycle();

        mIcon = new ImageView(mContext);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,27,getResources().getDisplayMetrics());
        LayoutParams params = new LayoutParams(size,size);
        params.gravity = Gravity.CENTER;
        mIcon.setImageResource(icon);
        mIcon.setLayoutParams(params);
        mIcon.setColorFilter(Color.parseColor("#c9c9c9"));
        addView(mIcon);

    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if(selected){
            mIcon.setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary));
        }else{
            mIcon.setColorFilter(ContextCompat.getColor(mContext,R.color.tab_unselect));
        }
    }

    public int getmTabPostion() {
        return mTabPostion;
    }

    public void setmTabPostion(int position) {
        this.mTabPostion = position;
        if(position == 0){
            setSelected(true);
        }
    }
}
