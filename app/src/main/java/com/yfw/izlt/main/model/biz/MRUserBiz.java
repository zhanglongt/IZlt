package com.yfw.izlt.main.model.biz;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yfw.izlt.common.Constants;
import com.yfw.izlt.common.Toasttool;
import com.yfw.izlt.main.model.bean.MUser;
import com.yfw.izlt.main.model.biz.Interface.IMRUserBiz;
import com.yfw.izlt.main.model.biz.Interface.OnRegisterListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;

/**
 * Created by zlt on 2016/7/11.
 */
public class MRUserBiz implements IMRUserBiz {
    @Override
    public void register(String userName, String userPhone, String userPassword, final OnRegisterListener registerListener) {
        String url= Constants.REGISTER_ACCESS;
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userName",userName);
        params.addBodyParameter("userPass",userPassword);
        params.addBodyParameter("mobile",userPhone);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("ii","result:"+result);
                MUser data=null;
                try {
                    data= new ObjectMapper().readValue(result,MUser.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                registerListener.registerSuccess(data);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
               registerListener.registerFailed();
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
