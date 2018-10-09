package com.demo.liuhf.mytext;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.demo.liuhf.mybaselibrarys.base.BaseApplication;
import com.demo.liuhf.mybaselibrarys.base.BaseStartUpActivity;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/13.
 * 描述：
 * 修改历史：
 */

public class App extends BaseApplication{
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

/**
 * 设置网络连接时间  为0时默认为30*1000
 * */
    @Override
    public int connectionTime() {
        return 0;
    }
    /**
     * 设置网络响应时间 为0时默认为30*1000
     * */
    @Override
    public int readTime() {
        return 0;
    }

    @Override
    public void getLogin() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("textlogin", MODE_PRIVATE);
    }

}
