package com.yfw.izlt.main.view.activity;

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
    }
    /**
     * 注册
     */
    private void regist(){
        String url= Constants.REGISTER_ACCESS;
        String phones = phone.getText().toString().trim();
        String passwords = password.getText().toString().trim();
        String rePasswords = rePassword.getText().toString().trim();
//        xUtils网络请求用法
//        RequestParams params = new RequestParams("http://192.168.0.13:8080/upload");、、、GET
//        // 加到url里的参数, http://xxxx/s?wd=xUtils
//        params.addQueryStringParameter("wd", "xUtils");
//        添加到请求body体的参数, 只有POST, PUT, PATCH, DELETE请求支持.
//        params.addBodyParameter("wd", "xUtils");
//        使用multipart表单上传文件
//        params.setMultipart(true);
//        params.addBodyParameter(
//                "file",
//                new File("/sdcard/test.jpg"),
//                null); // 如果文件没有扩展名, 最好设置contentType参数.
//        try {
//            params.addBodyParameter(
//                    "file2",
//                    new FileInputStream(new File("/sdcard/test2.jpg")),
//                    "image/jpeg",
//                    // 测试中文文件名
//                    "你+& \" 好.jpg"); // InputStream参数获取不到文件名, 最好设置, 除非服务端不关心这个参数.
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        }
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userName","zlt");
        params.addBodyParameter("userPass",passwords);
        params.addBodyParameter("mobile",phones);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("ii","result:"+result);
                MUser data=null;
                try {
                    data= new ObjectMapper().readValue(result,MUser.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(data.getResult().equals("right")){
                    new Toasttool().MyToast(RegistActivity.this,"注册成功");
                }else {
                    new Toasttool().MyToast(RegistActivity.this,"注册失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                new Toasttool().MyToast(RegistActivity.this,"网络连接失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
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
            }else {
                new Toasttool().MyToast(x.app(),"手机号已注册");
            }
    }

    @Override
    public void showFailedError() {
             new Toasttool().MyToast(x.app(),"网络连接失败");
    }
}
