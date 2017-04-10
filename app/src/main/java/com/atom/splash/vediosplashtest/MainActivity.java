package com.atom.splash.vediosplashtest;

import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atom.splash.vediosplashtest.view.PopView;


import static com.atom.splash.vediosplashtest.R.id.addMore;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 工具bar
    private Toolbar toolbar;
    // 画布布局
    private DrawerLayout drawerLayout;
    // 导航栏布局
    private NavigationView navigationView;
    // frame布局
    private FrameLayout frame_layout;
    // 初始化底部菜单栏布局
    private TextView txt_home;
    private TextView txt_find;
    private TextView txt_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化视图
        initSettingView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_right_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 获取视图控件id,设置监听
     */
    private void initSettingView() {
        // 添加到Activity管理类中
        APPManager.getManager().addActivity(this);
        // 判断当编译的sdk版本大于API 19(4.0)情况
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {

            // 设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 设置活动状态栏状态
        setSupportActionBar(toolbar = (Toolbar) findViewById(R.id.toolbar));
        // 获取画布布局id
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        // 设置导航栏视图id
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        // bar设置icon
        toolbar.setNavigationIcon(R.drawable.avatar_icon);
        // 取消默认标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //  初始状态栏转化键
        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        // 设置状态栏状态同步
        actionBarDrawerToggle.syncState();
        // 导航栏左侧按钮设置监听
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        // 点击滑动出现左侧抽屉
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });
        // toolbar 菜单设置点击
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case addMore:
                        PopView popView = new PopView(MainActivity.this);
                        popView.showPopWindows(toolbar);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        // 导航菜单栏设置监听
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.exam: // 真题点击
                        Toast.makeText(MainActivity.this, "真题试卷", Toast.LENGTH_SHORT).show();
//                        Intent it = new Intent();
//                        startActivity(it);
                        break;
                    case R.id.everyday_test: // 每日一题点击
                        Toast.makeText(MainActivity.this, "每日一题", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.month_exam: // 每月一测点击
                        Toast.makeText(MainActivity.this, "每月一测", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.history_practice: // 练习历史
                        Toast.makeText(MainActivity.this, "练习历史", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.error_practice: // 错题练习点击
                        Toast.makeText(MainActivity.this, "错题练习", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.browser: // 错题浏览点击
                        Toast.makeText(MainActivity.this, "错题浏览", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.favor: // 收藏题目点击
                        Toast.makeText(MainActivity.this, "收藏题目", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        // 初始化frame_layout
        frame_layout = (FrameLayout) findViewById(R.id.frame_layout);
        // 初始化底部菜单栏
        txt_home = (TextView) findViewById(R.id.txt_home);
        txt_find = (TextView) findViewById(R.id.txt_find);
        txt_user = (TextView) findViewById(R.id.txt_user);
        // 底部设置监听
        txt_home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_home: // 主页监听
                break;
            case R.id.txt_find: // 发现监听
                break;
            case R.id.txt_user: // 个人设置监听
                break;
            default:
                break;
        }
    }
}