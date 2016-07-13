package com.yfw.izlt.picture.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.main.view.activity.MainPageActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_picture)
public class PictureActivity extends BaseActivity {
    @ViewInject(R.id.tvCommonTitle)
    private TextView tvCommonTitle;
    @Event(R.id.ivBack)
    private void backClick(View view){
        startActivity(new Intent(x.app(), MainPageActivity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_picture);
        tvCommonTitle.setText("图片");
    }
}
