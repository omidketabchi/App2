package com.example.app2.SampleProject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.app2.Alibaba.AlibabaMainActivity;
import com.example.app2.CafeBazar.Cafe_MainActivity;
import com.example.app2.R;
import com.example.app2.SampleProject.Server.ValyActivity;
import com.example.app2.SampleProject.Thread.AsyncActivity;
import com.example.app2.SampleProject.Thread.ThreadActivity;
import com.example.app2.SampleProject.Toolbar.ToolbarActivity;

public class
StartActivity extends AppCompatActivity {

    CardView sqliteParent;
    CardView cardToolbar;
    CardView cardServer;
    CardView cardThreadSync;
    CardView cardThreadAsync;
    CardView cardFragment;
    CardView cardTabLayout;
    CardView cardPermission;
    CardView cardCamera;
    CardView cardSms;
    CardView cardEmail;
    CardView cardMaterial;
    CardView keshoMaterial;
    CardView sampleNavigation;
    CardView cardSensor;
    CardView cardShared;
    CardView cardAnimation;
    CardView cardVideo;
    CardView cardMusic;
    CardView cardService;
    CardView cardTypeOfServices;
    CardView cardNotification;
    CardView cardRecorder;
    CardView cardDownload;
    CardView cardHiddenIcon;
    CardView cardTimer;
    CardView cardSwipRefresh;
    CardView cardCafe;
    CardView cardSmsVerification;
    CardView cardWidgetTest;
    CardView cardLifeCycle;
    CardView cardCallBack;
    CardView cardAliBaba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        setupViews();

        listeners();
    }

    private void setupViews() {

        sqliteParent = (CardView) findViewById(R.id.card_start_parent);
        cardToolbar = (CardView) findViewById(R.id.card_start_toolbar);
        cardServer = (CardView) findViewById(R.id.card_start_server);
        cardThreadSync = (CardView) findViewById(R.id.card_start_thread_sync);
        cardThreadAsync = (CardView) findViewById(R.id.card_start_thread_async);
        cardFragment = (CardView) findViewById(R.id.card_start_fragment);
        cardTabLayout = (CardView) findViewById(R.id.card_start_tabLayout);
        cardPermission = (CardView) findViewById(R.id.card_start_runTimePermission);
        cardCamera = (CardView) findViewById(R.id.card_start_camera);
        cardSms = (CardView) findViewById(R.id.card_start_sms);
        cardEmail = (CardView) findViewById(R.id.card_start_email);
        cardMaterial = (CardView) findViewById(R.id.card_start_materialDesign);
        keshoMaterial = (CardView) findViewById(R.id.card_start_kesho_materialDesign);
        sampleNavigation = (CardView) findViewById(R.id.card_start_sampleNavigation);
        cardSensor = (CardView) findViewById(R.id.card_start_sendor);
        cardShared = (CardView) findViewById(R.id.card_start_sharedPrefrences);
        cardAnimation = (CardView) findViewById(R.id.card_start_animation);
        cardVideo = (CardView) findViewById(R.id.card_start_player);
        cardMusic = (CardView) findViewById(R.id.card_start_music);
        cardService = (CardView) findViewById(R.id.card_start_service);
        cardTypeOfServices = (CardView) findViewById(R.id.card_type_of_services);
        cardNotification = (CardView) findViewById(R.id.card_start_notification);
        cardRecorder = (CardView) findViewById(R.id.card_start_recorder);
        cardDownload = (CardView) findViewById(R.id.card_start_download);
        cardHiddenIcon = (CardView) findViewById(R.id.card_start_hidden_icon);
        cardTimer = (CardView) findViewById(R.id.card_start_timer);
        cardSwipRefresh = (CardView) findViewById(R.id.card_start_swip_refresh);
        cardCafe = (CardView) findViewById(R.id.card_start_cafe);
        cardSmsVerification = (CardView) findViewById(R.id.card_start_sms_verification);
        cardWidgetTest = (CardView) findViewById(R.id.card_start_widget_test);
        cardLifeCycle = (CardView) findViewById(R.id.card_start_life_cycle);
        cardCallBack = (CardView) findViewById(R.id.card_start_callBack);
        cardAliBaba = (CardView) findViewById(R.id.card_start_alibaba);
    }

    private void listeners() {

        sqliteParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, CommentActivity.class);
                intent.putExtra("toolbarTitle", "آموزش پایگاه داده و لیست ها");
                startActivity(intent);
            }
        });

        cardToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ToolbarActivity.class);
                intent.putExtra("title", "toolbar");
                startActivity(intent);
            }
        });

        cardServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ValyActivity.class);
                intent.putExtra("title", "لیست محصولات");
                startActivity(intent);
            }
        });

        cardThreadSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ThreadActivity.class);
                startActivity(intent);
            }
        });

        cardThreadAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, AsyncActivity.class);
                startActivity(intent);
            }
        });

        cardFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SampleFragment.class);
                startActivity(intent);
            }
        });

        cardTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ActivityTabLayout.class);
                startActivity(intent);
            }
        });

        cardPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, PermissionActivity.class);
                startActivity(intent);
            }
        });

        cardCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        cardSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SmsActivity.class);
                startActivity(intent);
            }
        });

        cardEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, EmailActivity.class);
                startActivity(intent);
            }
        });

        cardMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MaterialActivity.class);
                startActivity(intent);
            }
        });

        keshoMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, KeshoActivity.class);
                startActivity(intent);
            }
        });

        sampleNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ActivitySampleNavigation.class);
                startActivity(intent);
            }
        });

        cardSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SensorActivity.class);
                startActivity(intent);
            }
        });

        cardShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LoginActivity2.class);
                startActivity(intent);
            }
        });

        cardAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });

        cardVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, PlayerActivity.class);
                startActivity(intent);
            }
        });

        cardMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });

        cardService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });

        cardTypeOfServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, TestServicesActivity.class);
                startActivity(intent);
            }
        });

        cardNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, TestNotificationActivity.class);
                startActivity(intent);
            }
        });

        cardRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, RecorderActivity.class);
                startActivity(intent);
            }
        });

        cardDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, DownloaderActivity.class);
                startActivity(intent);
            }
        });

        cardHiddenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, HiddenIconActivity.class);
                startActivity(intent);
            }
        });

        cardTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });

        cardSwipRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SwipRefreshActivity.class);
                startActivity(intent);
            }
        });

        cardCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, Cafe_MainActivity.class);
                startActivity(intent);
            }
        });

        cardSmsVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SmsVerificationActivity.class);
                startActivity(intent);
            }
        });

        cardWidgetTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, WidgetTestActivity.class);
                startActivity(intent);
            }
        });

        cardLifeCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LifeCycleActivity.class);
                startActivity(intent);
            }
        });

        cardCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, CallBackActivity.class);
                startActivity(intent);
            }
        });

        cardAliBaba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, AlibabaMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
