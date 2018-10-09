package com.demo.liuhf.mybaselibrarys.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.liuhf.mybaselibrarys.http.BaseHttpUtil;


//import butterknife.Bind;
//import butterknife.ButterKnife;


/**
 * Created by liuhaifeng on 2018/3/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private View view;
    private String TAG="BaseActivity";

    public abstract void initview();

    public abstract View onCreateview();
    public abstract Activity  close();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (null == view) {
            view = onCreateview();
        }
        initview();
        Log.d(TAG,close().toString());
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       if(close()!=null){
           BaseHttpUtil.cancle(close());
       }

    }
}
