package com.demo.liuhf.mytext;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.demo.liuhf.mybaselibrarys.base.BaseLoginActivity;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/17.
 * 描述：
 * 修改历史：
 */

public class LoginActivity extends BaseLoginActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this,"跳转main",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"跳转main");
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void savedata() {

    }
}
