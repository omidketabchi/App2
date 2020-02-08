package com.example.app2.SampleProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app2.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class KeshoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NoteModel> dataList;
    MyDataBase myDataBase;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView hamberMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesho);

        setupViews();

        getDataFromDB();

        recyclerView.setAdapter(new NoteAdapter(KeshoActivity.this, dataList));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_setting:
                        Toast.makeText(KeshoActivity.this, "action_setting", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_chart:
                        Toast.makeText(KeshoActivity.this, "action_chart", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_description:
                        Toast.makeText(KeshoActivity.this, "action_description", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });

        hamberMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }

    private void setupViews() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_material_drawer);
        navigationView = (NavigationView) findViewById(R.id.navView_material_menu);
        hamberMenu = (ImageView) findViewById(R.id.img_material_hamberMenu);
        recyclerView = (RecyclerView) findViewById(R.id.rv_kesho_material_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(KeshoActivity.this, LinearLayoutManager.VERTICAL, false));

        myDataBase = new MyDataBase(KeshoActivity.this);
        dataList = new ArrayList<>();
    }

    private void getDataFromDB() {

        MyDataBase myDataBase = new MyDataBase(KeshoActivity.this);

        Cursor cursor = myDataBase.getInfos();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            NoteModel noteModel = new NoteModel();

            noteModel.setId(cursor.getInt(0));
            noteModel.setTitle(cursor.getString(1));
            noteModel.setDescription(cursor.getString(2));
            noteModel.setRemember(cursor.getInt(3));
            noteModel.setCurrentDate(cursor.getString(4));
            noteModel.setCurrentTime(cursor.getString(5));

            dataList.add(noteModel);
        }
    }
}
