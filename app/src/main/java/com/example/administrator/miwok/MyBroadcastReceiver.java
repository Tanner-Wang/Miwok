package com.example.administrator.miwok;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/8.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    //action操作
    final String SMS_RECEIVER = "android.provider.Telephony.SMS_RECEIVED";

    public MyBroadcastReceiver(){}
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVER)){
            Toast.makeText(context, "got a message.", Toast.LENGTH_SHORT).show();
        }
    }
}
