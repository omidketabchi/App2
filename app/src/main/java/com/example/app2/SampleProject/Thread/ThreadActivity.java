package com.example.app2.SampleProject.Thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app2.R;

public class ThreadActivity extends AppCompatActivity {

    Button btnThread;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        handler = new Handler();

        btnThread = (Button) findViewById(R.id.btn_thread_thread);

        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThreadActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 200000; i++) {
                    String str = new String();
                    Log.i("LOG", "onCreate: " + i);

                    if(i == 200000){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ThreadActivity.this, "worker thread finishd!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });

        thread.start();
    }

    private void setupViews() {
        btnThread = (Button) findViewById(R.id.btn_thread_thread);
    }
}
