package com.example.app2.Alibaba.FragmentAlibaba;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Adapter.HotelItemAdapter;
import com.example.app2.Alibaba.Model.AlibabaHotelModel;
import com.example.app2.R;

import java.util.ArrayList;

public class FragmentShowHotelList extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<AlibabaHotelModel> hotels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_show_hotel_list, container, false);

        setupViews();

        recyclerView.setAdapter(new HotelItemAdapter(getContext(), hotels));

        return view;
    }

    private void setupViews() {

        hotels = new ArrayList<>();

        hotels = getArguments().getParcelableArrayList("hotels");
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_hotelList_showList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
