package com.shanghai.logistics.injections.components;

import android.app.Activity;

import com.shanghai.logistics.injections.modules.ActivityModule;
import com.shanghai.logistics.injections.scope.ActivityScope;
import com.shanghai.logistics.ui.activity.login.ChangePasswordActivity;
import com.shanghai.logistics.ui.activity.login.LoginActivity;
import com.shanghai.logistics.ui.activity.login.RegisterActivity;


import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(ChangePasswordActivity changePasswordActivity);
}
