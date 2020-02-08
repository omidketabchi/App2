package com.example.app2.SampleProject;

import android.app.Application;
import android.util.Log;

public class G extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("TEST", "onCreate G Class");
    }
}
