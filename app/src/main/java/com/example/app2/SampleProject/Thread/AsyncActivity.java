package com.example.app2.SampleProject.Thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.app2.R;

public class AsyncActivity extends AppCompatActivity {

    Button btnAsyncThread;
    FrameLayout frameLayout;
    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        btnAsyncThread = (Button)findViewById(R.id.btn_async_run);
        frameLayout = (FrameLayout) findViewById(R.id.frm_async_thread);
        txtShow = (TextView) findViewById(R.id.txt_thread_show);

        MyAsyncTask myAsyncTask = new MyAsyncTask(frameLayout, txtShow);

        myAsyncTask.execute();

//        btnAsyncThread.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
