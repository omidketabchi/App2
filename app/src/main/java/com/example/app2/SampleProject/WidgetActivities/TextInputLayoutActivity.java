package com.example.app2.SampleProject.WidgetActivities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app2.R;

public class TextInputLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

//        new AlertDialog.Builder(this, R.style.omidStyle)
//                .setTitle("title")
//                .setMessage("message")
//                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(TextInputLayoutActivity.this, "Ok", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(TextInputLayoutActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
//                    }
//                }).show();

    }
}
