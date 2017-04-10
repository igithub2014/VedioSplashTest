package com.atom.splash.vediosplashtest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.atom.splash.vediosplashtest.index.LoginManagerAc;

import java.io.IOException;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager viewPager;
    // 定义view容器
    private ArrayList<View> viewscontanies;
    // 定义存放视频容器surfaceView
    private SurfaceView sf1, sf2, sf3;
    // 定义视图对象
    private View view1, view2, view3;
    // 定义surfaceHolder 对象
    private SurfaceHolder holder1, holder2, holder3;
    // 定义MediaPlayer 对象
    private MediaPlayer player;
    // 目录
    private String Path = Environment.getExternalStorageDirectory().getPath()
            + "/1.mp4";
    // 注册按钮
    private Button register;
    // 进入按钮
    private Button joinin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 初始化视图
        initView();
        // 获取surfaceView holder对象
        holder1 = sf1.getHolder();
        holder2 = sf2.getHolder();
        holder3 = sf3.getHolder();
        // 实现回调
        holder1.addCallback(this);
        holder2.addCallback(this);
        holder3.addCallback(this);

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewscontanies.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewscontanies.get(position));
                return viewscontanies.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView((View) object);
            }
        });

        viewPager.addOnPageChangeListener(this);
    }

    /**
     * 初始化视图，获取视图控件id
     */
    private void initView() {

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewscontanies = new ArrayList<View>();

        view1 = View.inflate(this, R.layout.guide_view1, null);
        view2 = View.inflate(this, R.layout.guide_view2, null);
        view3 = View.inflate(this, R.layout.guide_view3, null);

        sf1 = (SurfaceView) view1.findViewById(R.id.sf1);
        sf2 = (SurfaceView) view2.findViewById(R.id.sf2);
        sf3 = (SurfaceView) view3.findViewById(R.id.sf3);

        viewscontanies.add(view1);
        viewscontanies.add(view2);
        viewscontanies.add(view3);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // 初始化mediaplayer对象
        player = new MediaPlayer();
        // 添加到surfaceView上
        player.setDisplay(holder);
        player.setOnPreparedListener(this);
        // 设置循环播放
        player.setLooping(true);
        if (holder1.equals(holder)) {
            Path = Environment.getExternalStorageDirectory().getPath()
                    + "/1.mp4";
        }
        if (holder2.equals(holder)) {
            Path = Environment.getExternalStorageDirectory().getPath()
                    + "/2.mp4";
        }
        if (holder3.equals(holder)) {
            Path = Environment.getExternalStorageDirectory().getPath()
                    + "/3.mp4";
        }

        /* 设置文件路径 */
        try {
            player.setDataSource(Path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* 准备播放*/
        try {
            player.prepareAsync();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        // 开始播放
        mp.start();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if(i == viewscontanies.size()-1){ // 当viewpager 滑动到最后一页的时候
            register = (Button) findViewById(R.id.register);
            joinin = (Button) findViewById(R.id.joinin);

            register.setOnClickListener(this);
            joinin.setOnClickListener(this);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.register: // 注册按钮
                player.stop();
                player.release();
                Intent it = new Intent(this,LoginManagerAc.class);
                startActivity(it);
                this.finish();
                break;
            case R.id.joinin: // 直接进入
                player.stop();
                player.release();
                Intent mainIt = new Intent(this,MainActivity.class);
                startActivity(mainIt);
                this.finish();
                break;
            default:
                break;
        }
    }
}
