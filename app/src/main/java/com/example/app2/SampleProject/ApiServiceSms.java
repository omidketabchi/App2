package com.example.app2.SampleProject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServiceSms {


    @Headers("Content-Type: application/json")

    @POST("Token")
    Call<ResponseBody> getToken(@Body SMSRequest body);

    @POST("MessageSend")
    Call<ResponseBody> sendSms(
            @Header("x-sms-ir-secure-token") String header,
            @Body SMSStructure body);
}
