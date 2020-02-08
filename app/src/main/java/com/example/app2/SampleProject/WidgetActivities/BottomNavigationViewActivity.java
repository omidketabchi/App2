package com.example.app2.SampleProject.WidgetActivities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.btm_bottomNavigationView_parent);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.btm_bottomNavigationView_item1:
                        Toast.makeText(BottomNavigationViewActivity.this, "item1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btm_bottomNavigationView_item2:
                        Toast.makeText(BottomNavigationViewActivity.this, "item2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btm_bottomNavigationView_item3:
                        Toast.makeText(BottomNavigationViewActivity.this, "item3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btm_bottomNavigationView_item4:
                        Toast.makeText(BottomNavigationViewActivity.this, "item4", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btm_bottomNavigationView_item5:
                        Toast.makeText(BottomNavigationViewActivity.this, "item5", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
