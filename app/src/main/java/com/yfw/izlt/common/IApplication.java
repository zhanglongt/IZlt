package com.yfw.izlt.common;

import android.app.Application;

import org.xutils.BuildConfig;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import cn.finalteam.okhttpfinal.Part;
import okhttp3.Headers;
import okhttp3.Interceptor;
import org.xutils.x;

/**
 * Created by zlt on 2016/7/7.
 */
public class IApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //okhttpFinal初始化网络请求
        initOkHttpFinal();
        //xUtils初始化网络请求
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

    private void initOkHttpFinal() {
        List<Part> commomParams = new ArrayList<>();
        Headers commonHeaders = new Headers.Builder().build();
        List<Interceptor> interceptorList = new ArrayList<>();
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder()
                .setCommenParams(commomParams)
                .setCommenHeaders(commonHeaders)
                .setTimeout(Constants.REQ_TIMEOUT)
                .setInterceptors(interceptorList)
                //.setCookieJar(CookieJar.NO_COOKIES)
                //.setCertificates(...)
                //.setHostnameVerifier(new SkirtHttpsHostnameVerifier())
                .setDebug(true);
//        addHttps(builder);
        OkHttpFinal.getInstance().init(builder.build());
    }
}
