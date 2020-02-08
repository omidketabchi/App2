package com.example.app2.CafeBazar.CafeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeAdapter.SearchAdapter;
import com.example.app2.CafeBazar.CafeModel.App;
import com.example.app2.CafeBazar.Retrofit.ApiClient;
import com.example.app2.CafeBazar.Retrofit.ApiService;
import com.example.app2.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment {

    View view;
    SearchView searchView; //androidx version
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    List<App> apps;
    SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cafe_search,
                container, false);


        setupViews();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getContext(), "submit:" + query, Toast.LENGTH_SHORT).show();
                linearLayout.setVisibility(View.VISIBLE);
                doSearch(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                Toast.makeText(getContext(), "newText:" + newText, Toast.LENGTH_SHORT).show();
                linearLayout.setVisibility(View.VISIBLE);
                doSearch(newText);

                return true;
            }
        });

        return view;
    }

    private void setupViews() {

        linearLayout = (LinearLayout) view.findViewById(R.id.progress_fragmentSearch);
        searchView = (SearchView) view.findViewById(R.id.sv_fragmentSearch_searchView);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragmentSearch_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void doSearch(String search) {

        if (!search.equals("")) {
            ApiService service = ApiClient.getClient().create(ApiService.class);
            Call<List<App>> call = service.search(search);

            call.enqueue(new Callback<List<App>>() {
                @Override
                public void onResponse(Call<List<App>> call, Response<List<App>> response) {
                    apps = response.body();

                    linearLayout.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    searchAdapter = new SearchAdapter(getContext(), apps);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<App>> call, Throwable t) {

                }
            });
        } else {
            linearLayout.setVisibility(View.GONE);
            apps.clear();
            searchAdapter.notifyDataSetChanged();
        }
    }
}
