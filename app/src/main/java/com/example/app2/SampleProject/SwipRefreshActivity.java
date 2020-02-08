package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.app2.SampleProject.Adapter.MyAdapter;
import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwipRefreshActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    List<String> names;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip_refresh);

        setupViews();

        for (int i = 0; i < 20; i++) {
            names.add("name" + i);
        }

        adapter = new MyAdapter(names);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                names.clear();
                for (int i = 0; i < 20; i++) {

                    Random random = new Random();
                    int r = random.nextInt(20);//[0,19]
                    names.add("name" + r);
                }

                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setupViews() {

        names = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip_main);
    }
}
