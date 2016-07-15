package com.yfw.izlt.main.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;


import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.common.IApplication;
import com.yfw.izlt.common.SaveDatas;
import com.yfw.izlt.common.Toasttool;
import com.yfw.izlt.common.Utils;
import com.yfw.izlt.main.view.Interface.ISwitchMFragment;
import com.yfw.izlt.main.view.fragment.BookFragment;
import com.yfw.izlt.main.view.fragment.ClassFragment;
import com.yfw.izlt.main.view.fragment.FindFragment;
import com.yfw.izlt.main.view.fragment.HomeFragment;
import com.yfw.izlt.main.view.view.DragLayout;
import com.yfw.izlt.main.view.view.MyLinearLayout;
import com.yfw.izlt.news.view.activity.NewsActivity;
import com.yfw.izlt.picture.view.activity.PictureActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main_page)
public class MainPageActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener,ISwitchMFragment{
    //左侧资源
    private String leftArr[];
    @ViewInject(R.id.lv_left)
    private ListView mLeftList;
    @ViewInject(R.id.mImage)
    private ImageView mHeaderImage;
    @ViewInject(R.id.mainTitle)
    private TextView mainTitle;
    @ViewInject(R.id.mll)
    private MyLinearLayout mLinearLayout;
    // 查找Draglayout, 设置监听
    @ViewInject(R.id.dl)
    private DragLayout mDragLayout;

    /**  定义首页、分类、发现 的fragment页面 */
    private HomeFragment homeFragment;
    private ClassFragment classFragment;
    private FindFragment findFragment;
    private BookFragment bookFragment;

    /**  定义首页、分类、发现 通讯录的Tab图标 */
    @ViewInject(R.id.btnHomeID)
    private RadioButton btnHomeID;
    @ViewInject(R.id.btnClassID)
    private RadioButton btnClassID;
    @ViewInject(R.id.btnFindID)
    private RadioButton btnFindID;
    @ViewInject(R.id.btnBookID)
    private RadioButton btnBookID;

    /** 对Fragment进行管理  */
    private FragmentManager fragmentManager;
    private IApplication iApplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_page);
        iApplication= (IApplication) getApplicationContext();
        fragmentManager=getSupportFragmentManager();
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
                // mHeaderImage.setTranslationX(translationX)
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
        //页面点击
        btnHomeID.setOnClickListener(this);
        btnClassID.setOnClickListener(this);
        btnFindID.setOnClickListener(this);
        btnBookID.setOnClickListener(this);

        //左侧设置
        leftArr=getResources().getStringArray(R.array.menus);
        mLeftList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,leftArr){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView mText = ((TextView)view);
                mText.setTextColor(Color.WHITE);
                return view;
            }
        });
        mLeftList.setOnItemClickListener(this);
        //首页
        mHomeFragment();
    }

    /**  隐藏所有的fragment*/
    private void hideFragment(FragmentTransaction transaction){
        if(homeFragment !=null){
            transaction.hide(homeFragment);
        }
        if(classFragment !=null){
            transaction.hide(classFragment);
        }
        if(findFragment !=null){
            transaction.hide(findFragment);
        }
        if(bookFragment !=null){
            transaction.hide(bookFragment);
        }
    }
    /** 设置开启的tab首页页面 */
    @Override
    public void mHomeFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        if(homeFragment==null){
            homeFragment=new HomeFragment();
            transaction.add(R.id.include,homeFragment);
        }else {
            transaction.show(homeFragment);
        }
        transaction.commitAllowingStateLoss();


    }
    /** 设置开启的tab分类页面 */
    @Override
    public void mClassFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        if(classFragment==null){
            classFragment=new ClassFragment();
            transaction.add(R.id.include,classFragment);
        }else {
            transaction.show(classFragment);
        }
        transaction.commitAllowingStateLoss();

    }
    /** 设置开启的tab发现页面 */
    @Override
    public void mFindFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        if(findFragment==null){
            findFragment=new FindFragment();
            transaction.add(R.id.include,findFragment);
        }else {
            transaction.show(findFragment);
        }
        transaction.commitAllowingStateLoss();

    }
    /** 设置开启的tab通讯录页面 */
    @Override
    public void mBookFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        if(bookFragment==null){
            bookFragment=new BookFragment();
            transaction.add(R.id.include,bookFragment);
        }else {
            transaction.show(bookFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHomeID:
                mHomeFragment();
                mainTitle.setText("首页");
                break;
            case R.id.btnClassID:
                mClassFragment();
                mainTitle.setText("分类");
                break;
            case R.id.btnFindID:
                mFindFragment();
                mainTitle.setText("发现");
                break;
            case R.id.btnBookID:
                mBookFragment();
                mainTitle.setText("通讯录");
                break;
            default:
                mHomeFragment();
                mainTitle.setText("首页");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String data=  mLeftList.getItemAtPosition(i).toString();
        Log.i("ii","data:"+data+":::"+i);
        switch (i){
            case 0://新闻
                startActivity(new Intent(x.app(), NewsActivity.class));
                break;
            case 1://图片
                startActivity(new Intent(x.app(), PictureActivity.class));
                break;
            case 2:
                break;
            case 3://注销
                //SaveDatas.getInstance(x.app()).delete("keyId");
                iApplication.setLoginKey("");
                startActivity(new Intent(MainPageActivity.this,LoginActivity.class));
                new Toasttool().MyToast(x.app(),"注销成功");
                finish();
                break;
            default:
                break;
        }

    }
}
