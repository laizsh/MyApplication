package com.soon.test.myapplication2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EntryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_test_jump1);

        Button btnNewActivity = findViewById(R.id.btn_request);
        btnNewActivity.setText("btnNewActivity");
        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EntryActivity.this, EntryActivity.class);
                startActivity(i);
            }
        });

        Button btnBackApp1 = findViewById(R.id.btn_request_service);
        btnBackApp1.setText("btnBackApp1");
        btnBackApp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.soon.test.myapplication", "com.soon.test.myapplication.ui.EntryActivity"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        Button btnBackMobileQQ = findViewById(R.id.btn3);
        btnBackMobileQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.wxapi.WXPayEntryActivity"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
