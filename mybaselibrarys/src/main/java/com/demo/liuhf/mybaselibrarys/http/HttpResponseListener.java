package com.demo.liuhf.mybaselibrarys.http;

import android.app.Activity;
import android.content.DialogInterface;


import com.demo.liuhf.mybaselibrarys.R;
import com.demo.liuhf.mybaselibrarys.utils.ToastUtils;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2018/3/11.
 */

public class HttpResponseListener <T> implements OnResponseListener<T> {
    private Object mActivity;
    /**
     * Dialog.
     */
//    private WaitDialog mWaitDialog;
    /**
     * Request.
     */
    private Request<?> mRequest;
    /**
     * 结果回调.
     */
    private HttpListener<T> callback;

    /**
     * @param activity     context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     */
    public HttpResponseListener(Object activity, Request<?> request, HttpListener<T> httpCallback, boolean canCancel) {
        this.mActivity = activity;
        this.mRequest = request;
//        if (activity != null && isLoading) {
//            mWaitDialog = new WaitDialog(activity);
//            mWaitDialog.setCancelable(canCancel);
//            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    mRequest.cancel();
//                }
//            });
//        }
        this.callback = httpCallback;
    }

    /**
     * 开始请求, 这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {
//        if (mWaitDialog != null && !mActivity.isFinishing() && !mWaitDialog.isShowing())
//            mWaitDialog.show();
    }

    /**
     * 结束请求, 这里关闭dialog.
     */
    @Override
    public void onFinish(int what) {
//        if (mWaitDialog != null && mWaitDialog.isShowing())
//            mWaitDialog.dismiss();
    }

    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        if (callback != null) {
            // 这里判断一下http响应码，这个响应码问下你们的服务端你们的状态有几种，一般是200成功。
            // w3c标准http响应码：http://www.w3school.com.cn/tags/html_ref_httpmessages.asp

            callback.onSucceed(what, response.get().toString());
        }
    }

    /**
     * 失败回调.
     */
    @Override
    public void onFailed(int what, Response<T> response) {
        Exception exception = response.getException();
        if (exception instanceof NetworkError) {// 网络不好
            ToastUtils.showShort(R.string.error_please_check_network);
//            show(BaseApplication.getInstance(), R.string.error_please_check_network);
        } else if (exception instanceof TimeoutError) {// 请求超时
            ToastUtils.showShort(R.string.error_timeout);

//            Toast.show(BaseApplication.getInstance(), R.string.error_timeout);
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            ToastUtils.showShort(R.string.error_not_found_server);

//            Toast.show(BaseApplication.getInstance(), R.string.error_not_found_server);
        } else if (exception instanceof URLError) {// URL是错的
            ToastUtils.showShort(R.string.error_url_error);

//            Toast.show(BaseApplication.getInstance(), R.string.error_url_error);
        } else if (exception instanceof NotFoundCacheError) {
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
            // 没有缓存一般不提示用户，如果需要随你。
        } else {
            ToastUtils.showShort( R.string.error_unknow);

//            Toast.show(BaseApplication.getInstance(), R.string.error_unknow);
        }
        Logger.e("错误：" + exception.getMessage());
        if (callback != null)
            callback.onFailed(what,  response.getException());
    }
}
