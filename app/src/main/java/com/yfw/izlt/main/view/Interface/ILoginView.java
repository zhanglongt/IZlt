package com.yfw.izlt.main.view.Interface;

import com.yfw.izlt.main.model.bean.MUser;

/**
 * Created by zlt on 2016/7/11.
 */
public interface ILoginView {
    String getUserPhone();
    String getPassword();
    void toMainManagerActivity(MUser user);
    void showFailedError();
}
