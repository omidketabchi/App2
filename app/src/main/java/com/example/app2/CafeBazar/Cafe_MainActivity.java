package com.example.app2.CafeBazar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app2.CafeBazar.CafeFragment.FragmentApps;
import com.example.app2.CafeBazar.CafeFragment.FragmentBests;
import com.example.app2.CafeBazar.CafeFragment.FragmentCats;
import com.example.app2.CafeBazar.CafeFragment.FragmentHome;
import com.example.app2.CafeBazar.CafeFragment.FragmentSearch;
import com.example.app2.R;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import java.io.File;

public class Cafe_MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1001;
    Typeface typeface;
    FragmentTransaction transaction;
    BottomNavigation bottomNavigation;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cb_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

            } else {
                createFilePath();
            }
        } else {
            createFilePath();
        }

        setupViews();
    }

    private void setupViews() {
        bottomNavigation =
                (BottomNavigation) findViewById(R.id.bnv_main_bottom_navigation);

        typeface = Typeface.createFromAsset(getAssets(), "bhoma.ttf");
        bottomNavigation.setTypeface(typeface);

        bottomNavigation.setDefaultItem(bottomNavigation.getChildCount() - 1);

        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int itemId) {

                fragmentManager = getSupportFragmentManager();

                switch (itemId) {
                    case R.id.tab_home:
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rel_main_parentAllView, new FragmentHome());
                        break;
                    case R.id.tab_bests:
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rel_main_parentAllView, new FragmentBests());
                        break;
                    case R.id.tab_cats:
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rel_main_parentAllView, new FragmentCats());
                        break;
                    case R.id.tab_search:
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rel_main_parentAllView, new FragmentSearch());
                        break;
                    case R.id.tab_apps:
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.rel_main_parentAllView, new FragmentApps());
                        break;
                }
                transaction.commit();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (bottomNavigation.getSelectedItem() == bottomNavigation.getChildCount() - 1) {
            super.onBackPressed();
        } else {
            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.rel_main_parentAllView, new FragmentHome());
            transaction.commit();

            bottomNavigation.setSelectedItem(bottomNavigation.getChildCount() - 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE && permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(Cafe_MainActivity.this, "write accepted", Toast.LENGTH_SHORT).show();
            createFilePath();
        } else {
            Toast.makeText(Cafe_MainActivity.this, "write denied", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == REQUEST_CODE && permissions.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(Cafe_MainActivity.this, "location accepted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Cafe_MainActivity.this, "location denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void createFilePath() {

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/cm1");

        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
