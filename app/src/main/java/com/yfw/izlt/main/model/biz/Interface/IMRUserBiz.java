package com.yfw.izlt.main.model.biz.Interface;

/**
 * Created by zlt on 2016/7/11.
 */
public interface IMRUserBiz {
    void register(String userName,String userPhone,String userPassword,OnRegisterListener registerListener);
}
