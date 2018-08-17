package com.demo.liuhf.mytext;

import android.content.Intent;

import com.demo.liuhf.mybaselibrarys.base.BaseStartUpActivity;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/17.
 * 描述：
 * 修改历史：
 */

public class StartUpActivity extends BaseStartUpActivity {
    @Override
    public void addimg() {
        imagid = R.mipmap.ic_launcher;
        savename = "textshare";
    }

    @Override
    public void init() {

    }

    @Override
    public void goGuide() {
        startActivity(new Intent(this, GuideActivity.class));
    }

    @Override
    public void goother() {

    }
}
