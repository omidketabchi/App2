package com.example.app2.Alibaba;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app2.Alibaba.Adapter.AlibabaCitiesAdapter;
import com.example.app2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {

    private static final String url = "https://api.myjson.com/bins/193ek2";
    ImageView imgBack;
    EditText edtSearch;
    RecyclerView recyclerView;
    List<String> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        setupViews();

        getAllCities();
    }

    private void setupViews() {

        cities = new ArrayList<>();

        imgBack = (ImageView) findViewById(R.id.img_cities_back);
        edtSearch = (EditText) findViewById(R.id.edt_cities_search);
        recyclerView = (RecyclerView) findViewById(R.id.rv_cities_city);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getAllCities() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String city = jsonObject.getString("city");
                        cities.add(city);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setAdapter(new AlibabaCitiesAdapter(cities, new AlibabaCitiesAdapter.OnCitySelected() {
                    @Override
                    public void onSelected(String city) {
//                        Toast.makeText(CitiesActivity.this, city, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("city", city);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonArrayRequest);
    }
}
