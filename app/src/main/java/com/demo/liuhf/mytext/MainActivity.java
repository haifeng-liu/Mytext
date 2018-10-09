package com.demo.liuhf.mytext;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.liuhf.mybaselibrarys.base.BaseActivity;
import com.demo.liuhf.mybaselibrarys.base.BaseAdapter;
import com.demo.liuhf.mybaselibrarys.base.BaseGuideActivity;
import com.demo.liuhf.mybaselibrarys.base.BaseHolder;
import com.demo.liuhf.mytext.databinding.ActivityMainBinding;
import com.demo.liuhf.mytext.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends BaseActivity {


    ActivityMainBinding binding;
    MyAdapter adapter;
    List<String> data=new ArrayList<>();
    @Override
    public void initview() {
        data.add("11111");
        data.add("12222");
        data.add("13333");
        data.add("14444");

        adapter=new MyAdapter(this,data,R.layout.item);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public View onCreateview() {
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        return binding.getRoot();
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
    class  MyAdapter extends BaseAdapter<String>{
        public MyAdapter(Context context, List<String> data, int layoutId) {
            super(context, data,layoutId);
        }
        @Override
        public void onBind(BaseHolder holder, String s, int position) {
            ItemBinding ibinding= (ItemBinding) holder.getBinding();
            ibinding.tvName.setText(s);
        }
    }
}
