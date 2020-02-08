package com.example.app2.Alibaba;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.example.app2.Alibaba.Model.ProfileModel;
import com.example.app2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ImageView imgBack;
    EditText edtName;
    EditText edtFamily;
    EditText edtCode;
    EditText edtMobile;
    TextView txtBirthDate;
    Button btnSave;
    AppCompatSpinner spinner;
    String email = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setupViews();

        email = getIntent().getExtras().getString("email");
    }

    private void setupViews() {
        
        imgBack = (ImageView) findViewById(R.id.img_alibabaEditProfile_back);
        edtName = (EditText) findViewById(R.id.edt_alibabaEditProfile_name);
        edtFamily = (EditText) findViewById(R.id.edt_alibabaEditProfile_family);
        edtCode = (EditText) findViewById(R.id.edt_alibabaEditProfile_code);
        edtMobile = (EditText) findViewById(R.id.edt_alibabaEditProfile_mobile);
        txtBirthDate = (TextView) findViewById(R.id.txt_alibabaEditProfile_birthday);
        btnSave = (Button) findViewById(R.id.btn_alibabaEditProfile_save);
        spinner = (AppCompatSpinner) findViewById(R.id.sp_alibabaEditProfile_spinner);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveProfileData(email, edtName.getText().toString(), edtFamily.getText().toString(),
                        txtBirthDate.getText().toString(), edtCode.getText().toString(),
                        edtMobile.getText().toString(), spinner.getSelectedItem().toString());
            }
        });

        txtBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                DatePickerDialog dialog = new DatePickerDialog(EditProfileActivity.this, EditProfileActivity.this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dialog.show();
            }
        });

        ArrayList<String> lists = new ArrayList<>();
        lists.add("مرد");
        lists.add("زن");
        ArrayAdapter arrayAdapter = new ArrayAdapter(EditProfileActivity.this, android.R.layout.simple_spinner_item, lists);
        spinner.setAdapter(arrayAdapter);
    }

    private void saveProfileData(String email, String name, String family,
                                 String birthDate, String code, String mobileNo,
                                 String manOrWoman) {

//        String url = "";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                if (response.equals("NOT OK")) {
//                    Toast.makeText(EditProfileActivity.this, "مشکل در ثبت اطلاعات کاربری", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent();
//
//                    ProfileModel mobile = new ProfileModel();
//                    mobile.setTitle("شماره همراه:");
//                    mobile.setValue(mobileNo);
//                    intent.putExtra("mobileNo", mobile);
//
//                    ProfileModel mw = new ProfileModel();
//                    mw.setTitle("جنسیت:");
//                    mw.setValue(manOrWoman);
//                    intent.putExtra("manOrWoman", mw);
//
//                    ProfileModel cd = new ProfileModel();
//                    mobile.setTitle("کد ملی:");
//                    mobile.setValue(code);
//                    intent.putExtra("code", code);
//
//                    ProfileModel brDate = new ProfileModel();
//                    mw.setTitle("تاریخ تولد:");
//                    mw.setValue(birthDate);
//                    intent.putExtra("birthDate", brDate);
//
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//
//                params.put("email", email);
//                params.put("name", name);
//                params.put("family", family);
//                params.put("birthDate", birthDate);
//                params.put("code", code);
//                params.put("mobileNo", mobileNo);
//                params.put("manOrWoman", manOrWoman);
//
//                return params;
//            }
//        };
//
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        RequestQueue requestQueue = Volley.newRequestQueue(EditProfileActivity.this);
//        requestQueue.add(stringRequest);


        Intent intent = new Intent();

        ProfileModel mobile = new ProfileModel();
        mobile.setTitle("شماره همراه:");
        mobile.setValue(mobileNo);
        intent.putExtra("mobileNo", mobile);

        ProfileModel mw = new ProfileModel();
        mw.setTitle("جنسیت:");
        mw.setValue(manOrWoman);
        intent.putExtra("manOrWoman", mw);

        ProfileModel cd = new ProfileModel();
        cd.setTitle("کد ملی:");
        cd.setValue(code);
        intent.putExtra("code", cd);

        ProfileModel brDate = new ProfileModel();
        brDate.setTitle("تاریخ تولد:");
        brDate.setValue(birthDate);
        intent.putExtra("birthDate", brDate);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        txtBirthDate.setText(String.format("%04d", year) + "/" +
                String.format("%02d", month + 1) + "/" +
                String.format("%02d", dayOfMonth));
    }
}
