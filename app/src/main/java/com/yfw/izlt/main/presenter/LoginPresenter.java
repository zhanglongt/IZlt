package com.yfw.izlt.main.presenter;
import com.yfw.izlt.main.model.bean.MUser;
import com.yfw.izlt.main.model.biz.Interface.IMUserBiz;
import com.yfw.izlt.main.model.biz.Interface.OnLoginListener;
import com.yfw.izlt.main.model.biz.MUserBiz;
import com.yfw.izlt.main.view.Interface.ILoginView;

/**
 * Created by zlt on 2016/7/11.
 */
public class LoginPresenter {
      private ILoginView iLoginView;
      private IMUserBiz imUserBiz;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        imUserBiz=new MUserBiz();
    }
    public void Login(){
        imUserBiz.login(iLoginView.getUserPhone(), iLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(MUser user) {
                iLoginView.toMainManagerActivity(user);
            }

            @Override
            public void loginFailed() {
                iLoginView.showFailedError();
            }
        });
    }
}
