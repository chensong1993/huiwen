package com.shanghai.logistics.injections.components;

import com.shanghai.logistics.app.App;
import com.shanghai.logistics.injections.modules.AppModule;
import com.shanghai.logistics.injections.modules.HttpModule;
import com.shanghai.logistics.models.DataManager;
import com.shanghai.logistics.models.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();

    DataManager getDataManager();

    RetrofitHelper retrofitHelper();



}
