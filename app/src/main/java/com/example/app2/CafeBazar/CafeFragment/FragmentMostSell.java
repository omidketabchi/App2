package com.example.app2.CafeBazar.CafeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeAdapter.MostSellAdapter;
import com.example.app2.CafeBazar.CafeModel.App;
import com.example.app2.CafeBazar.Retrofit.ApiClient;
import com.example.app2.CafeBazar.Retrofit.ApiService;
import com.example.app2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMostSell extends Fragment {

    List<App> apps;
    MostSellAdapter mostSellAdapter;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ImageView imgSlide;
    View view;

    private boolean loading = true;
    int pastVisiblesItems = 0;
    int visibleItemCount = 0;
    int totalItemCount = 0;

    String imgUrl = "https://cfotech.com.au/uploads/story/2019/12/17/Capture.png";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.cafe_fragment_most_sale, container, false);

        setupViews();

        Picasso.with(getContext()).load(imgUrl).into(imgSlide);

        getMostSellApp();

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (v.getChildAt(v.getChildCount() - 1) != null) {
                    if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                            scrollY > oldScrollY) {
//                        Toast.makeText(getContext(), "end list most sell", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.VISIBLE);
                        getMostSellApp();
                    }
                }
            }
        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0) //check for scroll down
//                {
//                    visibleItemCount = layoutManager.getChildCount();
//                    totalItemCount = layoutManager.getItemCount();
//                    pastVisiblesItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//
//                    if (loading) {
//                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
//                            loading = false;
//                            Toast.makeText(getContext(), "end shod", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            }
//        });

//        InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
//        infiniteScrollProvider.attach(recyclerView, new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                progressBar.setVisibility(View.VISIBLE);
//            }
//        });

        return view;
    }

    private void setupViews() {

        nestedScrollView = (NestedScrollView) view.findViewById(R.id.nst_fragmentMostSell_nestedScrollView);
        imgSlide = (ImageView) view.findViewById(R.id.img_fragmentMostSell_slide);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_mostSell);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragmentMostSell_mostSellRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mostSellAdapter = new MostSellAdapter(getContext());
        recyclerView.setAdapter(mostSellAdapter);
    }

    private void getMostSellApp() {

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<App>> call = apiService.getNewApps();

        call.enqueue(new Callback<List<App>>() {
            @Override
            public void onResponse(Call<List<App>> call, Response<List<App>> response) {

                apps = response.body();
                mostSellAdapter.addData(apps);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<App>> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
