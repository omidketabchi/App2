package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.app2.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CommentActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    EditText edtTitle;
    EditText edtDescription;
    TextView txtTime;
    TextView txtDate;
    Button btnSave;
    Button btnShow;
    ImageView imgDate;
    ImageView imgTime;
    AppCompatCheckBox chbRemember;
    MyDataBase myDataBase;
    AlarmManager alarmManager;
    private int requestCode = 1001;
    private TextView txtToolbarTitle;
    private CheckNetworkReceiver checkNetworkReceiver;
    private String get;
    private String setname;

    ArrayList<PendingIntent> pendingIntents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        checkNetworkReceiver = new CheckNetworkReceiver();

        registerReceiver(checkNetworkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

        setupViews();
        txtToolbarTitle.setText(getMyIntent());
        //callBroadcast();

        myDataBase = new MyDataBase(CommentActivity.this);
    }

    private void setupViews() {

        txtToolbarTitle = (TextView) findViewById(R.id.txt_myToolbar_toolbarTitle);
        pendingIntents = new ArrayList<>();
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        edtTitle = (EditText) findViewById(R.id.edt_login_username);
        edtDescription = (EditText) findViewById(R.id.edt_login_password);
        txtTime = (TextView) findViewById(R.id.txt_comment_time);
        txtDate = (TextView) findViewById(R.id.txt_comment_date);
        imgTime = (ImageView) findViewById(R.id.img_comment_time);
        imgDate = (ImageView) findViewById(R.id.img_comment_date);
        btnShow = (Button) findViewById(R.id.btn_comment_show);
        btnSave = (Button) findViewById(R.id.btn_login_save);
        chbRemember = (AppCompatCheckBox) findViewById(R.id.chb_comment_check_alarm);

        imgTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int hour = 0;

                Calendar calendar = Calendar.getInstance();

                if (calendar.get(Calendar.AM_PM) == Calendar.PM) {

                    hour = calendar.get(Calendar.HOUR) + 12;
                }

                TimePickerDialog timePickerDialog = new TimePickerDialog(CommentActivity.this,
                        CommentActivity.this, hour,
                        calendar.get(Calendar.MINUTE), true);

                timePickerDialog.show();
            }
        });

        imgDate.setOnClickListener(new View.OnClickListener() {

            Calendar calendar = Calendar.getInstance();

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CommentActivity.this,
                        CommentActivity.this, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chbRemember.isChecked()) {
                    if (edtTitle.getText().toString().isEmpty() ||
                            edtDescription.getText().toString().isEmpty() ||
                            txtTime.getText().toString().isEmpty() ||
                            txtDate.getText().toString().isEmpty()) {
                        Toast.makeText(CommentActivity.this, "وارد کردن اطلاعات الزامی است.", Toast.LENGTH_SHORT).show();

                    } else {

                        long id = myDataBase.insertRecord(edtTitle.getText().toString(),
                                edtDescription.getText().toString(),
                                chbRemember.isChecked() ? 1 : 0,
                                getCurrentDate(),
                                getCurrentTime());

                        if (id != -1) {
                            setMultipleAlarm();
                        }

                        Toast.makeText(CommentActivity.this, id + "", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (edtTitle.getText().toString().isEmpty() ||
                            edtDescription.getText().toString().isEmpty()) {
                        Toast.makeText(CommentActivity.this, "وارد کردن اطلاعات الزامی است.", Toast.LENGTH_SHORT).show();

                    } else {
                        long id = myDataBase.insertRecord(edtTitle.getText().toString(),
                                edtDescription.getText().toString(),
                                chbRemember.isChecked() ? 1 : 0,
                                getCurrentDate(),
                                getCurrentTime());

                        Toast.makeText(CommentActivity.this, id + "", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        txtTime.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute) + ":" + "00");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        txtDate.setText(String.format("%04d", year) + "/" +
                String.format("%02d", month + 1) + "/" + String.format("%02d", dayOfMonth));
    }

    public String getCurrentDateTime() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy hh:mm:ss aa");

        return dateFormat.format(calendar.getTime());
    }

    public void getCurrentDateAndTime() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int amOrPm = calendar.get(Calendar.AM_PM);

        String time = year + "/" + month + "/" + day + " " +
                hour + ":" + minute + ":" + second;

        Toast.makeText(CommentActivity.this, time, Toast.LENGTH_LONG).show();
        Log.i("LOG", "TIME IS: " + time);
    }

    public void setMultipleAlarm() {

        int hour = 0;

        String[] dateString = txtDate.getText().toString().split("/");
        String[] timeString = txtTime.getText().toString().split(":");

        Intent intent = new Intent(CommentActivity.this, AlarmActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(CommentActivity.this,
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
    }

    public void setAlarm() {

        Intent intent = new Intent(CommentActivity.this, AlarmActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(CommentActivity.this,
                        1001,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.AM_PM, 0); // 0->AM & 1->PM

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void setAlarm2() {

        Intent intent = new Intent(CommentActivity.this, AlarmActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(CommentActivity.this,
                        1002, // for adding more alarms, should declare different RequestCode
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        calendar.set(Calendar.HOUR, 10);
        calendar.set(Calendar.MINUTE, 47);
        calendar.set(Calendar.SECOND, 00);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void callBroadcast() {
        Intent intent = new Intent();

        intent.setAction("omid_ketabchi_broadcast");

        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(checkNetworkReceiver);
    }

    private String getCurrentDate() {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return (year + "/" + month + "/" + day);
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return (hour + ":" + minute + ":" + second);
    }

    private class CheckNetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "connection changed", Toast.LENGTH_SHORT).show();
        }
    }

    private String getMyIntent() {
        return getIntent().getExtras().getString("toolbarTitle");
    }
}


