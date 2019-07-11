package com.shanghai.logistics.ui.activity.login;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.shanghai.logistics.R;
import com.shanghai.logistics.app.App;
import com.shanghai.logistics.app.Constants;
import com.shanghai.logistics.base.BaseActivity;
import com.shanghai.logistics.base.connectors.login.LoginConnector;
import com.shanghai.logistics.models.entity.LoginEntity;
import com.shanghai.logistics.models.http.cookies.CookiesManager;
import com.shanghai.logistics.presenters.login.LoginPresenter;
import com.shanghai.logistics.util.FormatUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @note 登录主界面
 * @anthor Song Chen
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginConnector.View {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_forgetpwd)
    TextView mTvForgerPwd;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    Animation mShakeAnim;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        mShakeAnim = AnimationUtils.loadAnimation(this, R.anim.shake_view);
        mTvTitle.setText("登陆");
        mImgBack.setVisibility(View.GONE);
    }


    @OnClick({R.id.img_back, R.id.tv_login, R.id.tv_register, R.id.tv_forgetpwd})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                //  finish();
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_login:
                String phone = mEtPhone.getText().toString();
                String pwd = mEtPwd.getText().toString();
                if (phone.isEmpty()) {
                    ToastUtils.show("手机号不能为空");
                }
                if (FormatUtil.isMobileNO(phone)) {
                    mPresenter.getShowLogin(phone, pwd);
                } else {
                    ToastUtils.show("手机号格式不正确");
                }
                Log.i("onLogin", phone + pwd);

                break;
            case R.id.tv_forgetpwd:
                startActivity(new Intent(this, ChangePasswordActivity.class));
                break;
        }

    }


    @Override
    public void showLogin(LoginEntity entities) {
        App.kv.encode(Constants.USER_PHONE, entities.getPhone());
        ToastUtils.show("登录成功");
       // finish();
    }

    @Override
    public void stateError() {
        super.stateError();
        mTvLogin.startAnimation(mShakeAnim);
        ToastUtils.show("登陆失败");
        CookiesManager.clearAllCookies();
        App.kv.remove(Constants.USER_PHONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // App.refWatcher.watch(this);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
