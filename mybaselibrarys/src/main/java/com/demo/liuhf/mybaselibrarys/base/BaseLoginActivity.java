package com.demo.liuhf.mybaselibrarys.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/17.
 * 描述：登陆base类
 * 修改历史：
 */

public abstract class BaseLoginActivity extends AppCompatActivity{

    public  static String savename="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    /**
     * 保存登录信息
     * */

    public void saveLogin(boolean isLogin,String name,String password){
        SharedPreferences sharedPreferences =this.getSharedPreferences(savename!=null?savename:"Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",isLogin);//是否记住密码
        editor.putString("name",name);//
        editor.putString("password",password);
        savedata();
    }

    /**
     * 是否记住密码
     * */
    public  boolean getLogin(){
        SharedPreferences sharedPreferences =this.getSharedPreferences(savename!=null?savename:"Login", MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLogin" ,false);
    }

    /**
     * 获取帐号
     * */
    public String getname(){
        SharedPreferences sharedPreferences =this.getSharedPreferences(savename!=null?savename:"Login", MODE_PRIVATE);
        return sharedPreferences.getString("name",null);
    }
    /**
     * 获取密码
     * */
    public String getpassward(){
        SharedPreferences sharedPreferences =this.getSharedPreferences(savename!=null?savename:"Login", MODE_PRIVATE);
        return sharedPreferences.getString("password",null);
    }

    /**
     * 保存其他信息
     * */
    public abstract void savedata();
}
