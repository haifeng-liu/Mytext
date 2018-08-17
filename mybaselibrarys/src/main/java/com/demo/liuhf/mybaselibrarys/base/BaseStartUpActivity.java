package com.demo.liuhf.mybaselibrarys.base;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.demo.liuhf.mybaselibrarys.R;
import com.demo.liuhf.mybaselibrarys.databinding.ActivityBasestartupBinding;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/17.
 * 描述：启动页
 * 修改历史：
 */

public abstract class BaseStartUpActivity extends AppCompatActivity {
    private final String TAG = "StartUp";
    public static String savename="";
    ActivityBasestartupBinding binding;
    public static int imagid = 0;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_basestartup);
        if (imagid == 0) {
            addimg();
            binding.startimage.setImageResource(imagid);
        }
        binding.startimage.setImageResource(imagid);


        new Thread(new Runnable() {
            @Override
            public void run() {
                //初始化操作
                init();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences sharedPreferences = BaseStartUpActivity.this.getSharedPreferences(savename!=null?savename:"share", MODE_PRIVATE);
                        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (isFirstRun) {
                            Log.d(TAG, "first");
                            //第一次登陆
                            goGuide();
                            editor.putBoolean("isFirstRun", false);
                            editor.commit();
                        } else {
                            Log.d(TAG, "Nofirst");
                            //非第一次登陆
                            goother();
                        }
                    }
                });
            }
        }).start();


    }

    /**
     * 添加加载页图片
     */
    public abstract void addimg();

    /**
     * 添加需要初始化或网络数据的加载
     */
    public abstract void init();

    /**
     * 跳转到引导页
     */
    public abstract void goGuide();

    /**
     * 跳转到其他界面
     */
    public abstract void goother();


}
