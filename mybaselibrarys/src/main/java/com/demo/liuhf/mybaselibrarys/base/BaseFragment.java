package com.demo.liuhf.mybaselibrarys.base;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


//
//import butterknife.Bind;
//import butterknife.ButterKnife;

/**
 * Created by liuhaifeng on 2018/3/11.
 */

public abstract class BaseFragment extends Fragment {
    public View v;
    public LinearLayout lines;
    public ImageView imgErrors;

    public abstract void initView();

    protected abstract View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        if (null != v) {
            ViewGroup group = (ViewGroup) v.getParent();
            if (group != null) {
                group.removeView(v);
            }
        } else {

           v= getContentView(inflater, container, savedInstanceState);
        }


        initView();

        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }
}
