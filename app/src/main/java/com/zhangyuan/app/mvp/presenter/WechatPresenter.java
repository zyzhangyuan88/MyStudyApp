package com.zhangyuan.app.mvp.presenter;



import android.app.Activity;
import android.util.Log;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.zhangyuan.app.base.RxPresenter;
import com.zhangyuan.app.http.RetrofitHelper;
import com.zhangyuan.app.http.response.WXHttpResponse;
import com.zhangyuan.app.mvp.DataManager;
import com.zhangyuan.app.mvp.contract.WechatContract;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;
import com.zhangyuan.app.mvp.model.prefs.ImplPreferencesHelper;
import com.zhangyuan.app.rxlifecycle.RxActivity;
import com.zhangyuan.app.util.RxUtil;
import com.zhangyuan.app.component.CommonSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by codeest on 16/8/29.
 */

public class WechatPresenter extends RxPresenter<WechatContract.View> implements WechatContract.Presenter {

    private static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;
    private String queryStr = null;

    private DataManager mDataManager;
    private RetrofitHelper retrofitHelper;
    private ImplPreferencesHelper preferencesHelper;

    @Inject
    public WechatPresenter(DataManager mDataManager, RetrofitHelper retrofitHelper,ImplPreferencesHelper preferencesHelper) {
        this.mDataManager = mDataManager;
        this.retrofitHelper = retrofitHelper;
        this.preferencesHelper = preferencesHelper;
    }


    @Override
    public void getWechatData(Activity activity) {
        currentPage = 1;


        addSubscribe(retrofitHelper.fetchWechatListInfo(NUM_OF_PAGE,currentPage)
                .compose(RxUtil.<WXHttpResponse<List<WXItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<WXItemBean>>handleWXResult())
                .compose(((RxActivity)activity).<List<WXItemBean>>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeWith(new CommonSubscriber<List<WXItemBean>>(mView) {
                    @Override
                    public void onNext(List<WXItemBean> wxItemBeen) {
                        mView.showContent(wxItemBeen);
                    }
                })
        );

        addSubscribe(Observable.interval(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(((RxActivity)activity).<Long>bindUntilEvent(ActivityEvent.PAUSE))
                .subscribeWith(new ResourceObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        Log.d("===onNext=====","====test======");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("====onError====","====test======");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("====onComplete====","====test======");
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
