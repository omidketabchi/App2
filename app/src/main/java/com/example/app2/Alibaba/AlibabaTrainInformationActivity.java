package com.example.app2.Alibaba;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app2.Alibaba.Model.AlibabaTrainTicketModel;
import com.example.app2.R;

public class AlibabaTrainInformationActivity extends AppCompatActivity {

    TextView txtTitle;
    TextView txtDate;
    TextView txtTrainId;
    TextView txtTrainType;
    TextView txtCoupeCapacity;
    TextView txtPrice;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_information);

        setupViews();

        getPageInfo();
    }

    private void setupViews() {


        txtTitle = (TextView) findViewById(R.id.txt_trainInfo_title);
        txtDate = (TextView) findViewById(R.id.txt_trainInfo_date);
        txtTrainId = (TextView) findViewById(R.id.txt_trainInfo_trainId);
        txtTrainType = (TextView) findViewById(R.id.txt_trainInfo_type);
        txtCoupeCapacity = (TextView) findViewById(R.id.txt_trainInfo_coupeCapacity);
        txtPrice = (TextView) findViewById(R.id.txt_trainInfo_price);
        imgBack = (ImageView) findViewById(R.id.img_trainInfo_back);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getPageInfo() {

        AlibabaTrainTicketModel model = getIntent().getParcelableExtra("model");

        txtTitle.setText(model.getSource() + " - " + model.getDestination());
        txtDate.setText(model.getDate());
        txtTrainId.setText(model.getTrainId());
        txtTrainType.setText(model.getType());
        txtCoupeCapacity.setText(model.getCoupeCapacity());
        txtPrice.setText(model.getPrice());
    }
}
