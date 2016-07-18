package com.yfw.izlt.picture.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.yfw.izlt.R;
import com.yfw.izlt.main.util.Cheeses;
import com.yfw.izlt.picture.view.view.MyListView;

/**
 * Created by zlt on 2016/7/13.
 */
public class TReFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        final MyListView mListView= (MyListView) view.findViewById(R.id.lv);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // 加Header
        final View mHeaderView = View.inflate(getActivity(), R.layout.view_header, null);
        final ImageView mImage = (ImageView) mHeaderView.findViewById(R.id.iv);
        mListView.addHeaderView(mHeaderView);

        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // 当布局填充结束之后, 此方法会被调用

                mListView.setParallaxImage(mImage);

                mHeaderView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
// 填充数据
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Cheeses.NAMES));
    }
}
