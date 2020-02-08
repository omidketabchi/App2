package com.example.app2.Alibaba.FragmentAlibaba;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app2.R;

public class FragmentTools extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alibaba_tools, container, false);
        TextView textView = (TextView) view.findViewById(R.id.txt_fragmentAlibabaTools_text);
        textView.setText("Tools");
        return view;
    }
}
