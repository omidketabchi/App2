package com.example.app2.SampleProject.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app2.R;

public class ToolbarActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    ImageView imgAddToBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        String title = getIntent().getExtras().getString("title");

        toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_toolbar);
        imgAddToBasket = (ImageView) findViewById(R.id.img_toolbar_addTobasket);

        toolbar.setTitle(title);
        customToolbar();
        setSupportActionBar(toolbar);// baes mishe ... rooye menu biyad.

        imgAddToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolbarActivity.this, "add to basket pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu, menu);// menu is output.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_setting:
                Toast.makeText(this, "تنظیمات", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_chart:
                Toast.makeText(this, "نمودار", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_description:
                Toast.makeText(this, "گزارش", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void customToolbar() {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);

            if (view instanceof TextView) {
                TextView txt = (TextView) view;
                txt.setTextSize(12);
            }

            if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ToolbarActivity.this, "shopping cart", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
