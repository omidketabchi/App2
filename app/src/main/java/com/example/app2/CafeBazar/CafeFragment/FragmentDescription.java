package com.example.app2.CafeBazar.CafeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app2.R;

public class FragmentDescription extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cafe_description,
                container, false);

        TextView txtDescription = view.findViewById(R.id.txt_fragmentDescription_desc);

        Bundle bundle = getArguments();
        String description = bundle.getString("description");

        txtDescription.setText(description);

        return view;
    }
}
