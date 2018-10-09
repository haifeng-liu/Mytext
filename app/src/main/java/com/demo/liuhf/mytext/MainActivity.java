package com.demo.liuhf.mytext;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.liuhf.mybaselibrarys.base.BaseActivity;
import com.demo.liuhf.mybaselibrarys.base.BaseGuideActivity;

import java.util.ArrayList;

public  class MainActivity extends BaseActivity {


    @Override
    public void initview() {

    }

    @Override
    public View onCreateview() {
        return null;
    }

    @Override
    public Activity close() {
        return MainActivity.this;
    }
    private long firstPressedTime;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis()-firstPressedTime<2000){
            super.onBackPressed();
        }else{
            Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
            firstPressedTime=System.currentTimeMillis();
        }
    }

}
