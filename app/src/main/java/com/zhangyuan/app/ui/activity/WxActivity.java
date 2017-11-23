package com.zhangyuan.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.zhangyuan.app.R;
import com.zhangyuan.app.app.App;
import com.zhangyuan.app.base.RootActivity;
import com.zhangyuan.app.mvp.contract.WechatContract;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;
import com.zhangyuan.app.mvp.model.prefs.ImplPreferencesHelper;
import com.zhangyuan.app.mvp.presenter.WechatPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by zhangyuan on 17/11/18.
 */

public class WxActivity extends RootActivity<WechatPresenter> implements WechatContract.View {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.content_frame)
    FrameLayout contentFrame;


    @Override
    protected int getLayout() {
        return R.layout.wx_activity;
    }

    @Override
    protected void initInject() {

        getActivityComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getWechatData(this);
//        ImplPreferencesHelper implPreferencesHelper = App.getAppComponent().preferencesHelper();
//        implPreferencesHelper.setVersionPoint(false);
        contentFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WxActivity.this.startActivity(new Intent(WxActivity.this, TestActivity.class));
            }
        });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showContent(List<WXItemBean> mList) {


    }

    @Override
    public void showMoreContent(List<WXItemBean> mList) {

    }

    @Override
    public void stateError() {
        super.stateError();
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

}
