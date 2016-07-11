package com.yfw.izlt.main.model.biz;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yfw.izlt.common.Constants;
import com.yfw.izlt.main.model.bean.MUser;
import com.yfw.izlt.main.model.biz.Interface.IMUserBiz;
import com.yfw.izlt.main.model.biz.Interface.OnLoginListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;

/**
 * Created by zlt on 2016/7/11.
 */
public class MUserBiz implements IMUserBiz{
    @Override
    public void login(String userPhone, String password, final OnLoginListener loginListener) {
        String url= Constants.LOGIN_ACCESS;
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("phone",userPhone);
        params.addQueryStringParameter("password",password);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("ii","result_mvp:"+result);
                MUser data=null;
                try {
                    data=new ObjectMapper().readValue(result,MUser.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                    loginListener.loginSuccess(data);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                loginListener.loginFailed();
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
