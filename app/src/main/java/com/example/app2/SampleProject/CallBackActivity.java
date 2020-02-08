package com.example.app2.SampleProject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app2.R;

public class CallBackActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_back);

        CallBackClass callBackClass = new CallBackClass(this);
        callBackClass.getList(new CallBackClass.OnResponseReceive() {
            @Override
            public void onReceive(String response) {
                Log.i("LOG", "onReceive: " + response);
            }

            @Override
            public void onError(String error) {
                Log.i("LOG", "onError: " + error);

            }
        });

        textView = (TextView) findViewById(R.id.txt_test);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog myDialog = new MyDialog();
                myDialog.show(getSupportFragmentManager(), null);

                myDialog.setOnClickDialogListener(new MyDialog.OnClickDialogListener() {
                    @Override
                    public void onClickDialog(String message) {
                        Toast.makeText(CallBackActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
