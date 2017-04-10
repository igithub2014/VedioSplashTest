package com.atom.splash.vediosplashtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.atom.splash.vediosplashtest.Util.ToastUtil;
import com.atom.splash.vediosplashtest.Util.Tools;
import com.atom.splash.vediosplashtest.core.KernelServer;
import com.atom.splash.vediosplashtest.task.DoHandler;
import com.atom.splash.vediosplashtest.task.Task;

public class LoginAc extends BaseActivity implements View.OnClickListener {

    private EditText userName_txt;
    private EditText passWord_txt;
    private Button login_Btn;
    private TextView findPWD_txt;
    private TextView register_txt;
    private String userName; // 用户名
    private String passWord; // 密码
    private Task task;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void findViewById() {
        userName_txt = (EditText) findViewById(R.id.userName_txt);
        passWord_txt = (EditText) findViewById(R.id.passWord_txt);
        login_Btn = (Button) findViewById(R.id.login_Btn);
        findPWD_txt = (TextView) findViewById(R.id.findPWD_txt);
        register_txt = (TextView) findViewById(R.id.register_txt);
    }

    @Override
    protected void setonListener() {
        login_Btn.setOnClickListener(this);
        findPWD_txt.setOnClickListener(this);
        register_txt.setOnClickListener(this);
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置视图
        setContentView(getLayoutId());
        this.isNetConnect();
    }

    @Override
    public void onNetChange(int netMobile) {
        super.onNetChange(netMobile);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_Btn: // 登录按钮监听
                if(verifyClickTime()){ // 防止重复点击
                    userName = Tools.strReplaceAll(userName_txt.getText().toString().trim());
                    passWord = Tools.strReplaceAll(passWord_txt.getText().toString().trim());
                    if(""== userName || "null".equals(userName)){ // 判断用户名是否为空
                        ToastUtil.show(this,R.string.hint_txt,4000);
                    }else if(""== userName || "null".equals(passWord)){
                       ToastUtil.show(this,R.string.hint_pwd_txt,4000);
                    }else{
                        task = new Task();
                        task.clazz = DoHandler.class;
                        task.method = "getLoginInfos";
                        task.params .put("userName",userName);
                        task.params.put("passWord",passWord);
                        task.params.put("handler",handler);
                        KernelServer.getInstance().doTask(task);
                    }
                }
                break;
            case R.id.findPWD_txt: // 找回密码监听
                if(verifyClickTime()){

                }
                break;
            case R.id.register_txt: // 新用户注册
                if(verifyClickTime()){

                }
                break;
            default:
                break;
        }
    }
}
