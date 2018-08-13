package com.demo.liuhf.mytext;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.demo.liuhf.mybaselibrarys.base.BaseApplication;

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
}
