package com.example.app2.Alibaba.FragmentAlibaba;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app2.Alibaba.AlibabaDetailActivity;
import com.example.app2.Alibaba.CitiesActivity;
import com.example.app2.R;

import java.util.Calendar;

//import com.example.app2.R;
//import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
//import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
//import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;

public class FragmentInsideFight extends Fragment implements DatePickerDialog.OnDateSetListener {

    View view;
    Button btnSearch;
    TextView txtSource;
    TextView txtDestination;
    TextView txtDate;

    private static final int REQ_SRC_CODE = 1000;
    private static final int REQ_DES_CODE = 1001;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_inside_filght, container, false);

        btnSearch = (Button) view.findViewById(R.id.btn_insideFlight_search);
        txtSource = (TextView) view.findViewById(R.id.txt_insideFlight_source);
        txtDestination = (TextView) view.findViewById(R.id.txt_insideFlight_destination);
        txtDate = (TextView) view.findViewById(R.id.txt_insideFlight_date);

        txtSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent, REQ_SRC_CODE);


                getActivity().overridePendingTransition(R.anim.inner_annimation,
                        R.anim.outter_annimation);
            }
        });

        txtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent, REQ_DES_CODE);

                getActivity().overridePendingTransition(R.anim.inner_annimation,
                        R.anim.outter_annimation);
            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        FragmentInsideFight.this, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AlibabaDetailActivity.class);
                intent.putExtra("type", "flight");
                intent.putExtra("source", txtSource.getText().toString());
                intent.putExtra("destination", txtDestination.getText().toString());
                intent.putExtra("date", txtDate.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_SRC_CODE && resultCode == Activity.RESULT_OK && data != null) {

            txtSource.setText(data.getExtras().getString("city"));
        } else if (requestCode == REQ_DES_CODE && resultCode == Activity.RESULT_OK && data != null) {

            txtDestination.setText(data.getExtras().getString("city"));
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        String month = "";
        switch (monthOfYear) {
            case 0:
                month = "فروردین";
                break;
            case 1:
                month = "اردیبهشت";
                break;
            case 2:
                month = "خرداد";
                break;
            case 3:
                month = "تیر";
                break;
            case 4:
                month = "مرداد";
                break;
            case 5:
                month = "شهریور";
                break;
            case 6:
                month = "مهر";
                break;
            case 7:
                month = "آبان";
                break;
            case 8:
                month = "آذر";
                break;
            case 9:
                month = "دی";
                break;
            case 10:
                month = "بهمن";
                break;
            case 11:
                month = "اسفند";
                break;
        }

        txtDate.setText(String.format("%02d", dayOfMonth) +
                " " + month + " " +
                String.format("%04d", year));
    }
}
