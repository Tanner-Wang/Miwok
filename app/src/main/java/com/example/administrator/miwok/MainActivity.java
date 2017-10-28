package com.example.administrator.miwok;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Tanner
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建可切换多页面UI
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        WordFragmentPagerAdapter wfpa = new WordFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setPageTransformer(true, new MyViewPagerTransformer());
        viewPager.setAdapter(wfpa);
        //创建多页面标题
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

}
