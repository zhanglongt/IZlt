package com.yfw.izlt.main.presenter;

import com.yfw.izlt.main.model.bean.MUser;
import com.yfw.izlt.main.model.biz.Interface.IMRUserBiz;
import com.yfw.izlt.main.model.biz.Interface.OnRegisterListener;
import com.yfw.izlt.main.model.biz.MRUserBiz;
import com.yfw.izlt.main.view.Interface.IRegisterView;

/**
 * Created by zlt on 2016/7/11.
 */
public class RegisterPresenter {
       private IRegisterView iRegisterView;
       private IMRUserBiz imrUserBiz;

       public RegisterPresenter(IRegisterView iRegisterView) {
              this.iRegisterView = iRegisterView;
              imrUserBiz=new MRUserBiz();
       }
       public void register(){
              imrUserBiz.register(iRegisterView.getUserName(), iRegisterView.getUserPhone(), iRegisterView.getUserPassword(), new OnRegisterListener() {
                     @Override
                     public void registerSuccess(MUser user) {
                            iRegisterView.toMainManagerActivity(user);
                     }

                     @Override
                     public void registerFailed() {
                             iRegisterView.showFailedError();
                     }
              });
       }
}
