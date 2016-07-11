package com.yfw.izlt.main.model.biz;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yfw.izlt.common.Constants;
import com.yfw.izlt.main.model.bean.MUser;
import com.yfw.izlt.main.model.biz.Interface.IMLUserBiz;
import com.yfw.izlt.main.model.biz.Interface.OnLoginListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;

/**
 * Created by zlt on 2016/7/11.
 */
public class MLUserBiz implements IMLUserBiz {
    @Override
    public void login(String userPhone, String password, final OnLoginListener loginListener) {//模拟子线程耗时操作
        String url= Constants.LOGIN_ACCESS;
        //xUtils网络请求 json解析不包括
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("phone",userPhone);
        params.addQueryStringParameter("password",password);
        x.http().get(params, new Callback.CommonCallback<String>() {

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

        //okhttpFinal网络请求,,,json解析内部包括
//        cn.finalteam.okhttpfinal.RequestParams params=new cn.finalteam.okhttpfinal.RequestParams();
//        String url1=url+"&phone="+userPhone+"&password="+password;
//        HttpRequest.get(url1,new BaseHttpRequestCallback<MUser>(){
//            @Override
//            protected void onSuccess(MUser mUser) {
//                super.onSuccess(mUser);
//                Log.i("ii","mUser_mvp:"+mUser.getResult());
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg) {
//                super.onFailure(errorCode, msg);
//                Log.i("ii","error_mvp:"+msg);
//            }
//        });
    }
}
