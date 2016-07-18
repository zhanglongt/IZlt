package com.yfw.izlt.picture.view.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yfw.izlt.R;
import com.yfw.izlt.common.Toasttool;
import com.yfw.izlt.common.Utils;
import com.yfw.izlt.main.model.bean.Person;
import com.yfw.izlt.main.util.Cheeses;
import com.yfw.izlt.picture.view.adapter.SwipeListAdapter;

import org.xutils.x;

public class SlideDeleteActivity extends Activity {
    private SwipeListAdapter adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_delete);
        list= (ListView) findViewById(R.id.list);
        adapter=new SwipeListAdapter(SlideDeleteActivity.this);
        list.setAdapter(adapter);

//        setListAdapter(adapter);
//        setListAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
//        adapter = new SwipeListAdapter(SlideDeleteActivity.this);
//        setListAdapter(adapter);
//        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    adapter.closeAllLayout();
//
//                }
//            }
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//            }
//        });
    }

}
