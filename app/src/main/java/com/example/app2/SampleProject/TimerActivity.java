package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.app2.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    Button btnStartTimer;
    TextView txtTimer;
    int second = 0;
    int minute = 0;
    int hour = 0;
    boolean isStart = false;
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        setupViews();

        txtTimer.setText("00:00:00");

        btnStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isStart) {
                    displayTime();
                    btnStartTimer.setText("Stop Timer");
                    isStart = true;
                } else {
                    stopTimer();
                    isStart = false;
                    btnStartTimer.setText("Start Timer");
                }

            }
        });
    }

    private void setupViews() {


        btnStartTimer = (Button) findViewById(R.id.btn_timer_startTimer);
        txtTimer = (TextView) findViewById(R.id.txt_timer_showTimer);
    }

    private void displayTime() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                second++;
                if (second == 60) {
                    minute++;
                    second = 0;
                }

                if (minute == 60) {
                    hour++;
                    minute = 0;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtTimer.setText(String.format("%02d", hour) + ":" +
                                String.format("%02d", minute) + ":" +
                                String.format("%02d", second));
                    }
                });
            }
        }, 0, 1000);
    }

    private void stopTimer() {
        timer.purge();
        timer.cancel();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.purge();
        timer.cancel();
    }
}
