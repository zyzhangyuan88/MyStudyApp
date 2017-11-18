package com.zhangyuan.app.http;




import com.zhangyuan.app.http.response.WXHttpResponse;
import com.zhangyuan.app.mvp.model.bean.WXItemBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public interface HttpHelper {


    Flowable<WXHttpResponse<List<WXItemBean>>> fetchWechatListInfo(int num, int page);


}
