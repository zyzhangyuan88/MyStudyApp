package com.zhangyuan.app.di.component;

import android.app.Activity;


import com.zhangyuan.app.MainActivity;
import com.zhangyuan.app.di.module.ActivityModule;
import com.zhangyuan.app.di.scope.ActivityScope;
import com.zhangyuan.app.ui.activity.WxActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();


    void inject(MainActivity mainActivity);

    void inject(WxActivity wxActivity);


}
