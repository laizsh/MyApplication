package com.soon.test.myapplication.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.soon.test.myapplication.R;
import com.soon.test.myapplication.utils.Utils;

public class AnimCameraActivity extends Activity {
    private TextView txtBalance, txtBalanceTitle;
    private View viewLayout;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_camera_activity);


        txtBalance = (TextView) findViewById(R.id.txt_balance);
        txtBalanceTitle = (TextView) findViewById(R.id.txt_balance_title);
        viewLayout = (View) findViewById(R.id.layout_balance);
        viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewLayout.setRotationX(0);
                viewLayout.setTranslationY(0);
                viewLayout.setAlpha(1);
                doAnim4();
            }
        });

//        TextView txtQb = (TextView) findViewById(R.id.txt_qb);
    }


    //这种方式很不自然
    private void doAnim(){
        txtBalance.setPivotY(0);
        txtBalance.setCameraDistance(100000);

        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(txtBalance,"rotationX", 0, 40, 0);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
//        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                System.out.println("onAnimationUpdate animation:" + animation.getAnimatedValue());
//                if((Float)animation.getAnimatedValue() < 0){
//                    txtBalance.setPivotY(Utils.dip2px(AnimCameraActivity.this, 100));
//                }
//            }
//        });
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                txtBalance.setPivotY(Utils.dip2px(AnimCameraActivity.this, 100));
                txtBalance.setCameraDistance(100000);
                ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(txtBalance,"rotationX", txtBalance.getRotationX(), -80);
                objectAnimator.setDuration(500);
                objectAnimator.setInterpolator(new DecelerateInterpolator(2));
                objectAnimator.start();
            }
        });
        objectAnimator.start();


//        txtBalance.setPivotY(Utils.dip2px(this, 50));
//        txtBalance.setCameraDistance(100000);
//        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(txtBalance,"rotationX", 0, -80);
//        objectAnimator.setDuration(10000);
//        objectAnimator.start();

//        txtBalance.animate().rotationX(-80).setDuration(2000).start();
    }

    //这种方式很不自然
    private void doAnim2(){
//        txtBalance.setPivotY(0);
//        txtBalance.setPivotY(Utils.dip2px(AnimCameraActivity.this, 200));
        txtBalance.setCameraDistance(100000);

        final int txtBalanceHeight = Utils.dip2px(AnimCameraActivity.this, 100);

        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(txtBalance,"rotationX", 0, 40, -80);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if((Float)animation.getAnimatedValue() >= 0) {
                    float pivotY = ((40f-(Float)animation.getAnimatedValue())/40f) * (txtBalanceHeight/2);
                    txtBalance.setPivotY(pivotY);
                }else{
                    float pivotY = (txtBalanceHeight/2) + ((0-(Float)animation.getAnimatedValue())/80f) * (txtBalanceHeight/2);
                    txtBalance.setPivotY(pivotY);
                }

//                if(animation.getAnimatedFraction()<0.6f){
//                    txtBalance.setPivotY(0);
//                }else{
//                    txtBalance.setPivotY(Utils.dip2px(AnimCameraActivity.this, 100));
//                }

                System.out.println("onAnimationUpdate animation:" + animation.getAnimatedValue());
//                if((Float)animation.getAnimatedValue() < 0){
//                    txtBalance.setPivotY(Utils.dip2px(AnimCameraActivity.this, 100));
//                }
            }
        });
//        objectAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                txtBalance.setPivotY(Utils.dip2px(AnimCameraActivity.this, 100));
//                txtBalance.setCameraDistance(100000);
//                ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(txtBalance,"rotationX", txtBalance.getRotationX(), -80);
//                objectAnimator.setDuration(500);
//                objectAnimator.setInterpolator(new DecelerateInterpolator(2));
//                objectAnimator.start();
//            }
//        });
        objectAnimator.start();


//        txtBalance.setPivotY(Utils.dip2px(this, 50));
//        txtBalance.setCameraDistance(100000);
//        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(txtBalance,"rotationX", 0, -80);
//        objectAnimator.setDuration(10000);
//        objectAnimator.start();

//        txtBalance.animate().rotationX(-80).setDuration(2000).start();
    }

    //这种方式很不自然
    private void doAnim3(){
        final int txtBalanceHeight = Utils.dip2px(AnimCameraActivity.this, 100);
        float upPercent = 0.2f;
        float upRotation = 90 * upPercent;
        float upTransLationY = - (txtBalanceHeight/2f) * upPercent;
        PropertyValuesHolder holderRotationX = PropertyValuesHolder.ofFloat("rotationX",0, upRotation, -90);
        PropertyValuesHolder holderTranslationY = PropertyValuesHolder.ofFloat("translationY",0, upTransLationY, txtBalanceHeight/2);
        PropertyValuesHolder holderAlpha = PropertyValuesHolder.ofFloat("alpha",1,0);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(viewLayout, holderRotationX, holderTranslationY,holderAlpha);
        animator.setDuration(800);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

//        txtBalance.setPivotY(Utils.dip2px(this, 50));
//        txtBalance.setCameraDistance(100000);
//        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(txtBalance,"rotationX", 0, -80);
//        objectAnimator.setDuration(10000);
//        objectAnimator.start();

//        txtBalance.animate().rotationX(-80).setDuration(2000).start();
    }

    private Animator getBalanceUpAnim(){
        final int txtBalanceHeight = Utils.dip2px(AnimCameraActivity.this, 100);
        float upPercent = 0.2f;
        float upRotation = 90 * upPercent;
        float upTransLationY = - (txtBalanceHeight/2f) * upPercent;

        PropertyValuesHolder holderRotationX = PropertyValuesHolder.ofFloat("rotationX",0, upRotation);
        PropertyValuesHolder holderTranslationY = PropertyValuesHolder.ofFloat("translationY",0, upTransLationY);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(viewLayout, holderRotationX, holderTranslationY);
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.start();

        return animator;
    }

    private Animator getBalanceDownAnim(){
        final int txtBalanceHeight = Utils.dip2px(AnimCameraActivity.this, 100);
        PropertyValuesHolder holderRotationX = PropertyValuesHolder.ofFloat("rotationX", -90);
        PropertyValuesHolder holderTranslationY = PropertyValuesHolder.ofFloat("translationY", txtBalanceHeight/2);
        PropertyValuesHolder holderAlpha = PropertyValuesHolder.ofFloat("alpha",1,0);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(viewLayout, holderRotationX, holderTranslationY,holderAlpha);
        animator.setDuration(600);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                txtBalance.setText("300");
                txtBalanceTitle.setText("Q币");
            }
        });
//        animator.start();

        return animator;
    }

    private Animator getQbDownAnim(){
//        txtBalance.setText("300");
//        txtBalanceTitle.setText("Q币");

        final int txtBalanceHeight = Utils.dip2px(AnimCameraActivity.this, 100);
        float upPercent = 0.4f;
        float upRotation = 90 * upPercent;
        float upTransLationY = - (txtBalanceHeight/2f) * upPercent;

        PropertyValuesHolder holderRotationX = PropertyValuesHolder.ofFloat("rotationX", upRotation, 0);
        PropertyValuesHolder holderTranslationY = PropertyValuesHolder.ofFloat("translationY", upTransLationY,0);
        PropertyValuesHolder holderAlpha = PropertyValuesHolder.ofFloat("alpha",0,1);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(viewLayout, holderRotationX, holderTranslationY,holderAlpha);
        animator.setDuration(600);
        animator.setInterpolator(new OvershootInterpolator());
        animator.setStartDelay(300);
//        animator.start();

        return animator;
    }

    private void doAnim4(){
        Animator balanceUpAnim = getBalanceUpAnim();
        Animator balanceDownAnim = getBalanceDownAnim();
        Animator qbDownAnim = getQbDownAnim();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(balanceUpAnim, balanceDownAnim, qbDownAnim);
        animatorSet.start();

//        final int txtBalanceHeight = Utils.dip2px(AnimCameraActivity.this, 100);
//        float upPercent = 0.2f;
//        float upRotation = 90 * upPercent;
//        float upTransLationY = - (txtBalanceHeight/2f) * upPercent;
//
//
//        PropertyValuesHolder holderRotationX = PropertyValuesHolder.ofFloat("rotationX",0, upRotation,0, -90);
//        PropertyValuesHolder holderTranslationY = PropertyValuesHolder.ofFloat("translationY",0, upTransLationY, 0, txtBalanceHeight/2);
//        PropertyValuesHolder holderAlpha = PropertyValuesHolder.ofFloat("alpha",1,1,0.5f,0);
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(viewLayout, holderRotationX, holderTranslationY,holderAlpha);
//        animator.setDuration(5000);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.start();

//        txtBalance.setPivotY(Utils.dip2px(this, 50));
//        txtBalance.setCameraDistance(100000);
//        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(txtBalance,"rotationX", 0, -80);
//        objectAnimator.setDuration(10000);
//        objectAnimator.start();

//        txtBalance.animate().rotationX(-80).setDuration(2000).start();
    }
}
