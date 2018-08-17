package com.demo.liuhf.mybaselibrarys.base;

import android.app.Application;
import android.content.Context;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.URLConnectionNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * 创建人：liuhaifeng
 * 时间：2018/4/24.
 * 描述：
 * 修改历史：
 */

public abstract class BaseApplication extends Application {
    private static Application _instance;
    public static Context context;
    /**
     * 请求队列。
     */
    public static RequestQueue mQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        context=this;
        // 如果你需要自定义配置：

        NoHttp.initialize(InitializationConfig.newBuilder(this)
                // 设置全局连接超时时间，单位毫秒，默认10s。
                .connectionTimeout(30 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .cacheStore(
                        new DBCacheStore(this).setEnable(true) // 如果不使用缓存，设置setEnable(false)禁用。
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现。
                .cookieStore(
                        new DBCookieStore(this).setEnable(true) // 如果不维护cookie，设置false禁用。
                )
                // 配置网络层，URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                .networkExecutor(new URLConnectionNetworkExecutor())
                .build()
        );
        getLogin();
    }

    public static RequestQueue getQueue(){
        mQueue=NoHttp.newRequestQueue(1);
        return mQueue;
    }


    public static Application getInstance() {
        return _instance;
    }

    /**
     * 保存登陆信息
     * */
    public abstract void getLogin();


}
