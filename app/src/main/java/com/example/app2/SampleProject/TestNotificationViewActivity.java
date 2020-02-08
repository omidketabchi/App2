package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.app2.R;

public class TestNotificationViewActivity extends AppCompatActivity {

    TextView txtNotificationMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_notification_view);

        txtNotificationMessage = (TextView) findViewById(R.id.txt_notificationView_message);

        txtNotificationMessage.setText(getIntent().getExtras().getString("title"));
    }
}
