package com.yfw.izlt.main.model.biz.Interface;

/**
 * Created by zlt on 2016/7/11.
 */
public interface IMUserBiz {
     void login(String userPhone, String password, OnLoginListener loginListener);
}
