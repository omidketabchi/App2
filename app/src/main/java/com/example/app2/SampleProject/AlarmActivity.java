package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app2.R;

public class AlarmActivity extends AppCompatActivity {

    TextView txtAlarmRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        txtAlarmRemember = (TextView) findViewById(R.id.txt_alarm_remember);
        txtAlarmRemember.setText("Hello From Alarm Manager");

        //MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sample);
        //mediaPlayer.start();
    }
}
