package com.example.app2.CafeBazar.CafeFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app2.R;

public class FragmentAccount extends Fragment {

    TextView txtUsername;
    TextView txtExit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cafe_fragment_account, container,
                false);

        Bundle bundle = new Bundle();

        bundle = getArguments();

        bundle.getString("userName");


        txtUsername = view.findViewById(R.id.txt_fragmentAccount_phone);
        txtExit = view.findViewById(R.id.txt_fragmentAccount_exit);

        txtUsername.setText(bundle.getString("userName"));

        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences =
                        getContext().getSharedPreferences("home", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", "");
                editor.apply();

                FragmentManager manager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.rel_main_parentAllView, new FragmentHome());
                transaction.commit();
            }
        });

        return view;
    }

}
