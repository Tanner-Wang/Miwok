package com.example.administrator.miwok;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Administrator on 2017/9/9.
 */

public class MyService extends Service {

    MyBinder myBinder;

    public MyService(){}

    public class MyBinder extends Binder{
        public Service getMyService(){
            return new MyService();
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
