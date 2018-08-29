package com.demo.liuhf.mybaselibrarys.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.liuhf.mybaselibrarys.R;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/29.
 * 描述：自定义搜索框
 * 修改历史：
 */

public class SearchView extends LinearLayout implements View.OnClickListener {

    public  EditText edit_search;
    public  ImageView img_search;
    public  TextView tv_search;
    ImgClickListener imgClickListener;
    TvClickListener tvClickListener;

    public void setTvClickListener(TvClickListener tvClickListener) {
        this.tvClickListener = tvClickListener;
    }

    public void setImgClickListener(ImgClickListener imgClickListener) {
        this.imgClickListener = imgClickListener;
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs,defStyleAttr,0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.searchview,this);
        init();
    }



    private void init(){
    edit_search=findViewById(R.id.edit_search);
    tv_search=findViewById(R.id.tv_search);
    img_search=findViewById(R.id.img_search);
    tv_search.setOnClickListener(this);
    img_search.setOnClickListener(this);
    edit_search.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            if (tv_search.getVisibility()==VISIBLE){
                if (edit_search.getText().equals("")){
                    tv_search.setEnabled(false);
                }
            }
            if (img_search.getVisibility()==VISIBLE){
                if (edit_search.getText().equals("")){
                    img_search.setEnabled(false);
                }
            }
        }
    });
    }


    public EditText getEdit(){
        return edit_search;
    }

    public TextView getText(){
        return tv_search;
    }

    public ImageView getImage(){
        return img_search;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_search) {
                tvClickListener.OnClick(edit_search.getText().toString());
        }else if (i==R.id.img_search){
                imgClickListener.OnClick(edit_search.getText().toString());
        }
    }

    public interface ImgClickListener{
        public void OnClick(String str);
    }

    public interface TvClickListener{
        public void OnClick(String str);
    }

}
