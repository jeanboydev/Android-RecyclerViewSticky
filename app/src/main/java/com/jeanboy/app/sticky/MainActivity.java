package com.jeanboy.app.sticky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv_container;
    private List<String> dataList = new ArrayList<>();
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv_container = findViewById(R.id.rv_container);
        rv_container.setLayoutManager(new LinearLayoutManager(this));
        rv_container.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv_container.addItemDecoration(new StickyItemDecoration());
        listAdapter = new ListAdapter(dataList);
        rv_container.setAdapter(listAdapter);


        for (int i = 0; i < 50; i++) {
            dataList.add("item=========" + i);
        }
        listAdapter.notifyDataSetChanged();
    }
}
