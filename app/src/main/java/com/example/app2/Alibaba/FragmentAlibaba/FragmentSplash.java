package com.example.app2.Alibaba.FragmentAlibaba;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.app2.Alibaba.Receiver.CheckInternetReceiver;
import com.example.app2.R;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentSplash extends Fragment {

    Timer timer;
    View view;
    ImageView imgLeft;
    ImageView imgRight;
    ImageView imgLogo;
    CheckInternetReceiver checkInternetReceiver;
    LinearLayout linearErrorConnection;
    Handler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkInternetReceiver = new CheckInternetReceiver();
        getContext().registerReceiver(checkInternetReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.alibaba_splash_screen, container, false);

        setupViews();

        checkInternetReceiver.setOnCheckConnection(new CheckInternetReceiver.OnCheckConnection() {
            @Override
            public void onErrorReceive() {
                linearErrorConnection.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceive() {

                timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ((AppCompatActivity)container.getContext()).getSupportFragmentManager().beginTransaction().remove(FragmentSplash.this).commit();
                                timer.purge();
                                timer.cancel();
                            }
                        });
                    }

                    //period must be positive
                }, 10, 1);

            }
        });

        leftAnimation();
        rightAnimation();

        return view;
    }

    private void setupViews() {

        handler = new Handler();
        imgLeft = (ImageView) view.findViewById(R.id.img_splash_left);
        imgRight = (ImageView) view.findViewById(R.id.img_splash_right);
        imgLogo = (ImageView) view.findViewById(R.id.img_splash_logo);
        linearErrorConnection = (LinearLayout) view.findViewById(R.id.ll_splash_error_connection);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        timer.purge();
        timer.cancel();

        getContext().unregisterReceiver(checkInternetReceiver);
    }

    private void leftAnimation() {
        AnimationSet animationSet = new AnimationSet(true);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);


        AlphaAnimation alphaAnimation = new AlphaAnimation(1, .7f);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);

        imgLeft.setAnimation(animationSet);
    }

    private void rightAnimation() {
        AnimationSet animationSet = new AnimationSet(true);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new AnticipateOvershootInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);


        AlphaAnimation alphaAnimation = new AlphaAnimation(1, .7f);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);

        imgRight.setAnimation(animationSet);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        timer.purge();
        timer.cancel();
    }
}
