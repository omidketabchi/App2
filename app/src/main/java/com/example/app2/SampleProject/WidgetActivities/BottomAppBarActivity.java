package com.example.app2.SampleProject.WidgetActivities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app2.R;
import com.google.android.material.bottomappbar.BottomAppBar;

public class BottomAppBarActivity extends AppCompatActivity {

    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom_app_bar);

        bottomAppBar = (BottomAppBar) findViewById(R.id.btm_bottomAppBar_bottomAppBar);
        setSupportActionBar(bottomAppBar);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.btm_bottomNavigation_item1:
                        Toast.makeText(BottomAppBarActivity.this, "item1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btm_bottomNavigation_item2:
                        Toast.makeText(BottomAppBarActivity.this, "item2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btm_bottomNavigation_item3:
                        Toast.makeText(BottomAppBarActivity.this, "item3", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomAppBarActivity.this, "share item click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.btm_bottomNavigation_item1:
//                Toast.makeText(BottomAppBarActivity.this, "item1", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btm_bottomNavigation_item2:
//                Toast.makeText(BottomAppBarActivity.this, "item2", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btm_bottomNavigation_item3:
//                Toast.makeText(BottomAppBarActivity.this, "item3", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return true;
//    }
}
