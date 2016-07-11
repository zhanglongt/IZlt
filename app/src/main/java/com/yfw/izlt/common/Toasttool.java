package com.yfw.izlt.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zlt on 2016/7/11.
 */
public class Toasttool {
    /**
     *
     * @param context  The Class's context , where  want to use this tool
     * @param message  The message will be show
     */
    public static void MyToast(Context context , String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
