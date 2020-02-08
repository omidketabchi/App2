package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app2.R;

public class EmailActivity extends AppCompatActivity {

    EditText edtSubject;
    EditText edtEmail;
    EditText edtMessage;
    Button btnSendEmail;
    Button btnShowToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        setupViews();

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        btnShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myToast(EmailActivity.this, "Custom Toast", Toast.LENGTH_SHORT);
            }
        });
    }

    private void setupViews() {

        edtEmail = (EditText) findViewById(R.id.edt_email_email);
        edtMessage = (EditText) findViewById(R.id.edt_email_message);
        edtSubject = (EditText) findViewById(R.id.edt_email_subject);
        btnSendEmail = (Button) findViewById(R.id.btn_email_sendEmail);
        btnShowToast = (Button) findViewById(R.id.btn_email_toast);
    }

    private void myToast(Context context, String title, int time) {

//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.custom_toast, null);
//
//        Toast toast = new Toast(context);
//        toast.setView(view);
//        toast.setDuration(time);
//
//        TextView txtTitle = (TextView) toast.getView().findViewById(R.id.txt_myToast_title);
//        txtTitle.setText(title);
//
//        toast.show();

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast, null);

        Toast toast = new Toast(context);
        toast.setView(view);
        ((TextView) toast.getView().findViewById(R.id.txt_myToast_title)).setText(title);

        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    private void sendEmail() {

        String[] emailAddress = new String[]{edtEmail.getText().toString()};

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
        intent.putExtra(Intent.EXTRA_SUBJECT, edtSubject.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, edtMessage.getText().toString());
        intent.setType("plain/text");

        startActivity(intent);
    }
}
