package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<NoteModel> noteModels;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setupViews();

        getDataFromDB();

        recyclerView.setAdapter(new NoteAdapter(ListActivity.this, noteModels));
    }

    private void setupViews() {

        noteModels = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rcv_list_items);

        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this, LinearLayoutManager.VERTICAL, false)); //--1

//        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this, LinearLayoutManager.HORIZONTAL, false)); //--2
//        recyclerView.setLayoutManager(new GridLayoutManager(ListActivity.this, 2, LinearLayoutManager.VERTICAL, false));//--3
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));// --4(same as #1, because default position is VERTICAL)
//        recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this)); //same as --1
    }

    private void getDataFromDB() {

        MyDataBase myDataBase = new MyDataBase(ListActivity.this);

        Cursor cursor = myDataBase.getInfos();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            NoteModel noteModel = new NoteModel();

            noteModel.setId(cursor.getInt(0));
            noteModel.setTitle(cursor.getString(1));
            noteModel.setDescription(cursor.getString(2));
            noteModel.setRemember(cursor.getInt(3));
            noteModel.setCurrentDate(cursor.getString(4));
            noteModel.setCurrentTime(cursor.getString(5));

            noteModels.add(noteModel);
        }
    }
}
