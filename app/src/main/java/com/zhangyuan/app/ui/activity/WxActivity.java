package com.zhangyuan.app.ui.activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.zhangyuan.app.R;
import com.zhangyuan.app.base.RootActivity;
import com.zhangyuan.app.mvp.contract.WechatContract;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;
import com.zhangyuan.app.mvp.presenter.WechatPresenter;

import java.util.List;

import butterknife.BindView;

/**
 *
 * Created by zhangyuan on 17/11/18.
 */

public class WxActivity extends RootActivity<WechatPresenter> implements WechatContract.View {
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

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
        mPresenter.getWechatData();
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
        if(swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }
}
