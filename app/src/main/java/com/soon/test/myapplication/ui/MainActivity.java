package com.soon.test.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.soon.test.myapplication.R;

public class MainActivity extends Activity{
    private LinearLayout mBtnsLayout;

    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mBtnsLayout = (LinearLayout)findViewById(R.id.btns_layout);

        addBtn("database", DataBaseActivity.class);
        addBtn("popupWindow", PopupWindowActivity.class);
        addBtn("testJumpActivity", TestJump1Activity.class);
        //       addBtn("testReqRsp", TestReqRspActivity.class);
        addBtn("animDemoActivity", AnimDemoActivity.class);
        addBtn("animCameraActivity", AnimCameraActivity.class);
        addBtn("TaskAffinityActivity", TaskafinityProblemActivity.class);


        Button button = new Button(this);
        button.setText("new Activity in null main stack");
        mBtnsLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskafinityProblemActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        if(mainActivity != null && mainActivity.getTaskId() != this.getTaskId()){
            mainActivity.finish();
        }

        mainActivity = this;
    }

    private void addBtn(String title, final Class<?> cls){
        Button button = new Button(this);
        button.setText(title);
        mBtnsLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, cls);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TestJump1Activity", " mainActivity onDestroy");
    }
}
