package com.soon.test.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.soon.test.myapplication.ui.TestJump2Activity;

public class TestJumpService extends Service {
    private static final String TAG = "TestJumpService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate@" + hashCode());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand@" + hashCode() + " flags:" + flags + "  startId:"+startId );

        if ((flags & START_FLAG_REDELIVERY) != 0) {
            return START_NOT_STICKY;
        }

        intent.setClass(this, TestJump2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        return START_NOT_STICKY;
    }
}
