package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app2.R;

public class TestServicesActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStartedService;
    Button btnBoundedService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_services);


        btnStartedService = findViewById(R.id.btn_services_startedService);
        btnBoundedService = findViewById(R.id.btn_services_BoundedService);

        btnStartedService.setOnClickListener(this);
        btnBoundedService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_services_startedService:
                startActivity(new Intent(TestServicesActivity.this, StartedServiceActivity.class));
                break;
            case R.id.btn_services_BoundedService:
                Toast.makeText(TestServicesActivity.this, "it is not implemented.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
