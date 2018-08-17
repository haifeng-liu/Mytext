package com.demo.liuhf.mybaselibrarys.base;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.demo.liuhf.mybaselibrarys.R;
import com.demo.liuhf.mybaselibrarys.databinding.ActivityBasewelcomeBinding;

import java.util.ArrayList;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/17.
 * 描述：引导页父类
 * 修改历史：
 */

public abstract class BaseGuideActivity extends AppCompatActivity {
    private final String TAG = "WELCOME";
    public static VpAdapter vpAdapter;
    public static int[] imgs = null;
    public static ArrayList<ImageView> imageViews;
    private ImageView[] dotViews;//小圆点

    ActivityBasewelcomeBinding basewelcomeBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun) {
            Log.d(TAG, "first");
            //第一次登陆
            initview();
            editor.putBoolean("isFirstRun", false);
            editor.commit();
        } else {
            Log.d(TAG, "Nofirst");

            //非第一次登陆
            NoFirst();
        }


    }





    /**
     * 绑定布局
     */
    private void initview() {
        basewelcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_basewelcome);
        imageViews = new ArrayList<>();
        vpAdapter = new VpAdapter(imageViews);
        basewelcomeBinding.guideViewPager.setAdapter(vpAdapter);
        if (imgs == null) {
            initImag();
            addImage();
            initDots();
            vpAdapter.setImageViewss(imageViews);
        }
        basewelcomeBinding.guideViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotViews.length; i++) {
                    if (position == i) {
                        dotViews[i].setSelected(true);
                    } else {
                        dotViews[i].setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     *添加引导图片
     */
    private void addImage(){
        //设置每一张图片都填充窗口
        ViewPager.LayoutParams mParams = new ViewPager.LayoutParams();
        imageViews = new ArrayList<ImageView>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);//设置布局
            iv.setImageResource(imgs[i]);//为ImageView添加图片资源
            iv.setScaleType(ImageView.ScaleType.FIT_XY);//这里也是一个图片的适配
            imageViews.add(iv);

            if (i==imgs.length-1){
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fisrt();
                    }
                });
            }

        }
    }

    /**
     * 添加小圆点
     * */
    private void initDots() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.dot_Layout);
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(20, 20);
        mParams.setMargins(10, 0, 10, 0);//设置小圆点左右之间的间隔
        dotViews = new ImageView[imgs.length];
        //判断小圆点的数量，从0开始，0表示第一个
        for (int i = 0; i < imageViews.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(mParams);
            imageView.setImageResource(R.drawable.dotselector);
            if (i == 0) {
                imageView.setSelected(true);//默认启动时，选中第一个小圆点
            } else {
                imageView.setSelected(false);
            }
            dotViews[i] = imageView;//得到每个小圆点的引用，用于滑动页面时，（onPageSelected方法中）更改它们的状态。
            layout.addView(imageView);//添加到布局里面显示
        }

    }


    /**
     * 切换图片的适配器
     */
    class VpAdapter extends PagerAdapter {

        private ArrayList<ImageView> imageViewss;

        public VpAdapter(ArrayList<ImageView> imageViews) {
            this.imageViewss = imageViews;
        }

        public void setImageViewss(ArrayList<ImageView> imageViewss) {
            this.imageViewss = imageViewss;
            notifyDataSetChanged();
        }

        /**
         * 获取当前要显示对象的数量
         */
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewss.size();


        }

        /**
         * 判断是否用对象生成界面
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        /**
         * 从ViewGroup中移除当前对象（图片）
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewss.get(position));
        }

        /**
         * 当前要显示的对象（图片）
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewss.get(position));


            return imageViewss.get(position);
        }

    }


    /***
     *添加引导图片
     */
    public abstract void initImag();

    /**
     * 非第一次跳转到欢迎界面
     */
    public abstract void NoFirst();

    /**
     * 第一次点击最后一页跳转的界面
     * */
    public abstract void Fisrt();
}
