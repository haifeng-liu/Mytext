package com.demo.liuhf.mybaselibrarys.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;


import static android.app.ProgressDialog.STYLE_SPINNER;

/**
 * 创建人：liuhaifeng
 * 时间：2018/4/24.
 * 描述：
 * 修改历史：
 */

public class DialogUtil  {
    public static Context mcontext;
    public DialogUtil(Context context) {
        this.mcontext=context;
    }
//    public static void Wait(){
//        ProgressDialog dialog=new ProgressDialog(mcontext);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setProgressStyle(STYLE_SPINNER);
//        dialog.setMessage(mcontext.getText(R.string.wait_dialog_title));
//    }

    
}
