package com.atom.splash.vediosplashtest.index;


import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.atom.splash.vediosplashtest.APPManager;
import com.atom.splash.vediosplashtest.BaseActivity;
import com.atom.splash.vediosplashtest.R;
import com.atom.splash.vediosplashtest.fragment.FastLoginFragment;
import com.atom.splash.vediosplashtest.fragment.NormalLoginFragment;

import java.util.ArrayList;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by atom on 2017/3/30.
 */

public class LoginManagerAc extends BaseActivity {

    private Unbinder unbinder;
    @BindView(R.id.vp) ViewPager vp;
    @BindView(R.id.tab_item_login) TextView tab_item_login;
    @BindView(R.id.tab_item_fastLogin) TextView tab_item_fastLogin;
    @BindArray(R.array.tab_name) String[] mtitiles;
    @BindView(R.id.img_cursor)  ImageView img_cursor;
    private static int bmpW; // 横线宽度
    private static int offset;//图片移动的偏移量
    private int currIndex;
    private ArrayList<Fragment> list;

    @Override
    protected int getLayoutId() {
        return R.layout.login_main;
    }

    @Override
    protected void findViewById() {


    }

    @Override
    protected void setonListener() {

    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPManager.getManager().addActivity(this);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        // 初始化偏移图片
        initImage();
        list = new ArrayList<Fragment>();
        Fragment normalLoginFragment = new NormalLoginFragment();
        Fragment fastLoginFragment = new FastLoginFragment();
        list.add(normalLoginFragment);
        list.add(fastLoginFragment);
        vp.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),list));
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int one = offset * 2 + bmpW; // 获取相邻页面偏移量
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Animation animation = new TranslateAnimation(currIndex*one,position*one,0,0);
                currIndex = position;
                animation.setFillAfter(true); // 终止动画停留在最后一帧
                animation.setDuration(200);
                img_cursor.setAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null !=unbinder){
            unbinder.unbind();
        }
        APPManager.getManager().finishActivity(this);
    }

    @OnClick({R.id.tab_item_login,R.id.tab_item_fastLogin})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tab_item_login: // 普通登录
                vp.setCurrentItem(0);
                break;
            case R.id.tab_item_fastLogin: // 手机快速登录
                vp.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    class MyFragmentAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment> mList;
        public MyFragmentAdapter(FragmentManager fm,ArrayList<Fragment> list) {
            super(fm);
            this.mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

    /*
 * 初始化图片的位移像素
 */
    public void initImage(){
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.bgborder).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW/2 - bmpW)/2;
        //imgageview设置平移，使下划线平移到初始位置（平移一个offset）
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);
    }
}
