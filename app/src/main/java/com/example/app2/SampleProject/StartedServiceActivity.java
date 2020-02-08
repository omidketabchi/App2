package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app2.R;
import com.example.app2.SampleProject.Service.MyService;

public class StartedServiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreateService;
    Button btnStartService;
    Button btnStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_service);

        btnStartService = (Button) findViewById(R.id.btn_started_startedService);
        btnStopService = (Button) findViewById(R.id.btn_started_stopService);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_started_startedService:
//                Toast.makeText(StartedServiceActivity.this, "btn_started_startedService", Toast.LENGTH_SHORT).show();
                startService(new Intent(StartedServiceActivity.this, MyService.class));
                break;
            case R.id.btn_started_stopService:
//                Toast.makeText(StartedServiceActivity.this, "btn_started_stopService", Toast.LENGTH_SHORT).show();
                stopService(new Intent(StartedServiceActivity.this, MyService.class));
                break;
        }
    }
}
