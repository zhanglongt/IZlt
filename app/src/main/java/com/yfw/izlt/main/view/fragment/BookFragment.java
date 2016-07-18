package com.yfw.izlt.main.view.fragment;


import android.os.Bundle;
import android.os.Handler;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.yfw.izlt.R;
import com.yfw.izlt.main.model.bean.Person;
import com.yfw.izlt.main.util.Cheeses;

import com.yfw.izlt.main.view.adapter.HaoHanAdapter;
import com.yfw.izlt.main.view.view.QuickIndexBar;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zlt on 2016/7/15.
 */
public class BookFragment extends Fragment {//索引
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        bar= (QuickIndexBar) view.findViewById(R.id.bar);
        tv_center= (TextView) view.findViewById(R.id.tv_center);
        mMainList= (ListView) view.findViewById(R.id.lv_main);
        bar.setListener(new QuickIndexBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                showLetter(letter);
                // 根据字母定位ListView, 找到集合中第一个以letter为拼音首字母的对象,得到索引
                for (int i = 0; i < persons.size(); i++) {
                    Person person = persons.get(i);
                    String l = person.getPinyin().charAt(0) + "";
                    if(TextUtils.equals(letter, l)){
                        // 匹配成功
                        mMainList.setSelection(i);
                        break;
                    }
                }
            }
        });
        persons = new ArrayList<Person>();
        // 填充数据 , 排序
        fillAndSortData(persons);
        mMainList.setAdapter(new HaoHanAdapter(getActivity() , persons));
        mMainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person data= (Person) mMainList.getItemAtPosition(i);
                Log.i("ss",data.getName()+":::i"+i);
            }
        });
    }

    private ArrayList<Person> persons;
   // @ViewInject(R.id.lv_main)
    private ListView mMainList;
    //@ViewInject(R.id.tv_center)
    private TextView tv_center;
    //@ViewInject(R.id.bar)
    private QuickIndexBar bar;

    private Handler mHandler = new Handler();

    /**
     * 显示字母
     *
     * @param letter
     */
    protected void showLetter(String letter) {
        tv_center.setVisibility(View.VISIBLE);
        tv_center.setText(letter);

        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_center.setVisibility(View.GONE);
            }
        }, 2000);

    }

    private void fillAndSortData(ArrayList<Person> persons) {
        // 填充数据
        for (int i = 0; i < Cheeses.NAMES.length; i++) {
            String name = Cheeses.NAMES[i];
            persons.add(new Person(name));
        }
        // 进行排序
        Collections.sort(persons);
    }

}
