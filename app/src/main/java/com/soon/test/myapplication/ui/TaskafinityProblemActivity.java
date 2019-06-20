package com.soon.test.myapplication.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.soon.test.myapplication.R;

public class TaskafinityProblemActivity extends Activity {

    public static TaskafinityProblemActivity sActivity;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_test_jump1);

        findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskafinityProblemActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_request_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskafinityProblemActivity.this, TaskafinityProblemActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)/*.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)*/;
                startActivity(intent);
            }
        });

        Button btnGoToApp2 = findViewById(R.id.btn3);
        btnGoToApp2.setText("btnGoToApp2");
        btnGoToApp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.soon.test.myapplication2", "com.soon.test.myapplication2.EntryActivity"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        sActivity = this;
    }

    public static void bringStackToFront(){
        if(sActivity != null){
//            ActivityManager activityManager = (ActivityManager) sActivity.getSystemService(Context.ACTIVITY_SERVICE);
            sActivity.moveTaskToBack();
        }
    }
}
