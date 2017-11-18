package com.zhangyuan.app.di.component;


import com.zhangyuan.app.app.App;
import com.zhangyuan.app.di.module.AppModule;
import com.zhangyuan.app.di.module.HttpModule;
import com.zhangyuan.app.http.RetrofitHelper;
import com.zhangyuan.app.mvp.DataManager;
import com.zhangyuan.app.mvp.model.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
