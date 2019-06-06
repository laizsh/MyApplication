package com.soon.test.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.soon.test.myapplication.R;

public class TestJump1Activity extends Activity implements View.OnClickListener {

    public static final String TAG = "TestJump1Activity";
    public static final int REQUEST_CODE_TEST  = 7;
    public static final String KEY_REQ = "key_req";

    /*
    * 1.先试试同进程，通过activity的方式传递？
    *
    * */

    Button mBtnReq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_jump1);

        Log.d(TAG, "onCreate@" + hashCode());
        findViewById(R.id.btn_request).setOnClickListener(this);
//        doRequest();
//        sCurInstance = this;
    }

    private void doRequest(){
        Log.d(TAG, "doRequest@" + hashCode());

        Intent intent = new Intent(TestJump1Activity.this, TestJump2Activity.class);
        intent.putExtra(KEY_REQ, hashCode());
        startActivityForResult(intent, REQUEST_CODE_TEST);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy@" + hashCode());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult@"+hashCode()+" requestCode="+requestCode+",resultCode="+resultCode+",data="+data);
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
        Log.d(TAG, "onPause()@" + hashCode());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()@" + hashCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()@" + hashCode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()@" + hashCode());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_request:
                doRequest();
                break;
            default:
                break;
        }
    }

//    public static TestJump1Activity sCurInstance;
}
