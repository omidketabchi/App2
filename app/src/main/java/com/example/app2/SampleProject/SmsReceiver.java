package com.example.app2.SampleProject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import javax.sql.CommonDataSource;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");

            if (pdus.length == 0) {
                return;
            }

            SmsMessage[] messages = new SmsMessage[pdus.length];
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                sb.append(messages[i].getMessageBody());
            }

            String sender = messages[0].getOriginatingAddress();
            String message = sb.toString();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
