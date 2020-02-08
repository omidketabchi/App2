package com.example.app2.Alibaba.FragmentAlibaba;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app2.R;

import java.util.HashMap;
import java.util.Map;

public class FragmentDialogLogin extends DialogFragment {

    EditText edtEmail;
    EditText edtPass;
    Button btnSignup;
    Button btnLogin;
    View view;
    String email = "";
    String password = "";
    OnSignupClicked onSignupClicked;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        view = LayoutInflater.from(getContext()).inflate(R.layout.login_dialog, null);

        setupViews();

        builder.setView(view);

        return builder.create();
    }

    private void setupViews() {

        btnLogin = (Button) view.findViewById(R.id.btn_alibabaLoginDialog_login);
        btnSignup = (Button) view.findViewById(R.id.btn_alibabaLoginDialog_signup);
        edtEmail = (EditText) view.findViewById(R.id.edt_alibabaLoginDialog_email);
        edtPass = (EditText) view.findViewById(R.id.edt_alibabaLoginDialog_pass);

        email = edtEmail.getText().toString();
        password = edtPass.getText().toString();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignup(email, password);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email, password);
            }
        });
    }

    private void login(String email, String password) {

        String url = "https://7e69edce-af20-4c49-9ad9-5697df4e8a20.mock.pstmn.io";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (!response.equals("not found")) {
                    onSignupClicked.onClicked(response);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onResponse12: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void setOnSignupClicked(OnSignupClicked onSignupClicked) {
        this.onSignupClicked = onSignupClicked;
    }

    private void userSignup(String email, String password) {

        // send email & pass to the server.
        String url = "https://7e69edce-af20-4c49-9ad9-5697df4e8a20.mock.pstmn.io";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.i("LOG", "onResponse: " + response);
                onSignupClicked.onClicked(response);
                dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("LOG", "onErrorResponse: " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("username", email);
                params.put("password", password);
                return params;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    public interface OnSignupClicked {
        void onClicked(String response);
    }
}
