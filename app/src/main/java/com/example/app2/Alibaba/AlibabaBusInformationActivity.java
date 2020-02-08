package com.example.app2.Alibaba;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Model.AlibabaBusTicketModel;
import com.example.app2.Alibaba.Model.AlibabaChairModel;
import com.example.app2.ChairSelectionActivity;
import com.example.app2.R;

import java.util.ArrayList;
import java.util.List;

public class AlibabaBusInformationActivity extends AppCompatActivity {

    ImageView imgBack;
    TextView txtTitle;
    TextView txtDate;
    TextView txtSource;
    TextView txtType;
    TextView txtTime;
    TextView txtPrice;
    TextView txtSourceTerminal;
    TextView txtDistance;
    TextView txtDestinationTerminal;
    Button btnChooseChair;
    RecyclerView recyclerView;

    List<AlibabaChairModel> chairModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alibaba_bus_information);

        setupViews();

        getIntentInfo();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViews() {

        txtTitle = (TextView) findViewById(R.id.txt_busInfo_title);
        txtDate = (TextView) findViewById(R.id.txt_busInfo_date);
        txtSource = (TextView) findViewById(R.id.txt_busInfo_source);
        txtType = (TextView) findViewById(R.id.txt_busInfo_type);
        txtTime = (TextView) findViewById(R.id.txt_busInfo_time);
        txtPrice = (TextView) findViewById(R.id.txt_busInfo_price);
        txtSourceTerminal = (TextView) findViewById(R.id.txt_busInfo_sourceTerminal);
        txtDistance = (TextView) findViewById(R.id.txt_busInfo_distance);
        txtDestinationTerminal = (TextView) findViewById(R.id.txt_busInfo_destinationTerminal);
        btnChooseChair = (Button) findViewById(R.id.btn_busInfo_chooseChair);
        imgBack = (ImageView) findViewById(R.id.img_busInfo_back);

        recyclerView = (RecyclerView) findViewById(R.id.rv_busInfo_penaltyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getIntentInfo() {

        AlibabaBusTicketModel model = getIntent().getParcelableExtra("model");
        chairModels = getIntent().getParcelableArrayListExtra("chairs");

        for (int i = 0; i < chairModels.size(); i++) {
            Log.i("LOG", "getIntentInfo: " + chairModels.get(i).getChair() + "//"
                    + chairModels.get(i).getStatus());
        }

        txtTitle.setText(model.getSource() + " - " + model.getSourceTerminal());
        txtDate.setText(model.getDate());
        txtSource.setText(model.getSource());
        txtType.setText(model.getType());
        txtTime.setText(model.getTime());
        txtPrice.setText(model.getPrice());
        txtSourceTerminal.setText(model.getSourceTerminal());
        txtDistance.setText(model.getDistance() + " کیلومتر");
        txtDestinationTerminal.setText(model.getDestinationTerminal());

        btnChooseChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlibabaBusInformationActivity.this, ChairSelectionActivity.class);
                intent.putParcelableArrayListExtra("chairs", (ArrayList<? extends Parcelable>) chairModels);
                intent.putExtra("price", model.getPrice());
                startActivity(intent);
            }
        });
    }
}
