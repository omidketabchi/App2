package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app2.SampleProject.NotificationSample.NotificationMainActivity;
import com.example.app2.R;

public class TestNotificationActivity extends AppCompatActivity {

    Button btnNotify;
    Button btnNotificationSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notification);

        btnNotify = (Button) findViewById(R.id.btn_test_notification_notify);
        btnNotificationSample = (Button) findViewById(R.id.btn_test_notification_sample);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNotification();
            }
        });

        btnNotificationSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestNotificationActivity.this, NotificationMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle("title")
                .setContentText("text")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        Intent intent = new Intent(TestNotificationActivity.this, TestNotificationViewActivity.class);
        intent.putExtra("title", "this is title from previous page");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
