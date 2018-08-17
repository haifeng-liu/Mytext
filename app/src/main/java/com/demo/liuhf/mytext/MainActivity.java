package com.demo.liuhf.mytext;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.liuhf.mybaselibrarys.base.BaseGuideActivity;

import java.util.ArrayList;

public  class MainActivity extends BaseGuideActivity {

    @Override
    public void initImag() {

    }

    @Override
    public void Fisrt() {
        Toast.makeText(MainActivity.this,"点击跳转了",Toast.LENGTH_SHORT).show();
    }

}
