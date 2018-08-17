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
//        Beta.installTinker(this);
    }


    @Override
    public void getLogin() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("textlogin", MODE_PRIVATE);
    }

}
