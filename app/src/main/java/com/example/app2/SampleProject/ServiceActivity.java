package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.app2.R;
import com.example.app2.SampleProject.Service.TestService;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Intent intent = new Intent(ServiceActivity.this, TestService.class);

        startService(intent);
    }
}
