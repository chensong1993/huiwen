package com.shanghai.logistics.injections.components;

import android.app.Activity;

import com.shanghai.logistics.injections.modules.ActivityModule;
import com.shanghai.logistics.injections.scope.ActivityScope;
import com.shanghai.logistics.ui.activity.login.ChangePasswordActivity;
import com.shanghai.logistics.ui.activity.login.LoginActivity;
import com.shanghai.logistics.ui.activity.login.RegisterActivity;
import com.shanghai.logistics.ui.logistics_activity.BillingActivity;
import com.shanghai.logistics.ui.logistics_activity.NewOrderDetailActivity;
import com.shanghai.logistics.ui.main_activity.address_list.NewFriendListActivity;
import com.shanghai.logistics.ui.user_activity.HomeDetailActivity;
import com.shanghai.logistics.ui.user_activity.MoreCommentsActivity;
import com.shanghai.logistics.ui.user_activity.ShopDetailActivity;
import com.shanghai.logistics.ui.user_activity.SpecialLineActivity;
import com.shanghai.logistics.ui.user_activity.UserBackActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.ContactActivity;
import com.shanghai.logistics.ui.user_activity.home_detail.PlaceAnOrderActivity;
import com.shanghai.logistics.ui.user_activity.me.AddressListActivity;
import com.shanghai.logistics.ui.user_activity.me.CertificationActivity;
import com.shanghai.logistics.ui.user_activity.release.ShipmentsActivity;


import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
    void inject(ChangePasswordActivity changePasswordActivity);
    void inject(HomeDetailActivity homeDetailActivity);
    void inject(MoreCommentsActivity moreCommentsActivity);
    void inject(ContactActivity contactActivity);
    void inject(UserBackActivity userBackActivity);
    void inject(SpecialLineActivity specialLineActivity);
    void inject(ShopDetailActivity shopDetailActivity);
    void inject(AddressListActivity addressListActivity);
    void inject(ShipmentsActivity shipmentsActivity);
    void inject(NewFriendListActivity newFriendListActivity);
    void inject(BillingActivity billingActivity);
    void inject(PlaceAnOrderActivity placeAnOrderActivity);
    void inject(NewOrderDetailActivity newOrderDetailActivity);
}
