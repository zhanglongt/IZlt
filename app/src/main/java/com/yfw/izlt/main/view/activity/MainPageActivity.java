package com.yfw.izlt.main.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;
import android.widget.ListView;


import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.common.SaveDatas;
import com.yfw.izlt.common.Utils;
import com.yfw.izlt.main.view.view.DragLayout;
import com.yfw.izlt.main.view.view.MyLinearLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main_page)
public class MainPageActivity extends BaseActivity {
    @ViewInject(R.id.lv_left)
    private ListView mLeftList;
    @ViewInject(R.id.lv_main)
    private ListView mMainList;
    @ViewInject(R.id.mImage)
    private ImageView mHeaderImage;
    @ViewInject(R.id.mll)
    private MyLinearLayout mLinearLayout;
    // 查找Draglayout, 设置监听
    @ViewInject(R.id.dl)
    private DragLayout mDragLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_page);
        mHeaderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDragLayout.open();
            }
        });
        // 设置引用
        mLinearLayout.setDraglayout(mDragLayout);
        mDragLayout.setDragStatusListener(new DragLayout.OnDragStatusChangeListener() {
            @Override
            public void onClose() {
                Utils.showToast(x.app(), "onClose");
               // 让图标晃动
//				mHeaderImage.setTranslationX(translationX)
                ObjectAnimator mAnim = ObjectAnimator.ofFloat(mHeaderImage, "translationX", 15.0f);
                mAnim.setInterpolator(new CycleInterpolator(4));
                mAnim.setDuration(500);
                mAnim.start();
            }

            @Override
            public void onOpen() {
                Utils.showToast(x.app(), "onOpen");
            }

            @Override
            public void onDraging(float percent) {
                ViewHelper.setAlpha(mHeaderImage, 1 - percent);
            }
        });
    }
}
