package com.demo.liuhf.mybaselibrarys.http;

import android.app.Activity;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created by Administrator on 2018/3/11.
 */

public class CallServer {

    private static CallServer sInstance;

    public static CallServer getInstance() {
        if (sInstance == null)
            synchronized (CallServer.class) {
                if (sInstance == null)
                    sInstance = new CallServer();
            }
        return sInstance;
    }

    private RequestQueue mRequestQueue;
    private DownloadQueue mDownloadQueue;


    private CallServer() {
        mRequestQueue = NoHttp.newRequestQueue(5);
        mDownloadQueue = NoHttp.newDownloadQueue(3);
    }
    /**
     * 取消这个sign标记的所有请求
     */
    public void cancelBySign(Object sign) {
        mRequestQueue.cancelBySign(sign);
    }
    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        mRequestQueue.add(what, request, listener);
    }
    /**
     * 取消队列中所有请求
     */
    public void cancelAll() {
        mRequestQueue.cancelAll();
    }

    /**
     * 退出app时停止所有请求
     */
    public void stopAll() {
        mRequestQueue.stop();
    }

    public <T> void request(Object activity, int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        mRequestQueue.add(what, request, new HttpResponseListener<>(activity, request, callback, canCancel, isLoading));
    }

    public void download(int what, DownloadRequest request, DownloadListener listener) {
        mDownloadQueue.add(what, request, listener);
    }

}
