package com.shanghai.logistics.ui.activity.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.connectors.login.RegisterConnector;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.presenters.login.RegisterPresenter;
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
    @BindView(R.id.img_back)
    RelativeLayout mRlBack;
    String phone;
    String password;
    String code;
    String nickName;
    Animation mShakeAnim;
    final static String TAG = "register";

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_view);
        mTvTitle.setText("注册");
    }

    @OnClick({R.id.img_back, R.id.tv_register, R.id.tv_login, R.id.tv_h_code})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
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
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;

        }

    }

    @Override
    public void registerUserData(LoginEntity loginEntity) {
        ToastUtils.show("注册成功");
        finish();
    }

    @Override
    public void sendCodeData() {

    }

    @Override
    public void registerErr(String msg) {
        mTvRegister.startAnimation(mShakeAnim);
        ToastUtils.show("注册失败");
        Log.i(TAG, "registerErr: " + msg);
    }

    @Override
    public void sendCodeDataErr(String msg) {
        Log.i(TAG, "sendCodeDataErr: " + msg);
        ToastUtils.show("获取验证码错误");
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
