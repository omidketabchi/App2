package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app2.R;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
    }

    private void setupViews() {
        edtUsername = (EditText) findViewById(R.id.edt_login_username);
        edtPassword = (EditText) findViewById(R.id.edt_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login_save);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CommentActivity.class);

                intent.putExtra("username", edtUsername.getText().toString());
                intent.putExtra("password", edtPassword.getText().toString());

                startActivity(intent);
            }
        });
    }
}
