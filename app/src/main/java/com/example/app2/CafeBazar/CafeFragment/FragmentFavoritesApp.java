package com.example.app2.CafeBazar.CafeFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeAdapter.MostSellAdapter;
import com.example.app2.CafeBazar.CafeModel.App;
import com.example.app2.CafeBazar.Database.SqliteOpenHelper;
import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavoritesApp extends Fragment {

//    App app;
    View view;
    RecyclerView recyclerView;

    SqliteOpenHelper sqliteOpenHelper;
    List<App> apps;
    MostSellAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorites, container, false);

        setupViews();

        Cursor cursor = sqliteOpenHelper.getInfo();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            App app = new App();

            app.setName(cursor.getString(2));
            app.setIcon(cursor.getString(3));
            app.setKind(cursor.getString(4));

            apps.add(app);
        }

        adapter.addData(apps);

        return view;
    }

    private void setupViews() {

        adapter = new MostSellAdapter(getContext());
        apps = new ArrayList<>();
        sqliteOpenHelper = new SqliteOpenHelper(getContext());

        recyclerView = view.findViewById(R.id.rv_fragmentFavorites_appList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
