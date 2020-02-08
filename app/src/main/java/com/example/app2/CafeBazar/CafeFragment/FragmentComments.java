package com.example.app2.CafeBazar.CafeFragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.CafeBazar.CafeAdapter.CommentsDetailAdapter;
import com.example.app2.CafeBazar.CafeModel.CommentApp;
import com.example.app2.CafeBazar.Retrofit.ApiClient;
import com.example.app2.CafeBazar.Retrofit.ApiService;
import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentComments extends Fragment {

    List<CommentApp> comments;
    RecyclerView commentsRecycler;
    TextView txtToolbarname;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.cafe_fragment_comments,
                container, false);


//        ArrayList<Parcelable> bundle = getArguments().getParcelableArrayList("comments");
//
//        CommentApp commentApp  = (CommentApp)bundle.get(0);
//
//        Toast.makeText(getContext(), commentApp.getTitle(), Toast.LENGTH_SHORT).show();

        Bundle bundle = getArguments();
        String appId = bundle.getString("id");
        getComments(appId);

        setupViews();

        return view;
    }

    private void getComments(String appId) {

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<List<CommentApp>> call = service.getAppComments(appId);

        call.enqueue(new Callback<List<CommentApp>>() {
            @Override
            public void onResponse(Call<List<CommentApp>> call, Response<List<CommentApp>> response) {
                comments = response.body();

                commentsRecycler.setAdapter(new CommentsDetailAdapter(getContext(), comments));
            }

            @Override
            public void onFailure(Call<List<CommentApp>> call, Throwable t) {

            }
        });
    }

    private void setupViews() {
        commentsRecycler = (RecyclerView) view.
                findViewById(R.id.rv_fragmentComments_comments);

        commentsRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        txtToolbarname = (TextView) view.findViewById(R.id.txt_usualToolbar_name);
        txtToolbarname.setText("نظرها");
    }
}
