package com.example.app2.Alibaba.FragmentAlibaba;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app2.R;

public class FragmentBusInfo extends Fragment {

    View view;
    TextView txtName;
    TextView txtDiscount;
    EditText edtDiscount;
    TextView txtEmail;
    TextView txtPrice;
    Button btnSubmit;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_bus_info, container, false);

        setupViews();

        return view;
    }

    private void setupViews() {

        sharedPreferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);

        txtName = (TextView) view.findViewById(R.id.txt_FragmentBusInfo_name);
        txtEmail = (TextView) view.findViewById(R.id.txt_FragmentBusInfo_email);
        txtPrice = (TextView) view.findViewById(R.id.txt_FragmentBusInfo_price);
        edtDiscount = (EditText) view.findViewById(R.id.edt_FragmentBusInfo_discount);
        btnSubmit = (Button) view.findViewById(R.id.btn_FragmentBusInfo_submit);

        String email = sharedPreferences.getString("email", "");

        if (!email.equals("")) {
            Toast.makeText(getContext(), "لطفا وارد حساب کاربری خود شوید", Toast.LENGTH_SHORT).show();
        } else {
            txtEmail.setText(email);
        }

        txtName.setText(PassengersFragment.nameG);
        txtPrice.setText(PassengersFragment.price);
    }
}
