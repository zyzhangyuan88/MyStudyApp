package com.zhangyuan.app.mvp;


import com.zhangyuan.app.http.HttpHelper;
import com.zhangyuan.app.http.response.WXHttpResponse;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;
import com.zhangyuan.app.mvp.model.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @desciption:
 */

public class DataManager implements HttpHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper,PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mPreferencesHelper = preferencesHelper;
    }


    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean state) {

    }

    @Override
    public boolean getNoImageState() {
        return false;
    }

    @Override
    public void setNoImageState(boolean state) {

    }

    @Override
    public boolean getAutoCacheState() {
        return false;
    }

    @Override
    public void setAutoCacheState(boolean state) {

    }

    @Override
    public int getCurrentItem() {
        return 0;
    }

    @Override
    public void setCurrentItem(int item) {

    }

    @Override
    public boolean getLikePoint() {
        return false;
    }

    @Override
    public void setLikePoint(boolean isFirst) {

    }

    @Override
    public boolean getVersionPoint() {
        return false;
    }

    @Override
    public void setVersionPoint(boolean isFirst) {

    }

    @Override
    public boolean getManagerPoint() {
        return false;
    }

    @Override
    public void setManagerPoint(boolean isFirst) {

    }


    @Override
    public Flowable<WXHttpResponse<List<WXItemBean>>> fetchWechatListInfo(int num, int page) {
        return mHttpHelper.fetchWechatListInfo(num, page);
    }
}
