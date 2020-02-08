package com.example.app2.SampleProject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state == null) {
            String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

            Toast.makeText(context, "outgoing call:" + number, Toast.LENGTH_SHORT).show();

            if (number.equals("3256")) {
//                Intent intent1 = new Intent(context, StartActivity.class);
//                context.startActivity(intent1);

                Intent intent1 = context.getPackageManager().
                        getLaunchIntentForPackage("com.example.lastnews");

                if (intent1 != null) {
                    context.startActivity(intent1);
                }
            }

        } else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Toast.makeText(context, "incomming call:" + number, Toast.LENGTH_SHORT).show();
        }
    }
}
