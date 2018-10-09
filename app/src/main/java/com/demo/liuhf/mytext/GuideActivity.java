package com.demo.liuhf.mytext;

import android.content.Intent;

import com.demo.liuhf.mybaselibrarys.base.BaseGuideActivity;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/17.
 * 描述：
 * 修改历史：
 */

public class GuideActivity extends BaseGuideActivity {

    @Override
    public void initImag() {
        imgs = new int[]{R.mipmap.ic_launcher, R.mipmap.xiao_xi, R.mipmap.yuan_liao_yuan};
    }

    @Override
    public void Fisrt() {
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }
}
