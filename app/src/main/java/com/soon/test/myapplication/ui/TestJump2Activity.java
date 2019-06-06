package com.soon.test.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.soon.test.myapplication.R;

import static com.soon.test.myapplication.ui.TestJump1Activity.KEY_REQ;

public class TestJump2Activity extends Activity {
    private static final String TAG = "TestJump2Activity";
    public static final String KEY_RSP = "key_rsp";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_jump2);

        findViewById(R.id.btn_rsp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(KEY_RSP, hashCode());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        Log.d(TAG, "onCreate@"+hashCode()+" intent=" + getIntent() + "|req hashcode="+getIntent().getIntExtra(KEY_REQ,0));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState@"+hashCode()+" savedInstanceState=" + savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState@"+hashCode()+" outState=" + outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()@"+hashCode());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()@"+hashCode());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()@"+hashCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()@"+hashCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()@"+hashCode());
    }
}
