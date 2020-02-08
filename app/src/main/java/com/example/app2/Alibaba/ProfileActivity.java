package com.example.app2.Alibaba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.Alibaba.Adapter.AlibabaProfileAdapter;
import com.example.app2.Alibaba.Model.ProfileModel;
import com.example.app2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 686;
    RecyclerView recyclerView;
    ImageView imgBack;
    FloatingActionButton floatingActionButton;
    SharedPreferences sharedPreferences;
    String email = "";
    List<ProfileModel> profiles;
    AlibabaProfileAdapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupViews();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("email", email);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViews() {

        profiles = new ArrayList<>();

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        ProfileModel profile = new ProfileModel();
        ProfileModel defaultProfile = new ProfileModel();
        ProfileModel profile2 = new ProfileModel();
        ProfileModel profile3 = new ProfileModel();
        ProfileModel profile4 = new ProfileModel();
        ProfileModel profile5 = new ProfileModel();

        defaultProfile.setTitle("موجودی:");
        defaultProfile.setValue("0 ریال");
        profiles.add(defaultProfile);

        profile.setTitle("ایمیل:");
        profile.setValue(email);
        profiles.add(profile);

        String mobileNo = sharedPreferences.getString("mobileNo", "");
        String manOrWoman = sharedPreferences.getString("manOrWoman", "");
        String code = sharedPreferences.getString("code", "");
        String birthDate = sharedPreferences.getString("birthDate", "");

        profile2.setTitle("شماره همراه:");
        profile2.setValue(mobileNo);
        profiles.add(profile2);

        profile3.setTitle("جنسیت:");
        profile3.setValue(manOrWoman);
        profiles.add(profile3);

        profile4.setTitle("کد ملی:");
        profile4.setValue(code);
        profiles.add(profile4);

        profile5.setTitle("تاریخ تولد:");
        profile5.setValue(birthDate);
        profiles.add(profile5);

        imgBack = (ImageView)findViewById(R.id.img_alibaba_profole_back);
        profileAdapter = new AlibabaProfileAdapter(ProfileActivity.this, profiles);

        recyclerView = (RecyclerView) findViewById(R.id.rv_alibaba_profole_list);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_alibaba_profole_edit);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        email = sharedPreferences.getString("email", "");
        recyclerView.setAdapter(profileAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode == REQUEST_CODE && data != null && resultCode == RESULT_OK) {

            ProfileModel mobileNo = data.getParcelableExtra("mobileNo");
            ProfileModel manOrWoman = data.getParcelableExtra("manOrWoman");
            ProfileModel code = data.getParcelableExtra("code");
            ProfileModel birthDate = data.getParcelableExtra("birthDate");

            profiles.clear();

            ProfileModel profile = new ProfileModel();
            ProfileModel defaultProfile = new ProfileModel();


            defaultProfile.setTitle("موجودی:");
            defaultProfile.setValue("0 ریال");
            profiles.add(defaultProfile);

            profile.setTitle("ایمیل:");
            profile.setValue(email);
            profiles.add(profile);

            profiles.add(mobileNo);
            profiles.add(manOrWoman);
            profiles.add(code);
            profiles.add(birthDate);

            profileAdapter.notifyDataSetChanged();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("mobileNo", mobileNo.getValue());
            editor.putString("manOrWoman", manOrWoman.getValue());
            editor.putString("code", code.getValue());
            editor.putString("birthDate", birthDate.getValue());

            editor.apply();
        }
    }
}
