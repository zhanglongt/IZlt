package com.yfw.izlt.news.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.yfw.izlt.R;
import com.yfw.izlt.news.view.fragment.NewsHomeFragment;
import com.yfw.izlt.news.view.fragment.NewsProfileFragment;
import com.yfw.izlt.news.view.view.ResideMenu;
import com.yfw.izlt.news.view.view.ResideMenuItem;

public class NewsActivity extends FragmentActivity implements View.OnClickListener{
    private NewsActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenu resideMenu;
    private ImageView mBtRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mContext = this;
        mBtRight= (ImageView) findViewById(R.id.ivSearch);
        setUpMenu();
        if( savedInstanceState == null )

            changeFragment(new NewsHomeFragment());
    }

    private void setUpMenu() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");
        itemProfile  = new ResideMenuItem(this, R.drawable.icon_profile,  "Profile");
        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);

        mBtRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
                mBtRight.setSelected(true);
            }
        });
//        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
//            }
//        });

    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener(){

        @Override
        public void openMenu() {
            mBtRight.setSelected(true);
        }

        @Override
        public void closeMenu() {
            mBtRight.setSelected(false);
        }
    };

    @Override
    public void onClick(View view) {
       if(view == itemHome){
           changeFragment(new NewsHomeFragment());
       }else if (view == itemProfile){
           changeFragment(new NewsProfileFragment());
       }
        resideMenu.closeMenu();
    }
}
