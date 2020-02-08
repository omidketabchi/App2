package com.example.app2.Alibaba.FragmentAlibaba;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Adapter.ChairItemAdapter;
import com.example.app2.Alibaba.Model.AlibabaChairModel;
import com.example.app2.R;

import java.util.ArrayList;

public class FragmentChair extends Fragment {

    OnSubmitClicked onSubmitClicked;
    View view;
    TextView txtPrice;
    Button btnSubmit;
    RecyclerView recyclerView;
    ChairItemAdapter chairItemAdapter;
    ArrayList<AlibabaChairModel> chairModels;
    String number = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_chair, container, false);

        setupViews();

        return view;
    }

    private void setupViews() {

        chairModels = getArguments().getParcelableArrayList("chairs");
        String price = getArguments().getString("price");

        txtPrice = (TextView) view.findViewById(R.id.txt_chair_totalPrice);
        txtPrice.setText(price);

        btnSubmit = (Button) view.findViewById(R.id.btn_chairSelection_submit);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_chairSelection_chairList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        chairItemAdapter = new ChairItemAdapter(getContext(), chairModels);
        recyclerView.setAdapter(chairItemAdapter);

        chairItemAdapter.setOnChairClicked(new ChairItemAdapter.OnChairClicked() {
            @Override
            public void chairClicked(String chairNumber) {
                number = chairNumber;
                btnSubmit.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_style));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitClicked.onClicked(number, price);
            }
        });
    }

    public void setOnSubmitClicked(OnSubmitClicked onSubmitClicked) {
        this.onSubmitClicked = onSubmitClicked;
    }

    public interface OnSubmitClicked {

        void onClicked(String number, String price);
    }
}
