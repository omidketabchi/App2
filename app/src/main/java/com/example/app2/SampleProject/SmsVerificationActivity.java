package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.app2.SampleProject.CommandTypes.*;

public class SmsVerificationActivity extends AppCompatActivity {

    EditText edtTo;
    EditText edtMessage;
    Button btnSendSms;
    Button btnGetVerificationCode;
    String tokenKey = "";
    int randomNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_verification);

        setupViews();

        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToken(SEND_SMS);
            }
        });

        btnGetVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random r = new Random();
//                Math.random()* maximum + minimum
                randomNumber = (int) (Math.random() * 99999 + 11111);
                getToken(GET_VERIFICATION_CODE);
            }
        });

        Log.i("LOG", "tk: " + tokenKey);
    }

    private void setupViews() {
        edtTo = (EditText) findViewById(R.id.edt_smsVerification_to);
        edtMessage = (EditText) findViewById(R.id.edt_smsVerification_message);

        btnSendSms = (Button) findViewById(R.id.btn_smsVerification_sendSms);
        btnGetVerificationCode = (Button) findViewById(R.id.btn_smsVerification_getVerificationCode);
    }

    private void getToken(CommandTypes commandType) {

//        String body = "{UserApiKey:71eed9008ba6598d4808d900,SecretKey:it66)%#teBC!@*&}";
        SMSRequest body = new SMSRequest("71eed9008ba6598d4808d900", "it66)%#teBC!@*&");

        ApiServiceSms service = ApiClientSms.getClient().create(ApiServiceSms.class);

        Call<ResponseBody> call1 = service.getToken(body);

        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String result = response.body().string();
                    Log.i("LOG", "onResponse: " + result);
                    JSONObject jsonObject = new JSONObject(result);
                    tokenKey = jsonObject.getString("TokenKey");
                    Log.i("LOG", "tokenKey: " + tokenKey);
                    sendCommand(commandType, tokenKey);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("LOG", "onResponse: " + t.toString());
            }
        });
    }

    private void sendCommand(CommandTypes commandTypes, String tokenKey) {
        switch (commandTypes) {
            case SEND_SMS:
                sendSms(tokenKey, edtTo.getText().toString(), edtMessage.getText().toString());
                break;
            case GET_VERIFICATION_CODE:
                String msg = "کد ورود به برنامه:" + "\n" + String.valueOf(randomNumber);
                sendSms(tokenKey, "09037578860", msg);
                break;
            case GET_SEND_REPORT:
                break;
        }
    }

    private void sendSms(String tokenKey, String to, String message) {
        ApiServiceSms serviceSms = ApiClientSms.getClient().create(ApiServiceSms.class);

        SMSStructure smsStructure = new SMSStructure(
                new String[]{message},
                new String[]{to},
                "30004554554977",
                "",
                "false");

        Call<ResponseBody> call = serviceSms.sendSms(tokenKey, smsStructure);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                try {
                    result = response.body().string();
                    Log.i("LOG", "onResponse: " + result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("LOG", "onResponse: " + t.toString());
            }
        });
    }
}
