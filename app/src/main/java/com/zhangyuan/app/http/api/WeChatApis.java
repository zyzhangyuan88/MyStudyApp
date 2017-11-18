package com.zhangyuan.app.http.api;


import com.zhangyuan.app.http.response.WXHttpResponse;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by codeest on 16/8/28.
 */

public interface WeChatApis {

    String HOST = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Flowable<WXHttpResponse<List<WXItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

}
