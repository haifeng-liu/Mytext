package com.demo.liuhf.mybaselibrarys.utils.SQLUtils;

import android.content.Context;

/**
 * 创建人：liuhaifeng
 * 时间：2018/4/24.
 * 描述：数据库工具类
 * 修改历史：
 */

public class DatabaseUtils {
    private static  MyOpenHelper mHelper;

    private DatabaseUtils(){
    }

    /**
     * 一般来说这里的initHelper放到application中去初始化
     * 当然也可以在项目运行阶段初始化
     */
    public static void initHelper(Context context, String name){
        if(mHelper == null){
            mHelper = new MyOpenHelper(context,name);
        }
    }
    public static MyOpenHelper getHelper(){
        if(mHelper == null){
            new RuntimeException("MyOpenHelper is null,No init it");
        }
        return mHelper;
    }
}
