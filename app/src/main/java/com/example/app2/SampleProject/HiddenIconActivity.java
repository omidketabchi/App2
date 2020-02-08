package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app2.R;

public class HiddenIconActivity extends AppCompatActivity {

    Button btnHideAppIcon;
    Button btnOpenApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_icon);

        btnHideAppIcon = (Button) findViewById(R.id.btn_hide_hideIcon);
        btnOpenApp = (Button) findViewById(R.id.btn_hide_openApp);

        btnHideAppIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAppIcon();
            }
        });

        btnOpenApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().
                        getLaunchIntentForPackage("com.sibche.aspardproject.app");

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    private void hideAppIcon() {
        PackageManager packageManager = getPackageManager();

        ComponentName componentName =
                new ComponentName(this, HiddenIconActivity.class);

        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}

