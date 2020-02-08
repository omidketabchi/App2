package com.example.app2.SampleProject;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app2.R;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = "LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG, "onBackPressed");
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Log.i(TAG, "KEYCODE_VOLUME_UP");
                break;
            case KeyEvent.KEYCODE_HOME:
                Log.i(TAG, "KEYCODE_HOME");
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Log.i(TAG, "KEYCODE_VOLUME_DOWN");
                break;
            case KeyEvent.KEYCODE_BACK:
                Log.i(TAG, "KEYCODE_BACK");
                break;
            case KeyEvent.KEYCODE_CAMERA:
                Log.i(TAG, "KEYCODE_CAMERA");
                break;
            case KeyEvent.KEYCODE_COMMA:
                Log.i(TAG, "KEYCODE_COMMA");
                break;
                default:
                    Log.i(TAG, "default:" + keyCode);
                    break;
        }
        return super.onKeyLongPress(keyCode, event);
    }
}
