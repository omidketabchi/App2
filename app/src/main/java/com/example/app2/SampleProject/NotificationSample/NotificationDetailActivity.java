package com.example.app2.SampleProject.NotificationSample;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.app2.R;

public class NotificationDetailActivity extends AppCompatActivity {

    public static final String CITY_ID = "city_id";
    public static final int INVALID_VALUE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        final TextView textView = findViewById(R.id.description_textView);
        final TextView source = findViewById(R.id.source_textView);

        setupToolbar(toolbar);

        final long cityId = getIntent().getLongExtra(CITY_ID, INVALID_VALUE);
        if (cityId != INVALID_VALUE) {
            final City city = DummyData.getCityById(cityId);
            if (city != null) {
                textView.setText(city.getDescription());
                Glide.with(this)
                        .load(city.getImageURL())
                        .into(new SimpleTarget<Drawable>() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                toolbar.setBackground(resource);
                            }
                        });
            }
        }

        source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityId != INVALID_VALUE) {
                    final City city = DummyData.getCityById(cityId);
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(city.getSource()));
                    startActivity(myIntent);
                }
            }
        });
    }

    private void setupToolbar(@Nullable final Toolbar toolbar) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
