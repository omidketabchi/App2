package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.app2.R;

public class AnimationActivity extends AppCompatActivity {

    Button btnXmlAnimation;
    Button btnAnimation;
    ImageView logo;
    ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        setupViews();

        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alphaAnimation();
//                myTranslateAnimation();
//                scaleAnimation();
//                rotateAnimation();
//                setAnimation();
//                changeBackgroundColor();
                useYoyoLibrary();
            }
        });

        btnXmlAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                xmlAnimation();
//                translateXmlAnimation();
//                scaleXmlAnimation();
//                rotateXmlAnimation();
//                setXmlAnimation();
            }
        });
    }

    private void setupViews() {
        btnAnimation = (Button) findViewById(R.id.btn_animation_animation);
        btnXmlAnimation = (Button) findViewById(R.id.btn_animation_xml);
        logo = (ImageView) findViewById(R.id.img_animation_logo);
        parent = (ConstraintLayout) findViewById(R.id.constraint_animation_parent);
    }

    private void alphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        logo.startAnimation(alphaAnimation);
    }

    private void xmlAnimation() {
        AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.alpha);
        logo.startAnimation(alphaAnimation);
    }

    private void myTranslateAnimation() {
//        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,500);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE,
                0, Animation.RELATIVE_TO_PARENT, 1);

//        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0,
//                Animation.ABSOLUTE, 0, Animation.ABSOLUTE,
//                0, Animation.RELATIVE_TO_SELF, 1);

        translateAnimation.setRepeatCount(5);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
//        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setInterpolator(new BounceInterpolator());
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setX(100);
                logo.setY(200);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        logo.startAnimation(translateAnimation);
    }

    private void translateXmlAnimation() {
        TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(AnimationActivity.this,
                R.anim.translate);

        logo.startAnimation(translateAnimation);
    }

    private void scaleAnimation() {
//        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.5f, 1f,
                1.5f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        logo.startAnimation(scaleAnimation);
    }

    private void scaleXmlAnimation() {
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(AnimationActivity.this,
                R.anim.scale);

        logo.startAnimation(scaleAnimation);
    }

    private void rotateAnimation() {
//        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f);
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(10);
        logo.startAnimation(rotateAnimation);
    }

    private void rotateXmlAnimation() {

        RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(AnimationActivity.this,
                R.anim.rotate);

        logo.startAnimation(rotateAnimation);
    }

    private void setAnimation() {
        AnimationSet animationSet = new AnimationSet(true);

        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(10);


        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);

        logo.startAnimation(animationSet);
    }

    private void setXmlAnimation() {
        AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(AnimationActivity.this,
                R.anim.set);

        logo.startAnimation(animationSet);
    }

    private void changeBackgroundColor() {

        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),
                ContextCompat.getColor(getApplicationContext(), R.color.colorWhite),
                ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                parent.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });

        valueAnimator.setDuration(10000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
    }

    private void useYoyoLibrary() {

        YoYo.with(Techniques.FadeInDown)
                .duration(1500)
                .repeat(0)
                .playOn(findViewById(R.id.img_animation_logo));
    }
}

