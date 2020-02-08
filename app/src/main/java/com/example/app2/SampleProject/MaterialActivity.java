package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NoteModel> dataList;
    MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        setupViews();

        getDataFromDB();

        recyclerView.setAdapter(new NoteAdapter(MaterialActivity.this, dataList));
    }

    private void setupViews() {

        myDataBase = new MyDataBase(MaterialActivity.this);
        dataList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_material_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(MaterialActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    private void getDataFromDB() {

        MyDataBase myDataBase = new MyDataBase(MaterialActivity.this);

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
