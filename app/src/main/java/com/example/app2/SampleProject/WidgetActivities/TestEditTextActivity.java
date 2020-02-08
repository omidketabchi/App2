package com.example.app2.SampleProject.WidgetActivities;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app2.R;

public class TestEditTextActivity extends AppCompatActivity {

    EditText edtMessage;
    ImageView imgEye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_edit_test);

        setupViews();

        imgEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtMessage.getTransformationMethod() instanceof PasswordTransformationMethod) {
                    imgEye.setImageResource(R.drawable.ic_eye);
                    edtMessage.setTransformationMethod(new SingleLineTransformationMethod());
                } else {
                    edtMessage.setTransformationMethod(new PasswordTransformationMethod());
                    imgEye.setImageResource(R.drawable.ic_eye_hidden);
                }

                edtMessage.setSelection(edtMessage.getText().length());
            }
        });
    }

    private void setupViews() {

        edtMessage = (EditText) findViewById(R.id.edt_widgetTest_message);
        imgEye = (ImageView) findViewById(R.id.img_widgetTest_eye);
    }
}
