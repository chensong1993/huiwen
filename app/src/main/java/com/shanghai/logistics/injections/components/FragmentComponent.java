package com.shanghai.logistics.injections.components;

import android.app.Activity;

import com.shanghai.logistics.injections.modules.FragmentModule;
import com.shanghai.logistics.injections.scope.FragmentScope;
import com.shanghai.logistics.ui.logistics_fragment.LogisticsMeFragment;
import com.shanghai.logistics.ui.logistics_fragment.home.LNewOrderFragment;
import com.shanghai.logistics.ui.mian_fragment.AddressListFragment;
import com.shanghai.logistics.ui.user_fragment.UserHomeFragment;
import com.shanghai.logistics.ui.user_fragment.UserMeFragment;


import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
    void inject(UserHomeFragment mUserHomeFragment);
    void inject(UserMeFragment userMeFragment);
    void inject(AddressListFragment addressListFragment);
    void inject(LNewOrderFragment lNewOrderFragment);
    void inject(LogisticsMeFragment logisticsMeFragment);
}
