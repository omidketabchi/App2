package com.example.app2.Alibaba.FragmentAlibaba;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.app2.R;

public class FragmentDialogPassengers extends DialogFragment implements View.OnClickListener {

    View view;
    TextView txtNumberOfNormalPassengers;
    TextView txtNumberOfBrothers;
    TextView txtNumberOfSisters;
    ImageView imgAddNormalPassengers;
    ImageView imgRemoveNormalPassengers;

    ImageView imgAddBrothers;
    ImageView imgRemoveBrothers;

    ImageView imgAddSisters;
    ImageView imgRemoveSisters;

    Button btnConfirm;

    private OnSubmitClicked onSubmitClicked;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_pick_passengers, null);

        setupViews();

        builder.setView(view);

        return builder.create();
    }

    private void setupViews() {

        txtNumberOfNormalPassengers = (TextView) view.findViewById(R.id.txt_dialogPassengers_count);
        txtNumberOfBrothers = (TextView) view.findViewById(R.id.txt_dialogPassengers_countB);
        txtNumberOfSisters = (TextView) view.findViewById(R.id.txt_dialogPassengers_countS);

        imgAddNormalPassengers = (ImageView) view.findViewById(R.id.img_dialogPassengers_plus);
        imgRemoveNormalPassengers = (ImageView) view.findViewById(R.id.img_dialogPassengers_minus);
        imgAddBrothers = (ImageView) view.findViewById(R.id.img_dialogPassengers_plusB);
        imgRemoveBrothers = (ImageView) view.findViewById(R.id.img_dialogPassengers_minusB);
        imgAddSisters = (ImageView) view.findViewById(R.id.img_dialogPassengers_plusS);
        imgRemoveSisters = (ImageView) view.findViewById(R.id.img_dialogPassengers_minusS);

        btnConfirm = (Button) view.findViewById(R.id.btn_dialogPassengers_submit);

        imgAddNormalPassengers.setOnClickListener(this);
        imgRemoveNormalPassengers.setOnClickListener(this);
        imgAddBrothers.setOnClickListener(this);
        imgRemoveBrothers.setOnClickListener(this);
        imgAddSisters.setOnClickListener(this);
        imgRemoveSisters.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_dialogPassengers_plus:
                incrementCount(txtNumberOfNormalPassengers);
                break;
            case R.id.img_dialogPassengers_plusB:
                incrementCount(txtNumberOfBrothers);
                break;
            case R.id.img_dialogPassengers_plusS:
                incrementCount(txtNumberOfSisters);
                break;

            case R.id.img_dialogPassengers_minus:
                decrementCount(txtNumberOfNormalPassengers);
                break;
            case R.id.img_dialogPassengers_minusB:
                decrementCount(txtNumberOfBrothers);
                break;
            case R.id.img_dialogPassengers_minusS:
                decrementCount(txtNumberOfSisters);
                break;
            case R.id.btn_dialogPassengers_submit:
                submit();
                break;
        }
    }

    private void incrementCount(TextView txtNumberOfPassengers) {

        int number = Integer.parseInt(txtNumberOfPassengers.getText().toString()) + 1;
        txtNumberOfPassengers.setText(String.valueOf(number));
    }

    private void decrementCount(TextView txtNumberOfPassengers) {

        int number = Integer.parseInt(txtNumberOfPassengers.getText().toString()) - 1;

        number = (number < 0) ? 0 : number;

        txtNumberOfPassengers.setText(String.valueOf(number));
    }


    private void submit() {


        onSubmitClicked.onClicked(txtNumberOfNormalPassengers.getText().toString(),
                txtNumberOfBrothers.getText().toString(),
                txtNumberOfSisters.getText().toString());

        dismiss();


    }

    public void setOnSubmitClicked(OnSubmitClicked onSubmitClicked) {
        this.onSubmitClicked = onSubmitClicked;
    }

    public interface OnSubmitClicked {
        void onClicked(String numberOfNormalPassengers,
                       String numberOfBrothers,
                       String numberOfSisters);
    }
}
