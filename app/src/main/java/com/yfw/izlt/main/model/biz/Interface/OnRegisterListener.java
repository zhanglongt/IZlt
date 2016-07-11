package com.yfw.izlt.main.model.biz.Interface;

import com.yfw.izlt.main.model.bean.MUser;

/**
 * Created by zlt on 2016/7/11.
 */
public interface OnRegisterListener {
    void registerSuccess(MUser user);
    void registerFailed();
}
