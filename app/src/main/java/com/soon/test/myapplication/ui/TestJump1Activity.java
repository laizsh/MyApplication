package com.soon.test.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.soon.test.myapplication.R;

public class TestJump1Activity extends Activity {

    public static final String TAG = "TestJump1Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_jump1);

        Log.d("TestJump1Activity", "onCreate");
        Intent intent = new Intent(TestJump1Activity.this, TestJump2Activity.class);
        intent.putExtra(TestJump2Activity.KEY_TEST, "soon");
        startActivityForResult(intent, TestJump2Activity.REQUEST_CODE_TEST);

        sCurInstance = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TestJump1Activity", "onDestroy");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TestJump1Activity", "onActivityResult requestCode="+requestCode+",resultCode="+resultCode+",data="+data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState savedInstanceState=" + savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState outState=" + outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    public static TestJump1Activity sCurInstance;
}
