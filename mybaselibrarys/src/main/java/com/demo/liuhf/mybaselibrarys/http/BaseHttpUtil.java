package com.demo.liuhf.mybaselibrarys.http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import com.demo.liuhf.mybaselibrarys.utils.SSLContextUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.ssl.SSLUtils;

import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * 创建人：liuhaifeng
 * 时间：2018/4/24.
 * 描述：HttpUtils
 * 修改历史：
 */

public class BaseHttpUtil{

    public static void GetHttps(Object object,int what, String URL, Map<String, Object> map, HttpListener<String> callback) {

        Request<String> httpsRequest = NoHttp.createStringRequest(URL, RequestMethod.GET);
        if (null != map) {
            httpsRequest.add(map);
        }
        SSLContext sslContext = SSLContextUtil.getSSLContext();
        // 主要是需要一个SocketFactory对象，这个对象是java通用的，具体用法还请Google、Baidu。
        if (sslContext != null) {
            // SSLUtils.fixSSLLowerThanLollipop 可修复在4.x中不支持TLSv1和TLSv1.1的问题。
            SSLSocketFactory socketFactory = SSLUtils.fixSSLLowerThanLollipop(sslContext.getSocketFactory());
            httpsRequest.setSSLSocketFactory(socketFactory);

        }
        Log.d("Get--------->NoHttp", httpsRequest.url());
        request(object,what, httpsRequest, callback, false, true);
    }

    public static void GetHttp(Object object,int what,String URL, Map<String, Object> map, HttpListener<String> callback) {
        Request<String> httpsRequest = NoHttp.createStringRequest(URL, RequestMethod.GET);

        if (null != map) {
            httpsRequest.add(map);
        }

        // 主要是需要一个SocketFactory对象，这个对象是java通用的，具体用法还请Google、Baidu。
        Log.d("Get--------->NoHttp", httpsRequest.url());
        request(object,what, httpsRequest, callback, false, true);
    }

    public static void PostHttps(Object object,int what,String URL,  Map<String, Object> map, HttpListener<String> callback) {
        Request<String> httpsRequest = NoHttp.createStringRequest(URL, RequestMethod.POST);
        if (null != map) {
            httpsRequest.add(map);
        }
        SSLContext sslContext = SSLContextUtil.getSSLContext();
        // 主要是需要一个SocketFactory对象，这个对象是java通用的，具体用法还请Google、Baidu。
        if (sslContext != null) {
            // SSLUtils.fixSSLLowerThanLollipop 可修复在4.x中不支持TLSv1和TLSv1.1的问题。
            SSLSocketFactory socketFactory = SSLUtils.fixSSLLowerThanLollipop(sslContext.getSocketFactory());
            httpsRequest.setSSLSocketFactory(socketFactory);
            Log.d("Get--------->NoHttp", httpsRequest.url());
        }
        Log.d("Post--------->NoHttp", httpsRequest.url());
        request(object,what, httpsRequest, callback, false, true);
    }

    public static void PostHttp(Object object,int what,String URL,  Map<String, Object> map, HttpListener<String> callback) {
        Request<String> httpsRequest = NoHttp.createStringRequest(URL, RequestMethod.POST);
        if (null != map) {
            httpsRequest.add(map);
        }
        SSLContext sslContext = SSLContextUtil.getSSLContext();
        Log.d("Post--------->NoHttp", httpsRequest.url());
        request(object,what, httpsRequest, callback, false, true);
    }

    public static void DownloadFile(String url, String path, String filename, boolean isa, boolean isb, DownloadListener listener){
        DownloadRequest request=NoHttp.createDownloadRequest(url,path,filename,isa,isb);
        CallServer.getInstance().download(0,request,listener);
    }

    /**
     * 发起请求。
     *
     * @param what      what.
     * @param request   请求对象。
     * @param callback  回调函数。
     * @param canCancel 是否能被用户取消。
     * @param isLoading 实现显示加载框。
     * @param <T>       想请求到的数据类型。
     */
    public static <T> void request(Object object,int what, Request<T> request, HttpListener<T> callback,
                                   boolean canCancel, boolean isLoading) {
        request.setCancelSign(object);
        CallServer callSever = CallServer.getInstance();
        callSever.request(object,what,request,callback,canCancel,isLoading);
    }

}
