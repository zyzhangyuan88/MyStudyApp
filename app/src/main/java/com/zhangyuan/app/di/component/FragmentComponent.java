package com.zhangyuan.app.di.component;

import android.app.Activity;


import com.zhangyuan.app.di.module.FragmentModule;
import com.zhangyuan.app.di.scope.FragmentScope;
import com.zhangyuan.app.ui.fragment.WxFragment;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(WxFragment wxFragment);

}
