package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.app2.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    Button btnWiFi;
    Button btnBluetooth;
    WifiManager wifiManager;
    SensorManager sensorManager;
    Sensor lightSensor;
    Sensor acSensor;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        btnWiFi = (Button) findViewById(R.id.btn_sensor_wifi);
        btnBluetooth = (Button) findViewById(R.id.btn_sensor_bluetooth);
        seekBar = (SeekBar) findViewById(R.id.sk_sensor_seekbar);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //acSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                getWindow().setAttributes(layoutParams);

                float percent = progress / 100f;
                layoutParams.screenBrightness = percent;

                Log.i("LOG", "percent:" + percent + "///" + "brightness:" +
                        layoutParams.screenBrightness);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SensorActivity.this, "onStartTrackingTouch", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SensorActivity.this, "onStopTrackingTouch", Toast.LENGTH_SHORT).show();
            }
        });

        btnWiFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(true);
                } else {
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.enable();
                } else {
                    bluetoothAdapter.disable();
                }
            }
        });

//        checkInfos();
    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

//        sensorManager.registerListener(new SensorEventListener() {
//                                           @Override
//                                           public void onSensorChanged(SensorEvent event) {
//                                               float x = event.values[0];
//                                               float y = event.values[1];
//                                               float z = event.values[2];
//
//                                               Log.i("LOG", "(x,y,z)=" + x + "," + y + "," + z);
//                                           }
//
//                                           @Override
//                                           public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//                                           }
//                                       }, acSensor,
//                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void checkInfos() {
        TelephonyManager telephonyManager =
                (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

//        String IMEI = "";

//        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                IMEI = telephonyManager.getImei();
//            } else {
//                IMEI = telephonyManager.getDeviceId();
//            }
//
//            return;
//        }


        String SimOperatorName = telephonyManager.getSimOperatorName();
        int PhoneType = telephonyManager.getPhoneType();
        String NetworkOperatorName = telephonyManager.getNetworkOperatorName();
        String SimCountryIso = telephonyManager.getSimCountryIso();
//        String SimSerialNumber = telephonyManager.getSimSerialNumber();
//        String DeviceSoftwareVersion = telephonyManager.getDeviceSoftwareVersion();
//        String SimState_0 = telephonyManager.getSimState(0);
//        String SimState_1 = telephonyManager.getSimState(1);


//        String IMEI = telephonyManager.getImei();

//        Log.i("LOG", "IMEI:" + IMEI);
        Log.i("LOG", "SimOperatorName:" + SimOperatorName);
        Log.i("LOG", "PhoneType:" + PhoneType);
        Log.i("LOG", "NetworkOperatorName:" + NetworkOperatorName);
        Log.i("LOG", "SimCountryIso:" + SimCountryIso);

        Log.i("LOG", "Build.MODEL:" + Build.MODEL);
        Log.i("LOG", "Build.BOARD:" + Build.BOARD);
        Log.i("LOG", "Build.BOOTLOADER:" + Build.BOOTLOADER);
        Log.i("LOG", "Build.BRAND:" + Build.BRAND);
        Log.i("LOG", "Build.DEVICE:" + Build.DEVICE);
        Log.i("LOG", "Build.DISPLAY:" + Build.DISPLAY);
        //Log.i("LOG", "Build.VERSION:" + Build.VERSION);
        Log.i("LOG", "Build.HARDWARE:" + Build.HARDWARE);
        Log.i("LOG", "Build.MANUFACTURER:" + Build.MANUFACTURER);
        Log.i("LOG", "Build.FINGERPRINT:" + Build.FINGERPRINT);
        Log.i("LOG", "Build.TIME:" + Build.TIME);
        Log.i("LOG", "Build.USER:" + Build.USER);
        Log.i("LOG", "Build.TYPE:" + Build.TYPE);
        Log.i("LOG", "Build.PRODUCT:" + Build.PRODUCT);
        Log.i("LOG", "Build.ID:" + Build.ID);
        Log.i("LOG", "Build.HOST:" + Build.HOST);
        Log.i("LOG", "Build.getRadioVersion:" + Build.getRadioVersion());

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        String detail = displayMetrics.density + "";
        int sizeX = displayMetrics.widthPixels;
        int sizeY = displayMetrics.heightPixels;

        Log.i("LOG", "detail:" + detail);
        Log.i("LOG", "sizeX:" + sizeX);
        Log.i("LOG", "sizeY:" + sizeY);

        PackageManager packageManager = getPackageManager();

        List<PackageInfo> installedPackages =
                packageManager.getInstalledPackages
                        (packageManager.GET_META_DATA | packageManager.GET_PERMISSIONS);

//        for (PackageInfo packageInfo : installedPackages) {
//            Log.i("LOG", "packageName" + packageInfo.packageName);
//            Log.i("LOG", "versionName" + packageInfo.versionName);
//            Log.i("LOG", "activities" + packageInfo.activities);
//
//            if (packageInfo.activities != null) {
//                for (ActivityInfo activityInfo : packageInfo.activities) {
//                    Log.i("LOG", "activityInfo.name: " + packageInfo.packageName
//                            + activityInfo.name);
//                }
//            }
//
//            if (packageInfo.requestedPermissions != null) {
//                for (String permission : packageInfo.requestedPermissions) {
//                    Log.i("LOG", "permission: " + packageInfo.packageName + permission);
//                }
//            }
//        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float light = event.values[0];
        Log.i("LOG", "light:" + light);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
