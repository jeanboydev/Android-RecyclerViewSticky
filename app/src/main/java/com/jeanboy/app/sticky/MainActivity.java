package com.jeanboy.app.sticky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv_container;
    private List<String> dataList = new ArrayList<>();
    private ListAdapter listAdapter;

    private LinearLayout ll_head;
    private View v_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ll_head = findViewById(R.id.ll_head);
        v_menu = findViewById(R.id.v_menu);
        rv_container = findViewById(R.id.rv_container);
        rv_container.setLayoutManager(new LinearLayoutManager(this));
        rv_container.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        rv_container.addItemDecoration(new StickyItemDecoration());
        listAdapter = new ListAdapter(dataList);
        rv_container.setAdapter(listAdapter);
        rv_container.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int offsetY = 0;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                Log.e(MainActivity.class.getSimpleName(), "==newState==" + newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e(MainActivity.class.getSimpleName(), "=====dy===" + dy);

                int headHeight = ll_head.getHeight();
                int menuHeight = v_menu.getHeight();
                int maxOffset = headHeight - menuHeight;
                int topOffset = ll_head.getTop() - dy;
                if (dy > 0) { // 向上滚动
                    if (Math.abs(topOffset) >= maxOffset) {
                        topOffset = -maxOffset;
                    }
                } else { //向下滚动
                    if (topOffset > 0) {
                        topOffset = 0;
                    }
                }

                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ll_head.getLayoutParams();
                layoutParams.topMargin = topOffset;
                ll_head.setLayoutParams(layoutParams);
                Log.e(MainActivity.class.getSimpleName(), "==ll_head===topOffset===" + topOffset + "===maxOffset==" + maxOffset);
            }
        });


        for (int i = 0; i < 50; i++) {
            dataList.add("item=========" + i);
        }
        listAdapter.notifyDataSetChanged();
    }
}
