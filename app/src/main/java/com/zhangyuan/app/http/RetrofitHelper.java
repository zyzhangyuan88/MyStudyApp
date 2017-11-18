package com.zhangyuan.app.http;


import com.zhangyuan.app.app.Constants;
import com.zhangyuan.app.http.api.WeChatApis;
import com.zhangyuan.app.http.response.WXHttpResponse;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 *
 * Created by codeest on 2016/8/3.
 */
public class RetrofitHelper implements HttpHelper {

    private WeChatApis mWechatApiService;

    @Inject
    public RetrofitHelper(WeChatApis wechatApiService) {
        this.mWechatApiService = wechatApiService;
    }


    @Override
    public Flowable<WXHttpResponse<List<WXItemBean>>> fetchWechatListInfo(int num, int page) {
        return mWechatApiService.getWXHot(Constants.KEY_API, num, page);
    }




}
