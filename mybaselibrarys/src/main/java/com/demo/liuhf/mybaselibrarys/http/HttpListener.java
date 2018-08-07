package com.demo.liuhf.mybaselibrarys.http;

import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2018/3/11.
 */

public interface HttpListener<T> {

    void onSucceed(int what, String data);

    void onFailed(int what, Exception data);
}
