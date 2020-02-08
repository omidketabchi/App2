package com.example.app2.Alibaba.FragmentAlibaba;

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

import static android.app.Activity.RESULT_OK;

public class FragmentTrain extends Fragment implements DatePickerDialog.OnDateSetListener {

    View view;
    private static final int SOURCE_REQUEST_CODE = 677;
    private static final int DESTINATION_REQUEST_CODE = 678;

    TextView txtSource;
    TextView txtDestination;
    TextView txtDate;
    TextView txtNumberOfPassengers;
    Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_train, container, false);

        setupViews();

        return view;
    }

    private void setupViews() {

        txtSource = (TextView) view.findViewById(R.id.txt_trainFragment_source);
        txtDestination = (TextView) view.findViewById(R.id.txt_trainFragment_destination);
        txtDate = (TextView) view.findViewById(R.id.txt_trainFragment_date);
        txtNumberOfPassengers = (TextView) view.findViewById(R.id.txt_trainFragment_passengers_number);
        btnSearch = (Button) view.findViewById(R.id.btn_trainFragment_search);

        txtSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent, SOURCE_REQUEST_CODE);
            }
        });

        txtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent, DESTINATION_REQUEST_CODE);
            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        FragmentTrain.this, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        txtNumberOfPassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialogPassengers dialogPassengers = new FragmentDialogPassengers();
                dialogPassengers.show(getActivity().getSupportFragmentManager(), null);

                dialogPassengers.setOnSubmitClicked(new FragmentDialogPassengers.OnSubmitClicked() {
                    @Override
                    public void onClicked(String numberOfNormalPassengers, String numberOfBrothers, String numberOfSisters) {
                        txtNumberOfPassengers.setText(numberOfNormalPassengers + " نفر");
                    }
                });
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), AlibabaDetailActivity.class);

                intent.putExtra("type", "train");
                intent.putExtra("source", txtSource.getText().toString());
                intent.putExtra("destination", txtDestination.getText().toString());
                intent.putExtra("date", txtDate.getText().toString());

                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SOURCE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            txtSource.setText(data.getExtras().getString("city"));
        } else if (requestCode == DESTINATION_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
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
