package com.example.app2.CafeBazar.CafeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app2.CafeBazar.CafeAdapter.CatViewPagerAdapter;
import com.example.app2.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentCats extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    CatViewPagerAdapter catViewPagerAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cafe_cats, container, false);

        setupViews();


        return view;
    }

    private void setupViews() {

        tabLayout = (TabLayout) view.findViewById(R.id.tab_fragmentCat_catTablayout);
        viewPager = (ViewPager) view.findViewById(R.id.vp_fragmentCat_viewPager);

        catViewPagerAdapter = new CatViewPagerAdapter(getChildFragmentManager());

        catViewPagerAdapter.addFragment(new FragmentGameCat(), "دسته بازی ها");
        catViewPagerAdapter.addFragment(new FragmentApplicationCat(), "دسته برنامه ها");

        viewPager.setAdapter(catViewPagerAdapter);
        viewPager.setCurrentItem(1);

        tabLayout.setupWithViewPager(viewPager);
    }
}
