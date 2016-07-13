package com.yfw.izlt.picture.view.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.picture.view.view.SlidingLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_picture)
public class PictureActivity extends BaseActivity implements View.OnClickListener{
    //侧滑
    @ViewInject(R.id.slidingLayout)
    SlidingLayout slidingLayout;
    @ViewInject(R.id.rightContent)
    LinearLayout rightContent;
    @ViewInject(R.id.leftContent)
    LinearLayout leftContent;

    @ViewInject(R.id.tvCommonTitle)
    private TextView tvCommonTitle;
    @Event(R.id.ivBack)
    private void backClick(View view){
        if(slidingLayout.isLeftLayoutVisible()){
            slidingLayout.scrollToRightLayout();
        }else {
            slidingLayout.scrollToLeftLayout();
        }
    }

    /**  定义推荐、分类的Tab图标 */
    @ViewInject(R.id.btnTRecommendID)
    private RadioButton btnTRecommendID;
    @ViewInject(R.id.btnTClassID)
    private RadioButton btnTClassID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_picture);
        tvCommonTitle.setText("图片");
        slidingLayout.setScrollEvent(leftContent);
        slidingLayout.setScrollEvent(rightContent);

    }

    @Override
    public void onClick(View view) {

    }
}
