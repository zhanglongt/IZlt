package com.yfw.izlt.common;

import android.os.Environment;

/**
 * 常量类
 * Created by zlt on 2016/7/7.
 */
public class Constants {
    /**
     * 系统初始化配置文件名
     */
    public static final String SYSTEM_INIT_FILE_NAME = "sysini";
    /**
     * 本地缓存目录
     */
    public static final String CACHE_DIR;
    /**
     * 图片缓存目录
     */
    public static final String CACHE_DIR_IMAGE;
    /**
     * 待上传图片缓存目录
     */
    public static final String CACHE_DIR_UPLOADING_IMG;
    /**
     * 表情缓存目录
     */
    public static final String CACHE_DIR_SMILEY;
    /**
     * 网络请求超时
     */
    public static final int REQ_TIMEOUT = 35000;
    /**
     * 登录请求接口
     */
    public static final String LOGIN_ACCESS="http://192.168.0.147/create.php?type=1";
    /**
     * 注册请求接口
     */
    public static final String REGISTER_ACCESS="http://192.168.0.147/create.php?type=2";
    /**
     * 登录成功广播返回标识
     */
    public static final String LOGIN_SUCCESS_URL = "1";

    public static String PhotoDir = Environment.getExternalStorageDirectory() + "/PhotoDemo/image/";

    static {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ShopNC/";
        } else {
            CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/ShopNC/";
        }

        CACHE_DIR_SMILEY = CACHE_DIR + "/smiley";
        CACHE_DIR_IMAGE = CACHE_DIR + "/pic";
        CACHE_DIR_UPLOADING_IMG = CACHE_DIR + "/uploading_img";
    }
}
