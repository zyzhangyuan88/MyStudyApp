package com.zhangyuan.app.ui.fragment;

import com.zhangyuan.app.base.BaseActivity;
import com.zhangyuan.app.base.BaseFragment;
import com.zhangyuan.app.base.SimpleFragment;
import com.zhangyuan.app.di.component.DaggerAppComponent;
import com.zhangyuan.app.mvp.presenter.WechatPresenter;

/**
 * Created by zhangyuan on 17/11/18.
 */

public class WxFragment extends BaseFragment<WechatPresenter>{

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
