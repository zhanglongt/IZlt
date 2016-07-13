package com.yfw.izlt.picture.view.activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.picture.view.adapter.MPageAdapter;
import com.yfw.izlt.picture.view.fragment.TClassFragment;
import com.yfw.izlt.picture.view.fragment.TReFragment;
import com.yfw.izlt.picture.view.view.CustomViewPager;
import com.yfw.izlt.picture.view.view.SlidingLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_picture)
public class PictureActivity extends BaseActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
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

    /** 对Fragment进行管理  */
    private FragmentManager fragmentManager;
    @ViewInject(R.id.viewPage)
    private CustomViewPager customViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_picture);
        tvCommonTitle.setText("图片");
        fragmentManager=getSupportFragmentManager();
        slidingLayout.setScrollEvent(leftContent);
        slidingLayout.setScrollEvent(rightContent);
        customViewPager.setAdapter(new MPageAdapter(fragmentManager));
        customViewPager.setCurrentItem(0);
        customViewPager.setOnPageChangeListener(this);

        btnTRecommendID.setOnClickListener(this);
        btnTClassID.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.btnTRecommendID:
                 customViewPager.setCurrentItem(0);
                 break;
             case R.id.btnTClassID:
                 customViewPager.setCurrentItem(1);
                 break;
         }
    }

    //页面滑动事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position==0){
            btnTRecommendID.setChecked(true);
            btnTClassID.setChecked(false);
        }else if(position==1){
            btnTRecommendID.setChecked(false);
            btnTClassID.setChecked(true);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
