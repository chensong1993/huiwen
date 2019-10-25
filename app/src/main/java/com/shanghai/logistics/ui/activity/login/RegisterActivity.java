package com.shanghai.logistics.ui.activity.login;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.UserActivity;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.connectors.login.RegisterConnector;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.presenters.login.RegisterPresenter;
import com.shanghai.logistics.util.ActivityCollector;
import com.shanghai.logistics.util.CountDownTimerUtils;
import com.shanghai.logistics.util.FormatUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterConnector.View {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_nickname)
    EditText mEtNickName;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.et_pwd)
    EditText mEtPassword;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_h_code)
    TextView mTvCode;
    @BindView(R.id.see_check_box_login_fragment)
    CheckBox mCbSeePwd;
    //    @BindView(R.id.img_back)
//    RelativeLayout mRlBack;
    String phone;
    String password;
    String code;
    String nickName;
    Animation mShakeAnim;
    final static String TAG = "register";
    //倒计时
    CountDownTimerUtils countDownTimerUtils;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_view);
        mTvTitle.setText("注册");
    }

    @OnClick({R.id.see_check_box_login_fragment, R.id.img_back, R.id.tv_register, R.id.tv_login, R.id.tv_h_code})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.see_check_box_login_fragment: //查看密码
                boolean checked = mCbSeePwd.isChecked();
                if (checked) {
                    mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.tv_register:
                phone = mEtPhone.getText().toString();
                nickName = mEtNickName.getText().toString();
                code = mEtCode.getText().toString();
                password = mEtPassword.getText().toString();
                Log.i(TAG, "nickName: " + nickName);
                Log.i(TAG, "phone: " + phone);
                Log.i(TAG, "code: " + code);
                Log.i(TAG, "password: " + password);
                if (nickName.isEmpty()) {
                    ToastUtils.show("昵称不能为空");
                    return;
                }
                if (phone.isEmpty()) {
                    ToastUtils.show("手机号不能为空");
                    return;
                }
                if (!FormatUtil.isMobileNO(phone)) {
                    ToastUtils.show("手机号格式不正确");
                    return;
                }
                if (code.isEmpty()) {
                    ToastUtils.show("验证码不能为空");
                    return;
                }
                if (password.isEmpty()) {
                    ToastUtils.show("密码不能为空");
                    return;
                }

                mPresenter.getRegisterData(phone, password, code, nickName);
                break;
            case R.id.tv_h_code:
                phone = mEtPhone.getText().toString();
                Log.i(TAG, "phone: " + phone);
                if (phone.isEmpty()) {
                    ToastUtils.show("手机号不能为空");
                    return;
                }
                if (FormatUtil.isMobileNO(phone)) {
                    mPresenter.getSendCode(phone, 1);
                } else {
                    ToastUtils.show("手机格式不正确");
                }
                break;
            case R.id.tv_login:
                if (ActivityCollector.isActivityExist(LoginActivity.class)) {
                    finish();
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
                break;

        }

    }


    @Override
    public void registerMsg(int msg) {
        mTvRegister.startAnimation(mShakeAnim);
        Log.i(TAG, "registerErr: " + msg);
        switch (msg) {
            case 0:
                ToastUtils.show("注册失败");
                break;
            case 1:
                ToastUtils.show("注册成功");
                finish();
                break;
            case -1:
                ToastUtils.show("还有哪项没填哦");
                break;
            case -2:
                ToastUtils.show("验证码错误");
                break;
            case -3:
                ToastUtils.show("验证码已失效");
                break;
            case -4:
                ToastUtils.show("该手机号已经注册过");
                break;
        }
    }

    @Override
    public void registerErr(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void sendCode(int msg) {
        Log.i(TAG, "sendCodeDataErr: " + msg);
        switch (msg) {
            case 0:
                ToastUtils.show("发送失败");
                break;
            case 1:
                countDownTimerUtils = new CountDownTimerUtils(this, mTvCode, 60000, 1000);
                countDownTimerUtils.start();
                ToastUtils.show("发送成功");
                break;
            case -1:
                ToastUtils.show("看看还有哪项没填");
                break;
            case -2:
                ToastUtils.show("该手机号已经注册过");
                break;
            case -3:
                ToastUtils.show("该手机号未注册过");
                break;
            case -4:
                ToastUtils.show("手机号不正确");
                break;
            case -5:
                ToastUtils.show("您的操作太频繁，请稍候再试");
                break;
            default:
                ToastUtils.show("获取验证码错误");
                break;
        }
    }

    @Override
    public void sendCodeErr(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


}
