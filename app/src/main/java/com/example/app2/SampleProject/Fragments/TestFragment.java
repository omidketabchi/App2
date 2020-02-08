package com.example.app2.SampleProject.Fragments;

// baraye har fragment bayad yek class dashteh bashim.

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app2.R;

public class TestFragment extends Fragment {

    Button btnTest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_fragment, container, false);
        btnTest = (Button)view.findViewById(R.id.btn_testFragment);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hello from fragment", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
