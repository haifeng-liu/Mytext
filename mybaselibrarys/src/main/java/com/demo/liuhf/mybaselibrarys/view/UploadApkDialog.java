package com.demo.liuhf.mybaselibrarys.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.liuhf.mybaselibrarys.R;
import com.demo.liuhf.mybaselibrarys.http.BaseHttpUtil;
import com.demo.liuhf.mybaselibrarys.utils.ConvertUtils;
import com.demo.liuhf.mybaselibrarys.utils.DateUtils;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.download.DownloadListener;

import java.io.File;

/**
 * 创建人：liuhaifeng
 * 时间：2018/8/10.
 * 描述：
 * 修改历史：
 */

public class UploadApkDialog extends AlertDialog implements View.OnClickListener {

    private final String TAG="UploadApkDialog";
    Context context;
    ImageView img_logo;
    TextView tv_codenum, tv_apksize, tv_time, tv_conter, tv_ljxz, tv_xczs;
    LinearLayout lin_btn,lin_seekbar;
    ProgressBar seekBar;

    private String URL=null,SIZE=null;

    public UploadApkDialog(Context context) {
        super(context);
        this.context = context;
    }

    public UploadApkDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;

    }

    public UploadApkDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_uploadapk);
        init();
    }

    private void init() {
        img_logo = findViewById(R.id.img_logo);
        tv_apksize = findViewById(R.id.tv_apksize);
        tv_codenum = findViewById(R.id.tv_codenum);
        tv_conter = findViewById(R.id.tv_conter);
        tv_ljxz = findViewById(R.id.tv_ljgx);
        tv_xczs = findViewById(R.id.tv_xczs);
        tv_time = findViewById(R.id.tv_time);
        lin_btn=findViewById(R.id.lin_btn);
        lin_seekbar=findViewById(R.id.lin_bar);
        seekBar=findViewById(R.id.progressbar);
        tv_ljxz.setOnClickListener(this);
        tv_xczs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_ljgx) {
            lin_seekbar.setVisibility(View.VISIBLE);
            lin_btn.setVisibility(View.GONE);
            if (URL== null){
                dismiss();
                return;
            }

            BaseHttpUtil.DownloadFile(URL,Environment.getExternalStorageDirectory()+"/JuNongTomato/","base.apk",false,false, new DownloadListener() {

                @Override
                public void onDownloadError(int what, Exception exception) {
                    Log.d(TAG,exception.getMessage()+"");
                    dismiss();

                }

                @Override
                public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

                }

                @Override
                public void onProgress(int what, int progress, long fileCount, long speed) {
                    Log.d(TAG,progress+"");
//                    int i=(Integer.parseInt(fileCount+"")/Integer.parseInt(SIZE))*100;
//                    Log.d(TAG,i+"");

                    seekBar.setProgress(progress);
                }

                @Override
                public void onFinish(int what, String filePath) {
                    Log.d(TAG,filePath);
                    String fileName = filePath;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
                    context.startActivity(intent);
                    dismiss();

                }

                @Override
                public void onCancel(int what) {

                }
            });
        }
        if (i == R.id.tv_xczs) {
            this.dismiss();
        }
    }
    public void setdata(UpdataBean data){
        if (data==null){
            return;
        }
        tv_time.setText(DateUtils.getSelf().getTimeStr(data.getDate(),"yyyy-MM-dd"));
        tv_apksize.setText(ConvertUtils.byte2FitMemorySize(Long.parseLong(data.getSize())));
        tv_codenum.setText(data.getVodeNum());
        SIZE= data.getSize();
        URL=data.getUrl();
        String[] dat=data.getConter().split(";");
        String str="";
        for (String d:dat){
            str=str+d+"\n";
        }

        tv_conter.setText(str);
        Glide.with(context).load(data.getImg()).into(img_logo);
        if (data.isIsupdata()) {
            tv_xczs.setVisibility(View.GONE);
        }else{
            tv_xczs.setVisibility(View.VISIBLE);
        }



    }



}
