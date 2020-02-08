package com.example.app2.Alibaba.FragmentAlibaba;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Adapter.PassengerAdapter;
import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;

public class PassengersFragment extends Fragment {

    View view;

    ImageView imgAddPAssenger;
    EditText edtName;
    EditText edtCode;
    Button btnSelection;
    RecyclerView recyclerView;

    List<String> names;
    PassengerAdapter passengerAdapter;
    OnPassengerNameReceive onPassengerNameReceive;

    public static String number = "";
    public static String price = "";
    public static String nameG = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.passenger_fragment, container, false);

        setupViews();

        price = getArguments().getString("price");
        number = getArguments().getString("number");

        return view;
    }

    private void setupViews() {

        names = new ArrayList<>();


        imgAddPAssenger = (ImageView) view.findViewById(R.id.img_passengerFragment_addPassenger);
        edtName = (EditText) view.findViewById(R.id.edt_passengerFragment_name);
        edtCode = (EditText) view.findViewById(R.id.edt_passengerFragment_code);
        btnSelection = (Button) view.findViewById(R.id.btn_passengerFragment_selectPassenger);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_passengerFragment_passengerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        btnSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                names.add(edtName.getText().toString());
                passengerAdapter = new PassengerAdapter(getContext(), names);
                recyclerView.setAdapter(passengerAdapter);
                passengerAdapter.notifyDataSetChanged();

                passengerAdapter.setOnAdapterNameClick(new PassengerAdapter.OnAdapterNameClick() {
                    @Override
                    public void onNameClick(String name) {
                        onPassengerNameReceive.onNameReceive(name);
                        nameG = name;
                    }
                });
            }
        });
    }

    public interface OnPassengerNameReceive {
        void onNameReceive(String name);
    }

    public void setOnPassengerNameReceive(OnPassengerNameReceive onPassengerNameReceive) {
        this.onPassengerNameReceive = onPassengerNameReceive;
    }
}
