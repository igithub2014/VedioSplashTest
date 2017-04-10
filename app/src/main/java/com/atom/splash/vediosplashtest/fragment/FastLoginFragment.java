package com.atom.splash.vediosplashtest.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.atom.splash.vediosplashtest.BaseActivity;
import com.atom.splash.vediosplashtest.BaseFragment;
import com.atom.splash.vediosplashtest.R;
import com.atom.splash.vediosplashtest.Util.NetUtil;
import com.atom.splash.vediosplashtest.Util.Tools;
import com.atom.splash.vediosplashtest.view.VerityCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * Created by User on 2017/4/1.
 */

public class FastLoginFragment extends BaseFragment {
    private Unbinder unbinder;
    private Activity mCtx;
    protected BaseActivity baseActivity;
    // 手机号输入框
    @BindView(R.id.fast_userName_txt)
    EditText fast_userName_txt;
    // 手机号错误提示框
    @BindView(R.id.fast_errorUser_tap_txt)
    TextView fast_errorUser_tap_txt;
    // 验证码输入框
    @BindView(R.id.fast_verity_txt)
    EditText fast_verity_txt;
    // 验证码错误提示框
    @BindView(R.id.fast_verity_tap_txt)
    TextView fast_verity_tap_txt;
    // 图形验证
    @BindView(R.id.verity_img)
    ImageView verity_img;
    // 动态码验证框
    @BindView(R.id.fast_passWord_txt)
    EditText fast_passWord_txt;
    // 动态验证码按钮
    @BindView(R.id.verity_imgButton)
    Button verity_imgButton;
    // 动态码错误提示
    @BindView(R.id.fast_errorPsd_tap_txt)
    TextView fast_errorPsd_tap_txt;
    // 快速登录按钮
    @BindView(R.id.fast_login_Btn)
    Button fast_login_Btn;
    // 产生的验证码
    private String realCode;
    // 倒计时对象
    private TimeCount timeCount;
    private boolean clickFlag = true;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fastlogin;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        // 将验证码以图形的形式显示出来
        verity_img.setImageBitmap(VerityCodeView.getInstance().createBitmap());
        // 获取生成的验证码的值
        realCode = VerityCodeView.getInstance().getCode().toLowerCase();
        // 初始倒计时设置
        timeCount = new TimeCount(60000, 1000);
        return view;
    }

    @OnClick({R.id.verity_img, R.id.verity_imgButton, R.id.fast_login_Btn})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.verity_img: // 随机码图片监听
                verity_img.setImageBitmap(VerityCodeView.getInstance().createBitmap());
                realCode = VerityCodeView.getInstance().getCode().toLowerCase();
                break;
            case R.id.verity_imgButton: // 动态码按钮监听
                // 发送消息，并启动倒计时
                timeCount.start();
                clickFlag = false;
                String phoneStr = fast_userName_txt.getText().toString();
                if(Tools.isMobileNo(phoneStr)){

                }else{
                    setErrorInfo(fast_errorUser_tap_txt, "手机号为空或非法手机号，请输入正确号码");
                }
                break;
            case R.id.fast_login_Btn: // 快速登录按钮监听
                if (baseActivity.verifyClickTime()) { // 防止快速点击
                    // 手机号
                    String phoneNum = fast_userName_txt.getText().toString();
                    // 验证码
                    String verityCode = fast_verity_txt.getText().toString().toLowerCase();
                    // 动态码
                    String actionCode = fast_passWord_txt.getText().toString();
                    if (null != phoneNum && phoneNum.length() > 0) {
                        if (Tools.isMobileNo(phoneNum)) { // 手机号验证通过
                            if (null != verityCode) { // 验证码不空
                                if (verityCode.equals(realCode)) { // 验证码输入正确
                                    if (null != actionCode && actionCode.length() > 0) { // 动态码不空
                                        if (NetUtil.isNetworkConneted(mCtx)) { // 判断当前有网络连接

                                        } else { // 无网络连接情况

                                        }
                                    } else {  // 动态码为空
                                        setErrorInfo(fast_errorPsd_tap_txt, "动态验证码不能为空");
                                    }
                                } else { // 验证码输入错误
                                    setErrorInfo(fast_verity_tap_txt, "验证码输入错误,请输入正确验证码");
                                    verity_img.setImageBitmap(VerityCodeView.getInstance().createBitmap());
                                    realCode = VerityCodeView.getInstance().getCode().toLowerCase();
                                }
                            } else { // 验证码为空
                                setErrorInfo(fast_verity_tap_txt, "验证码不能为空，请输入正确验证码");
                            }
                        } else { // 手机号非法
                            setErrorInfo(fast_errorUser_tap_txt, "非法手机号，请输入正确的号码格式");
                        }
                    } else { // 手机号输出为空
                        setErrorInfo(fast_errorUser_tap_txt, "手机号不能为空");
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * EidtText内容变化监听
     *
     * @param text
     */
    @OnTextChanged({R.id.fast_userName_txt, R.id.fast_verity_txt, R.id.fast_passWord_txt})
    void afterTextChanged(CharSequence text) {
        // 判断输入框验证码内容是否相同
        if (fast_errorUser_tap_txt.getVisibility() == View.VISIBLE) {
            fast_errorUser_tap_txt.setVisibility(View.GONE);
        } else if (fast_verity_tap_txt.getVisibility() == View.VISIBLE) {
            fast_verity_tap_txt.setVisibility(View.GONE);
        } else if (fast_errorPsd_tap_txt.getVisibility() == View.VISIBLE) {
            fast_errorPsd_tap_txt.setVisibility(View.GONE);
        }
        if(clickFlag){
            if (fast_verity_txt.getText().toString().toLowerCase().equals(realCode)) {
                verity_imgButton.setClickable(true);
                verity_imgButton.setBackgroundColor(Color.parseColor("#E18F8F"));
            } else {
                verity_imgButton.setClickable(false);
                verity_imgButton.setBackgroundColor(Color.parseColor("#dcdcdc"));
            }
        }
    }

    /**
     * 错误提示信息设置
     *
     * @param tv
     * @param errStr
     */
    private void setErrorInfo(TextView tv, String errStr) {
        tv.setText(errStr);
        tv.setTextColor(Color.RED);
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.baseActivity = (BaseActivity) context;
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long totalTime, long jumpTime) {
            super(totalTime, jumpTime);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            verity_imgButton.setClickable(false);
            verity_imgButton.setBackgroundColor(Color.parseColor("#dcdcdc"));
            verity_imgButton.setText((millisUntilFinished / 1000) + ""+"秒后重新发送");
            verity_imgButton.setTextSize(12);
        }

        @Override
        public void onFinish() {
            clickFlag = true;
            verity_imgButton.setText("重新获取动态码");
            verity_imgButton.setTextSize(13);
            verity_imgButton.setClickable(true);
            verity_imgButton.setBackgroundColor(Color.parseColor("#E18F8F"));
        }
    }
}
