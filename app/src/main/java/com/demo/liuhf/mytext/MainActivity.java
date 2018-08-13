package com.demo.liuhf.mytext;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.demo.liuhf.mybaselibrarys.view.UpdataBean;
import com.demo.liuhf.mybaselibrarys.view.UploadApkDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UploadApkDialog dialog=new UploadApkDialog(this,R.style.testDlg);
        UpdataBean updataBean=new UpdataBean();
        updataBean.setConter("1、运营报告优化;2、农机作业记录优化");
        updataBean.setDate("1533868519000");
        updataBean.setImg("https://s.beta.gtimg.com/rdmimg/exp/image2/2018/06/27/_ee24880c-ce78-404d-bae2-375ee581b938.png");
        updataBean.setSize("24582599");
        updataBean.setUrl("https://s.beta.myapp.com/myapp/rdmexp/exp/file2/2018/08/10/modeljunongtomato_1.2.2_346de463-6d1b-5be8-a36f-758bce2f8096.apk");
        updataBean.setVodeNum("1.2.2");
        updataBean.setIsupdata(false);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.show();
        dialog.setdata(updataBean);
    }
}
