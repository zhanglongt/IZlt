package com.yfw.izlt.common;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;

import org.xutils.BuildConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;
import cn.finalteam.okhttpfinal.Part;
import okhttp3.Headers;
import okhttp3.Interceptor;
import org.xutils.x;

/**
 * 全局变量
 * Created by zlt on 2016/7/7.
 */
public class IApplication extends Application {
    /** 系统初始化配置文件操作器 */
    private SharedPreferences sysInitSharedPreferences;
    /** 记录用户登录后的密钥KEY */
    private String loginKey;
    @Override
    public void onCreate() {
        super.onCreate();
        //okhttpFinal初始化网络请求
        initOkHttpFinal();
        //xUtils初始化网络请求
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

        sysInitSharedPreferences = getSharedPreferences(Constants.SYSTEM_INIT_FILE_NAME, MODE_PRIVATE);
        loginKey = sysInitSharedPreferences.getString("loginKey", "");
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

    public String getLoginKey() {
        String loginKey = sysInitSharedPreferences.getString("loginKey", "");
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
        sysInitSharedPreferences.edit().putString("loginKey", this.loginKey).commit();
    }

    /** 创建SD卡缓存目录 */
    private void createCacheDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            File f = new File(Constants.CACHE_DIR);
            if (f.exists()) {
                System.out.println("SD卡缓存目录:已存在!");
            } else {
                if (f.mkdirs()) {
                    System.out.println("SD卡缓存目录:" + f.getAbsolutePath()
                            + "已创建!");
                } else {
                    System.out.println("SD卡缓存目录:创建失败!");
                }
            }

            File ff = new File(Constants.CACHE_DIR_IMAGE);
            if (ff.exists()) {
                System.out.println("SD卡照片缓存目录:已存在!");
            } else {
                if (ff.mkdirs()) {
                    System.out.println("SD卡照片缓存目录:" + ff.getAbsolutePath()
                            + "已创建!");
                } else {
                    System.out.println("SD卡照片缓存目录:创建失败!");
                }
            }

            File fff = new File(Constants.CACHE_DIR_UPLOADING_IMG);
            if (fff.exists()) {
                System.out.println("SD卡照片缓存目录:已存在!");
            } else {
                if (fff.mkdirs()) {
                    System.out.println("SD卡照片缓存目录:" + fff.getAbsolutePath()
                            + "已创建!");
                } else {
                    System.out.println("SD卡照片缓存目录:创建失败!");
                }
            }
        }
    }

}
