package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.app2.SampleProject.Adapter.MyViewPagerAdapter;
import com.example.app2.SampleProject.Fragments.FirstFragment;
import com.example.app2.SampleProject.Fragments.SecondFragment;
import com.example.app2.SampleProject.Fragments.TestFragment;
import com.example.app2.R;
import com.google.android.material.tabs.TabLayout;

public class ActivityTabLayout extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    MyViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());

        tabLayout = (TabLayout) findViewById(R.id.tab_tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager_tabLayout);

        tabLayout.setupWithViewPager(viewPager);

        pagerAdapter.addFragment(new FirstFragment(), "first fragment");
        pagerAdapter.addFragment(new SecondFragment(), "second fragment");
        pagerAdapter.addFragment(new TestFragment(), "test fragment");

        viewPager.setAdapter(pagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_date_green);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_delete_green);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_edit_green);
    }
}
