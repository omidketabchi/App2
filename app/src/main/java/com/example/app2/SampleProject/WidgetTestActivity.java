package com.example.app2.SampleProject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app2.R;
import com.example.app2.SampleProject.WidgetActivities.BottomAppBarActivity;
import com.example.app2.SampleProject.WidgetActivities.BottomNavigationViewActivity;
import com.example.app2.SampleProject.WidgetActivities.BottomSheetViewActivity;
import com.example.app2.SampleProject.WidgetActivities.ScrollingActivity;
import com.example.app2.SampleProject.WidgetActivities.TestEditTextActivity;
import com.example.app2.SampleProject.WidgetActivities.TestRadioButtonActivity;
import com.example.app2.SampleProject.WidgetActivities.TextInputLayoutActivity;

public class WidgetTestActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTestEditText;
    Button btnTestRadioButton;
    Button btnTextInputLayout;
    Button btnBottomAppBar;
    Button btnBottomNavigationView;
    Button btnBottomSheetView;
    Button btncollapsingToolBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_test);

        setupViews();

    }

    private void setupViews() {

        btnTestEditText = (Button) findViewById(R.id.btn_widgetTest_editText);
        btnTestRadioButton = (Button) findViewById(R.id.btn_widgetTest_radioButton);
        btnTextInputLayout = (Button) findViewById(R.id.btn_widgetTest_textInputLayout);
        btnBottomAppBar = (Button) findViewById(R.id.btn_widgetTest_bottomAppBar);
        btnBottomNavigationView = (Button) findViewById(R.id.btn_widgetTest_bottomAppBar);
        btnBottomNavigationView = (Button) findViewById(R.id.btn_widgetTest_bottomNavigationView);
        btnBottomSheetView = (Button) findViewById(R.id.btn_widgetTest_bottomSheetView);
        btncollapsingToolBarLayout = (Button) findViewById(R.id.btn_widgetTest_collapsingToolBarLayout);

        btnTestEditText.setOnClickListener(this);
        btnTestRadioButton.setOnClickListener(this);
        btnTextInputLayout.setOnClickListener(this);
        btnBottomAppBar.setOnClickListener(this);
        btnBottomNavigationView.setOnClickListener(this);
        btnBottomSheetView.setOnClickListener(this);
        btncollapsingToolBarLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_widgetTest_editText:
                startActivity(new Intent(WidgetTestActivity.this, TestEditTextActivity.class));
                break;
            case R.id.btn_widgetTest_radioButton:
                startActivity(new Intent(WidgetTestActivity.this, TestRadioButtonActivity.class));
                break;
            case R.id.btn_widgetTest_textInputLayout:
                startActivity(new Intent(WidgetTestActivity.this, TextInputLayoutActivity.class));
                break;
            case R.id.btn_widgetTest_bottomAppBar:
                startActivity(new Intent(WidgetTestActivity.this, BottomAppBarActivity.class));
                break;
            case R.id.btn_widgetTest_bottomNavigationView:
                startActivity(new Intent(WidgetTestActivity.this, BottomNavigationViewActivity.class));
                break;
            case R.id.btn_widgetTest_bottomSheetView:
                startActivity(new Intent(WidgetTestActivity.this, BottomSheetViewActivity.class));
                break;
            case R.id.btn_widgetTest_collapsingToolBarLayout:
                startActivity(new Intent(WidgetTestActivity.this, ScrollingActivity.class));
                break;
        }
    }
}
