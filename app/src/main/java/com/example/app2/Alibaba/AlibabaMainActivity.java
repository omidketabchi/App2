package com.example.app2.Alibaba;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.app2.Alibaba.Adapter.AlibabaViewPagerAdapter;
import com.example.app2.Alibaba.Adapter.NavigationAdapter;
import com.example.app2.Alibaba.FragmentAlibaba.FragmentBus;
import com.example.app2.Alibaba.FragmentAlibaba.FragmentHotel;
import com.example.app2.Alibaba.FragmentAlibaba.FragmentInsideFight;
import com.example.app2.Alibaba.FragmentAlibaba.FragmentOutsideFight;
import com.example.app2.Alibaba.FragmentAlibaba.FragmentSplash;
import com.example.app2.Alibaba.FragmentAlibaba.FragmentTrain;
import com.example.app2.Alibaba.Model.NavigationModel;
import com.example.app2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class AlibabaMainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView txtIntro;
    ImageView imgHamberMenu;
    TabLayout tabLayout;
    ViewPager viewPager;
    RecyclerView recyclerViewMenu;
    TextView txtNavigationEmail;
    List<NavigationModel> models;
    Timer timer;
    FragmentSplash fragmentSplash;

    AppBarConfiguration appBarConfiguration;
    NavigationAdapter navigationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alibaba_main);

        setupViews();

        //showSplashFragment();

        setNavigationView();
    }


    private void setupViews() {

        models = new ArrayList<>();

        tabLayout = (TabLayout) findViewById(R.id.tab_alibabaContent_tab);
        viewPager = (ViewPager) findViewById(R.id.vp_alibabaContent_viewPager);
        toolbar = (Toolbar) findViewById(R.id.tb_alibabaMain_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_alibabaMain_parent);
        navigationView = (NavigationView) findViewById(R.id.nw_alibabaMain_navigationView);
        txtIntro = (TextView) findViewById(R.id.txt_alibabaMain_into);
        imgHamberMenu = (ImageView) findViewById(R.id.img_alibabaMain_hamberMenu);
        recyclerViewMenu = (RecyclerView) findViewById(R.id.rv_alibabaMain_navigationView);
        txtNavigationEmail = (TextView) findViewById(R.id.txt_alibabaMain_email);

        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));

        AlibabaViewPagerAdapter adapter = new AlibabaViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentHotel(), "هتل");
        adapter.addFragment(new FragmentBus(), "اتوبوس");
        adapter.addFragment(new FragmentTrain(), "قطار");
        adapter.addFragment(new FragmentOutsideFight(), "پرواز خارچی");
        adapter.addFragment(new FragmentInsideFight(), "پرواز داخلی");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(4);
        tabLayout.setupWithViewPager(viewPager);

        imgHamberMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
    }

    private void setNavigationView() {

        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String key = sharedPreferences.getString("email", "");

        txtNavigationEmail.setText(key);

        NavigationModel model1 = new NavigationModel();
        model1.setTitle("پروفایل کاربری");
        model1.setDrawable(R.drawable.ic_person_black_24dp);
        models.add(model1);

        NavigationModel model2 = new NavigationModel();
        model2.setTitle("لیست مسافران");
        model2.setDrawable(R.drawable.ic_passengers_black_24dp);
        models.add(model2);

        NavigationModel model3 = new NavigationModel();
        model3.setTitle("سوابق تراکنش");
        model3.setDrawable(R.drawable.ic_money_black_24dp);
        models.add(model3);

        NavigationModel model4 = new NavigationModel();
        model4.setTitle("خریدهای من");
        model4.setDrawable(R.drawable.ic_shopping_cart_black_24dp);
        models.add(model4);

        NavigationModel model5 = new NavigationModel();
        model5.setTitle("خروج از حساب کاربری");
        model5.setDrawable(R.drawable.ic_exit_to_app_black_24dp);
        models.add(model5);

        navigationAdapter = new NavigationAdapter(this, models);
        recyclerViewMenu.setAdapter(navigationAdapter);

        navigationAdapter.setOnDialogDismissed(new NavigationAdapter.OnDialogDismissed() {
            @Override
            public void onDismissed(String response) {

                String email = "";
                String password = "";
                SharedPreferences.Editor editor = sharedPreferences.edit();

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        email = jsonObject.getString("email");
                        password = jsonObject.getString("password");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                editor.putString("email", email);
                editor.apply();

                drawerLayout.closeDrawer(Gravity.RIGHT);
                txtNavigationEmail.setText(email + ":" + password);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

        }

        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }

    private void setTitleBar(String title) {
        txtIntro.setText(title);
    }

    private void showSplashFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        fragmentSplash = new FragmentSplash();
        transaction.replace(R.id.dl_alibabaMain_parent, fragmentSplash);
        transaction.commit();
    }
}
