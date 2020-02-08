package com.example.app2.CafeBazar.CafeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app2.CafeBazar.CafeAdapter.AppViewPagerAdapter;
import com.example.app2.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentBests extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        for managing dome fragments in a specific fragment, we must use getChildFragmentManager.
        View view = inflater.inflate(R.layout.fragment_cafe_bests,
                container, false);

        tabLayout = view.findViewById(R.id.tab_fragmentBest_tablayout);
        viewPager = view.findViewById(R.id.vp_fragmentBest_viewPager);

//        AppViewPagerAdapter adapter = new AppViewPagerAdapter(((AppCompatActivity) getContext()).getSupportFragmentManager());
//        when we have some fragments in another fragment, we can call getFragmentManager in their parent fragment.
        AppViewPagerAdapter adapter = new AppViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new FragmentPersian(), "فارسی ها");
        adapter.addFragment(new FragmentMostPopular(), "محبوب ترین ها");
        adapter.addFragment(new FragmentMostSell(), "پر فروش ها");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(2);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
