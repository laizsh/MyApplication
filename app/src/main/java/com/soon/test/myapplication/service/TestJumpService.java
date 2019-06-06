package com.soon.test.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TestJumpService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
