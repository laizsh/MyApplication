package com.soon.test.myapplication.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.soon.test.myapplication.R;
import com.soon.test.myapplication.service.TestJumpService;

import java.lang.ref.WeakReference;

public class TestJump1Activity extends Activity implements View.OnClickListener {

    public static final String TAG = "TestJump1Activity";
    public static final int REQUEST_CODE_TEST  = 7;
    public static final String KEY_REQ = "key_req";
    public static final String KEY_RECEIVER = "key_ipc_receiver";
    public static final String KEY_IS_REBOOT = "key_is_reboot";

    private boolean mIsReboot = false;

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
        findViewById(R.id.btn_request_service).setOnClickListener(this);

        //test retry in onCreate()

//        if(savedInstanceState != null){
//            mIsReboot = savedInstanceState.getBoolean(KEY_IS_REBOOT,false);
//        }
//        if(!mIsReboot){
//            doRequest();
//        }
//        doRequest();

        //        sCurInstance = this;
    }

    private void doRequest(){
        Log.d(TAG, "doRequest@" + hashCode());

        Intent intent = new Intent(TestJump1Activity.this, TestJump2Activity.class);
        intent.putExtra(KEY_REQ, hashCode());
        startActivityForResult(intent, REQUEST_CODE_TEST);
    }

    private void doRequestByService(){
        Log.d(TAG, "doRequestByService@" + hashCode());

        Intent intent = new Intent(TestJump1Activity.this, TestJumpService.class);
        intent.putExtra(KEY_REQ, hashCode());
        intent.putExtra(KEY_RECEIVER, getReceiver());
        startService(intent);
//        startActivityForResult(intent, REQUEST_CODE_TEST);
    }

    private ResultReceiver getReceiver(){
        return getIPCReceiver(new CompactReceiver(new Handler(), this));
    }

    private ResultReceiver getIPCReceiver(ResultReceiver r){
        if(r != null){
            Parcel parcel = Parcel.obtain();
            r.writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            ResultReceiver receiverForSending = ResultReceiver.CREATOR.createFromParcel(parcel);
            parcel.recycle();
        }
        return r;
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

        finish();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState@"+hashCode()+" savedInstanceState=" + savedInstanceState);
        mIsReboot = savedInstanceState.getBoolean(KEY_IS_REBOOT);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState@"+hashCode()+" outState=" + outState);
        outState.putBoolean(KEY_IS_REBOOT, true);
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
        Log.d(TAG, "onResume()@" + hashCode() + ", mIsReboot="+mIsReboot);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_request:
                doRequest();
                break;
            case R.id.btn_request_service:
                doRequestByService();
                break;
            default:
                break;
        }
    }

//    public static TestJump1Activity sCurInstance;

    public static class CompactReceiver extends ResultReceiver {

        private WeakReference<TestJump1Activity> mLogicRef;
        private TestJump1Activity mLogic;

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public CompactReceiver(Handler handler, TestJump1Activity logic) {
            super(handler);
            mLogicRef = new WeakReference<TestJump1Activity>(logic);
//            mLogic = logic;
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            TestJump1Activity logic = mLogicRef.get();
//            logic = mLogic;
            Log.d(TAG, "onReceiveResult() resultCode:" + resultCode + ",resultData=" + resultData + ",act=" + logic);
            if (logic != null) {
                Intent intent = new Intent();
                intent.putExtras(resultData);
                logic.onActivityResult(REQUEST_CODE_TEST, Activity.RESULT_OK, intent);
            }
        }
    }
}
