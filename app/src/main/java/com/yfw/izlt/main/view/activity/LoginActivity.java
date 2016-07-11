package com.yfw.izlt.main.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.common.Constants;
import com.yfw.izlt.common.Toasttool;
import com.yfw.izlt.main.model.bean.MUser;
import com.yfw.izlt.main.presenter.LoginPresenter;
import com.yfw.izlt.main.view.Interface.ILoginView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements ILoginView{
    @ViewInject(R.id.et_account)
    private EditText account;
    @ViewInject(R.id.et_pass)
    private EditText pass;
    private LoginPresenter mUserLoginPresenter = new LoginPresenter(this);
    @Event({R.id.tvRegister,R.id.login_submitID})
    private void getClick(View view){
       switch (view.getId()){
           case R.id.tvRegister:
               Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
               startActivity(intent);
               break;
           case R.id.login_submitID:
               mUserLoginPresenter.Login();
               break;
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getUserPhone() {
        return account.getText().toString();
    }

    @Override
    public String getPassword() {
        return pass.getText().toString();
    }

    @Override
    public void toMainManagerActivity(MUser user) {
            if(user.getResult().equals("right")){
                new Toasttool().MyToast(x.app(),"成功登录");
            }else {
                new Toasttool().MyToast(x.app(),"输入有误，请重试！");
            }
    }

    @Override
    public void showFailedError() {
            new Toasttool().MyToast(x.app(),"网络连接失败！");
    }
}
