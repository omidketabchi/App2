package com.example.app2.SampleProject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.app2.R;

public class MyDialog extends DialogFragment {

    private OnClickDialogListener onClickDialogListener;

    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        txtFirst = (TextView) view.findViewById(R.id.txt_first);
        txtSecond = (TextView) view.findViewById(R.id.txt_second);
        txtThird = (TextView) view.findViewById(R.id.txt_third);

        txtFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDialogListener.onClickDialog("txt First");
            }
        });

        txtSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDialogListener.onClickDialog("txt Second");
            }
        });

        txtThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDialogListener.onClickDialog("txt Third");
            }
        });

        return view;
    }

    public void setOnClickDialogListener(OnClickDialogListener onClickDialogListener) {

        this.onClickDialogListener = onClickDialogListener;
    }

    public interface OnClickDialogListener {
        void onClickDialog(String message);
    }
}
