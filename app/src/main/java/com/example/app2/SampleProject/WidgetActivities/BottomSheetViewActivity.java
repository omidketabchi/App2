package com.example.app2.SampleProject.WidgetActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class BottomSheetViewActivity extends AppCompatActivity implements ItemAdapter.ItemListener{

    BottomSheetBehavior behavior;
    RecyclerView recyclerView;
    private ItemAdapter mAdapter;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl_bottomSheetView_coordinatorLayout);

        View bottomSheet = findViewById(R.id.ll_bottomSheetView_bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.rv_bottomSheetView_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");
        items.add("Item 5");
        items.add("Item 6");
        items.add("Item 7");
        items.add("Item 8");
        items.add("Item 9");
        items.add("Item 10");

        mAdapter = new ItemAdapter(this , items);
        recyclerView.setAdapter(mAdapter);

        Button button = (Button) findViewById(R.id.btn_bottomSheetView_style);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
    }

    @Override
    public void onItemClick(String item) {

        Snackbar.make(coordinatorLayout,item + " is selected", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();


        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}
