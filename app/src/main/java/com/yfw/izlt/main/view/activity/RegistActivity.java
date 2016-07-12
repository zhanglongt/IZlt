package com.yfw.izlt.main.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.common.Constants;
import com.yfw.izlt.common.Toasttool;
import com.yfw.izlt.main.model.bean.MUser;
import com.yfw.izlt.main.presenter.RegisterPresenter;
import com.yfw.izlt.main.view.Interface.IRegisterView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@ContentView(R.layout.activity_regist)
public class RegistActivity extends BaseActivity implements IRegisterView{
//    private TextView tvCommonTitle;
//    private ImageView ivBack;
//    private EditText phone,password,rePassword;
    @ViewInject(R.id.editName)
    private EditText userName;
    @ViewInject(R.id.tvCommonTitle)
    private TextView tvCom;
    @ViewInject(R.id.editText)
    private EditText phone;
    @ViewInject(R.id.editText1)
    private EditText password;
    @ViewInject(R.id.editText2)
    private EditText rePassword;
    @ViewInject(R.id.ivSearch)
    private ImageView ivSearch;

    private RegisterPresenter registerPresenter=new RegisterPresenter(this);

    @Event(value = {R.id.ivBack,R.id.submitID})
    private void getClick(View view){
       switch (view.getId()){
           case R.id.ivBack:
              finish();
               break;
           case R.id.submitID:
              // regist();
               registerPresenter.register();
               break;
           default:
               break;
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_regist);
        init();
    }
    private void init(){
        tvCom.setText("注册");
        ivSearch.setVisibility(View.INVISIBLE);
    }
    @Override
    public String getUserPhone() {
        return phone.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return password.getText().toString();
    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public void toMainManagerActivity(MUser user) {
            if(user.getResult().equals("right")){

                new Toasttool().MyToast(x.app(),"注册成功");
                Intent intent=new Intent(RegistActivity.this,MainPageActivity.class);
                startActivity(intent);
                finish();
            }else {
                new Toasttool().MyToast(x.app(),"手机号已注册");
            }
    }

    @Override
    public void showFailedError() {
             new Toasttool().MyToast(x.app(),"网络连接失败");
    }
}
