package com.zhangyuan.app.mvp.presenter;



import com.zhangyuan.app.base.RxPresenter;
import com.zhangyuan.app.http.response.WXHttpResponse;
import com.zhangyuan.app.mvp.DataManager;
import com.zhangyuan.app.mvp.contract.WechatContract;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;
import com.zhangyuan.app.util.RxUtil;
import com.zhangyuan.app.component.CommonSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by codeest on 16/8/29.
 */

public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {

    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private String queryStr = null;

    private DataManager mDataManager;

    @Inject
    public WechatPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getWechatData() {
        currentPage = 1;
        addSubscribe(mDataManager.fetchWechatListInfo(NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WXItemBean>>(mView) {
                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showContent(wxItemBeen);
                    }
                })
        );


//        mView.showLoading();
//        addSubscribe(mDataManager.fetchWechatListInfo(NUM_OF_PAGE,currentPage)
//                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
//                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
//                .subscribeWith(new ResourceSubscriber<List<WXItemBean>>() {
//                    @Override
//                    public void onNext(List<WXItemBean> wxItemBeans) {
//                        mView.hideLoading();
//                        LogUtil.d("=======>:"+wxItemBeans.size());
////                        mView.showContent(wxItemBeans);
//                        mView.stateError();
//
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                        mView.hideLoading();
//                    }
//                }));

    }

    @Override
    public void getMoreWechatData() {
        Flowable<WXHttpResponse<List<WXItemBean>>> observable;
        observable = mDataManager.fetchWechatListInfo(NUM_OF_PAGE,++currentPage);
        addSubscribe(observable
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .subscribeWith(new CommonSubscriber<List<WXItemBean>>(mView, "没有更多了ヽ(≧Д≦)ノ") {
                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showMoreContent(wxItemBeen);
                    }
                })
        );
    }


}
