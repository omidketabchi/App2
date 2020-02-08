package com.example.app2.SampleProject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class CallBackClass {

    private String url = "https://api.myjson.com/bins/o8j2g";
    private Context context;
    private OnResponseReceive onResponseReceive;

    // we want to, when we get a response or error, the result passed to CallBackActivity.
    public CallBackClass(Context context) {
        this.context = context;
    }

    public void getList(OnResponseReceive onResponseReceive) {

        this.onResponseReceive = onResponseReceive;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                onResponseReceive.onReceive(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResponseReceive.onError(error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }

    public interface OnResponseReceive {

        void onReceive(String response);

        void onError(String error);
    }
}
