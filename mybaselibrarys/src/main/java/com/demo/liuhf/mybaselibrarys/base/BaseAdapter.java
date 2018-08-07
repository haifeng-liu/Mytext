package com.demo.liuhf.mybaselibrarys.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 创建人：liuhaifeng
 * 时间：2018/4/24.
 * 描述：
 * 修改历史：
 */

public  abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder> {

    Context context;
    List<T> data;//引入泛型
    int layoutId;
    public BaseAdapter(Context context, List<T> data, int layoutId) {
        this.context = context;
        this.data = data;
        this.layoutId = layoutId;
    }
    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }
    public List<T> getData() {
        return this.data;
    }
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //从holder基类中获取holder
        return BaseHolder.getHolder(context, parent, layoutId);
    }
    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        //调用由子类实现的抽象方法，将操作下放到子类中
        onBind(holder, data.get(position), position);
    }
    //抽象方法，让子类继承实现
    public abstract void onBind(BaseHolder holder, T t, int position);

}
