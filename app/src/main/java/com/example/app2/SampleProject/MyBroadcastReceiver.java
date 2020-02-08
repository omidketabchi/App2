package com.example.app2.SampleProject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MyBroadcastReceiver extends BroadcastReceiver {

    MyDataBase myDataBase;
    AlarmManager alarmManager;
    ArrayList<PendingIntent> pendingIntents;
    private int requestCode;
    private static final String LOG = "LOGK";
    int i = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "broadcast is called", Toast.LENGTH_SHORT).show();
        Log.i(LOG, "onReceive omid ketabchi");
        pendingIntents = new ArrayList<>();

        myDataBase = new MyDataBase(context);

        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Cursor cursor = myDataBase.getSomeInfo(1);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            Log.i(LOG, "i = " + i + "");

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            int remember = cursor.getInt(3);
            String date = cursor.getString(4);
            String time = cursor.getString(5);

            int hour = 0;

            String[] dateString = date.split("/");
            String[] timeString = time.split(":");

            Intent alarmIntent = new Intent(context, AlarmActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(context,
                            requestCode,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, Integer.parseInt(dateString[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dateString[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateString[2]));

            if (Integer.parseInt(timeString[0]) > 12) {
                hour = Integer.parseInt(timeString[0]) - 12;
            } else {
                hour = Integer.parseInt(timeString[0]);
            }

            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, Integer.parseInt(timeString[1]));
            calendar.set(Calendar.SECOND, 0);

            calendar.set(Calendar.AM_PM, (Integer.parseInt(timeString[0]) > 12) ? 1 : 0); // 0->AM & 1->PM

            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            pendingIntents.add(pendingIntent);

            requestCode++;
            i++;
        }
    }


}
