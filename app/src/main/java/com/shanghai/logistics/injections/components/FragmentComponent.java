package com.shanghai.logistics.injections.components;

import android.app.Activity;

import com.shanghai.logistics.injections.modules.FragmentModule;
import com.shanghai.logistics.injections.scope.FragmentScope;


import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

}
