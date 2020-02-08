package com.example.app2.CafeBazar.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // add "/" at the end of BASE_URL address.
    private static final String BASE_URL = "https://api.myjson.com/";
    //    private static final String BASE_URL = "https://api.github.com/";
    public static Retrofit retrofit = null;


    private static final String BASE_URL_TEST = "https://postman-echo.com/";
    public static Retrofit retrofit1 = null;

    private static final String BASE_URL_SMS = "https://postman-echo.com/";
    public static Retrofit retrofit2 = null;

    private static final String BASE_URL_VALIDATION = "https://postman-echo.com/";
    public static Retrofit retrofit3 = null;

    public static Retrofit getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getClient1() {

        if (retrofit1 == null) {

            retrofit1 = new Retrofit.Builder()
                    .baseUrl(BASE_URL_TEST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit1;
    }

    public static Retrofit getClient2() {

        if (retrofit2 == null) {

            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL_SMS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit2;
    }

    public static Retrofit getClient3() {

        if (retrofit3 == null) {

            retrofit3 = new Retrofit.Builder()
                    .baseUrl(BASE_URL_VALIDATION)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit3;
    }
}
