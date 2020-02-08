package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app2.R;

public class LoginActivity2 extends AppCompatActivity {

    Button btnLogin;
    EditText edtEmail;
    EditText edtPassword;
    TextView txtLogin;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        setupViews();

        checkLogin();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", edtEmail.getText().toString());
                editor.apply();
            }
        });
    }

    private void setupViews() {

        btnLogin = (Button) findViewById(R.id.btn_shared_login);
        edtPassword = (EditText) findViewById(R.id.edt_shared_password);
        edtEmail = (EditText) findViewById(R.id.edt_shared_email);
        txtLogin = (TextView) findViewById(R.id.txt_shared_email);
    }

    private void checkLogin() {

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

        if (!email.equals("")) {

            txtLogin.setVisibility(View.VISIBLE);
            txtLogin.setText(email);

            edtPassword.setVisibility(View.GONE);
            edtEmail.setVisibility(View.GONE);
            btnLogin.setVisibility(View.GONE);
        }
    }
}
