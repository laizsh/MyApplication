package com.soon.test.myapplication.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.soon.test.myapplication.R;
import com.soon.test.myapplication.utils.Utils;

public class AnimDemoActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_demo_activity);

        View imgMoney = (View)findViewById(R.id.img_money);

        int imgMoneyWidth = Utils.dip2px(this, 75);
        int imgMoneyHeight = Utils.dip2px(this, 75);

//        imgMoney.setPivotX(imgMoneyWidth - Utils.dip2px(this, 50));
//            imgMoney.setPivotY(imgMoneyHeight/2);
        imgMoney.setPivotX(imgMoneyWidth/2);
        imgMoney.setPivotY(imgMoneyHeight/2+Utils.dip2px(this, 15));

        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(imgMoney, "rotation", -5f, 20f);
        rotateAnim.setDuration(200);
        rotateAnim.setRepeatMode(ObjectAnimator.REVERSE);
        rotateAnim.setRepeatCount(ObjectAnimator.INFINITE);
        rotateAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnim.start();
    }
}
