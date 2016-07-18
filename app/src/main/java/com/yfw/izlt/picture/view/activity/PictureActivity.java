package com.yfw.izlt.picture.view.activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.common.SaveDatas;
import com.yfw.izlt.common.Toasttool;
import com.yfw.izlt.main.model.bean.MUser;
import com.yfw.izlt.picture.util.ImageUtils;
import com.yfw.izlt.picture.view.adapter.MPageAdapter;
import com.yfw.izlt.picture.view.view.AvatarImageView;
import com.yfw.izlt.picture.view.view.CustomViewPager;
import com.yfw.izlt.picture.view.view.SlidingLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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
    private  Uri imageUri0;

    /** 头像*/
    @ViewInject(R.id.item_avatar)
    private AvatarImageView avatar;
//    @ViewInject(R.id.llChange)
//    private LinearLayout avatarChange;
    @ViewInject(R.id.menulist)
    private ListView mList;
    @Event(R.id.llChange)
    private void changeClick(View view){
        ImageUtils.showImagePickDialog(PictureActivity.this);
    }
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

        //ImageUtils.getCurrentUri().getPath();
        //添加头像
        //avatar.setTextAndColor("美", AvatarImageView.COLORS[0]);
       // avatar.setImageResource(R.mipmap.ic_launcher);
        String path=SaveDatas.getInstance(x.app()).getUserInfo("uri");
        Log.i("ii","pp:"+path);
        Glide
                .with(PictureActivity.this)
                .load(path)
                .placeholder(R.mipmap.tu)
                .centerCrop()
                .crossFade()
                .into(avatar);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("dd","i:"+i);
                Toasttool.MyToast(x.app(),"i"+i);
                if(i==0){//側拉删除 侧拉
                    startActivity(new Intent(x.app(),SlideDeleteActivity.class));
                }
            }
        });

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


    //回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case ImageUtils.REQUEST_CODE_FROM_ALBUM: {

                if (resultCode == RESULT_CANCELED) {   //取消操作
                    return;
                }

                Uri imageUri = data.getData();
                ImageUtils.copyImageUri(this,imageUri);
                ImageUtils.cropImageUri(this, ImageUtils.getCurrentUri(), 200, 200);
                break;
            }
            case ImageUtils.REQUEST_CODE_FROM_CAMERA: {

                if (resultCode == RESULT_CANCELED) {     //取消操作
                    ImageUtils.deleteImageUri(this, ImageUtils.getCurrentUri());   //删除Uri
                }

                ImageUtils.cropImageUri(this, ImageUtils.getCurrentUri(), 200, 200);
                break;
            }
            case ImageUtils.REQUEST_CODE_CROP: {

                if (resultCode == RESULT_CANCELED) {     //取消操作
                    return;
                }

                imageUri0 = ImageUtils.getCurrentUri();
                if (imageUri0 != null) {
                    //avatar.setImageURI(imageUri);
                    SaveDatas.getInstance(x.app()).setUserInfo("uri",imageUri0.toString());
                    Log.i("ii","path:"+imageUri0);
           Glide
                .with(PictureActivity.this)
                .load(imageUri0)
                .centerCrop()
                .crossFade()
                .into(avatar);
                }
                break;
            }
            default:
                break;
        }
    }
}
