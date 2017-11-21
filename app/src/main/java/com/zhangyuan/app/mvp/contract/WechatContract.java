package com.zhangyuan.app.mvp.contract;


import android.app.Activity;

import com.zhangyuan.app.base.BasePresenter;
import com.zhangyuan.app.base.BaseView;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;

import java.util.List;

/**
 * Created by codeest on 16/8/29.
 */

public interface WechatContract {

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void showContent(List<WXItemBean> mList);

        void showMoreContent(List<WXItemBean> mList);
    }

    interface Presenter extends BasePresenter<View> {

        void getWechatData(Activity activity);

        void getMoreWechatData();
    }
}
