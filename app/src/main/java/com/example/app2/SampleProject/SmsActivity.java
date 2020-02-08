package com.example.app2.SampleProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app2.R;

public class SmsActivity extends AppCompatActivity {

    private static final int SMS_REQUEST_CODE = 1001;
    EditText edtNumber;
    EditText edtMessage;
    Button btnSendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        edtNumber = (EditText) findViewById(R.id.edt_sms_number);
        edtMessage = (EditText) findViewById(R.id.edt_sms_message);
        btnSendSms = (Button) findViewById(R.id.btn_sms_sendSms);

        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, SMS_REQUEST_CODE);
                    } else {
                        sendSMS();
                    }
                } else {
                    sendSMS();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SMS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(SmsActivity.this, "you get permision for SMS", Toast.LENGTH_SHORT).show();
            sendSMS();
        } else {
            Toast.makeText(SmsActivity.this, "you denied permision for SMS", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS() {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(edtNumber.getText().toString(), null,
                edtMessage.getText().toString(), null, null);
    }
}
