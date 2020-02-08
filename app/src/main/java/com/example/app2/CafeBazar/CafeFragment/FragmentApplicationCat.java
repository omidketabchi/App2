package com.example.app2.CafeBazar.CafeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeAdapter.CatAdapter;
import com.example.app2.CafeBazar.CafeModel.Cat;
import com.example.app2.CafeBazar.Retrofit.ApiClient;
import com.example.app2.CafeBazar.Retrofit.ApiService;
import com.example.app2.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentApplicationCat extends Fragment {

    RecyclerView recyclerView;
    List<Cat> cats;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_application_cat, container, false);

        setupViews();

        getAllCats();

        return view;
    }

    private void setupViews() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragmentCat_applicationCat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void getAllCats() {

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<List<Cat>> call = service.getCats();

        call.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                cats = response.body();

                recyclerView.setAdapter(new CatAdapter(getContext(), cats));
            }

            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {

            }
        });
    }
}
