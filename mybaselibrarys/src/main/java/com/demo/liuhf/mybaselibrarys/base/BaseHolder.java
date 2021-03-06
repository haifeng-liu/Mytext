package com.demo.liuhf.mybaselibrarys.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建人：liuhaifeng
 * 时间：2018/4/24.
 * 描述：
 * 修改历史：
 */

public class BaseHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    B binding;
    View itemView;
    SparseArray<View> views;//存放Item中的控件
    public BaseHolder(B binding){
        super(binding.getRoot());
        this.binding = binding;
        views = new SparseArray<View>();
    }

    public BaseHolder(View itemView){
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<View>();
    }
    //供adapter调用，返回holder
    public static <T extends BaseHolder> T getHolder(Context cxt, ViewGroup parent, int layoutId){
        return (T) new BaseHolder(DataBindingUtil.inflate(LayoutInflater.from(cxt),layoutId,parent, false));
    }


    //根据Item中的控件Id获取控件
    public <T extends View> T getView(int viewId){
        View childreView = views.get(viewId);
        if (childreView == null){
            childreView = itemView.findViewById(viewId);
            views.put(viewId, childreView);
        }
        return (T) childreView;
    }

    //返回binding
    public B getBinding() {
        return binding;
    }

    //根据Item中的控件Id向控件添加事件监听
    public BaseHolder setOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
