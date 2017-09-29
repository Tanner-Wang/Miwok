package com.example.administrator.miwok;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建可切换多页面UI
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        WordFragmentPagerAdapter wfpa = new WordFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(wfpa);
        //创建多页面标题
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = new Intent();
        bindService(intent, connection, 0);

    }
    @Override
    protected void onResume(){
        //注册广播接收者
        receiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(receiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause(){
        //注销广播接收者
        unregisterReceiver(receiver);
        super.onPause();
    }

    /**
     * 监控应用服务连接状态
     */
    ServiceConnection connection = new ServiceConnection(){
        //服务绑定时回调此方法。
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        }

        //服务意外崩溃或由于设备内存不足系统杀死服务时回调此方法。
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


}
