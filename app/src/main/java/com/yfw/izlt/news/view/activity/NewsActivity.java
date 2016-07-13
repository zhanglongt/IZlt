package com.yfw.izlt.news.view.activity;

import android.content.Intent;
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

@ContentView(R.layout.activity_news)
public class NewsActivity extends BaseActivity {
    @ViewInject(R.id.ivSearch)
    private ImageView ivSearch;
    @Event(R.id.ivBack)
    private void backClick(View view){
        startActivity(new Intent(x.app(), MainPageActivity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_news);
    }
}
