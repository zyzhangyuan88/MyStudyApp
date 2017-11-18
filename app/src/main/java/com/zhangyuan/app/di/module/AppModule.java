package com.zhangyuan.app.di.module;



import com.zhangyuan.app.app.App;
import com.zhangyuan.app.http.HttpHelper;
import com.zhangyuan.app.http.RetrofitHelper;
import com.zhangyuan.app.mvp.DataManager;
import com.zhangyuan.app.mvp.model.prefs.ImplPreferencesHelper;
import com.zhangyuan.app.mvp.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }



    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, preferencesHelper);
    }
}
