package com.demo.liuhf.mybaselibrarys.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.demo.liuhf.mybaselibrarys.R;


/**
 * Created by Administrator on 2018/3/11.
 */

public class WaitDialog extends ProgressDialog {
    public WaitDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage(context.getText(R.string.wait_dialog_title));
    }
}
