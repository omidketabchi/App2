package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app2.R;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class RecorderActivity extends AppCompatActivity {

    TextView txtTimer;
    ImageView imgRecord;
    MediaRecorder recorder;
    int sec = 0;
    int min = 0;
    File file;
    boolean isRecording = false;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        setupViews();

        imgRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isRecording) {
                    imgRecord.setImageResource(R.drawable.ic_stop_blue);
                    startRecord();
                    isRecording = true;

                    timer = new Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            sec++;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    min = sec / 60;
                                    int second = sec % 60;
                                    txtTimer.setText(min + " : " + second);
                                }
                            });
                        }
                    }, 0, 1000);

                } else {
                    imgRecord.setImageResource(R.drawable.ic_stop_blue);
                    stopRecord();
                    isRecording = false;
                    timer.purge();
                    timer.cancel();
                }
            }
        });
    }

    private void setupViews() {

        recorder = new MediaRecorder();
        imgRecord = (ImageView) findViewById(R.id.img_record_record);
        txtTimer = (TextView) findViewById(R.id.txt_record_timer);

        file = new File(Environment.
                getExternalStorageDirectory().getPath() + "/omRecord");

        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private void startRecord() {

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(file + "/" + System.currentTimeMillis() + ".amr");

        try {
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecord() {
        recorder.release();
    }

    @Override
    protected void onStop() {
        super.onStop();

        timer.purge();
        timer.cancel();

        recorder.release();
    }
}

