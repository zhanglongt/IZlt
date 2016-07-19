package com.yfw.izlt.picture.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yfw.izlt.BaseActivity;
import com.yfw.izlt.R;
import com.yfw.izlt.common.ShoppingView;
import com.yfw.izlt.common.Toasttool;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by zlt on 2016/7/19.
 */
@ContentView(R.layout.activity_ishopping)
public class IShoppingActivity extends BaseActivity {
    @ViewInject(R.id.svID)
    private ShoppingView svID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //svID= (ShoppingView) findViewById(R.id.svID);
        Log.d("@=>", "minus.......num=> "+1);
        svID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("@=>", "minus.......num=> "+svID.getNum());
                Toasttool.MyToast(x.app(),"minus.......num=> "+svID.getNum());
            }
        });
    }
}
