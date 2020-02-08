package com.example.app2.SampleProject.Server;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app2.SampleProject.Adapter.ProductAdapter;
import com.example.app2.SampleProject.Model.Product;
import com.example.app2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ValyActivity extends AppCompatActivity {

    TextView txtToolbarTitle;
    //agar permission Internet dadeh nashavd, ertebat ba server kar nemikonad.
//    String url = "https://1drv.ms/u/s!Ah473rMW8uke9xPTmwYOaEBcEc2V?e=tJcmCd";
    String url = "https://api.myjson.com/bins/gkudk";

    List<Product> products;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_valy);

        setupViews();

        setToolbarTitle();

        getProductList();
    }

    private void getProductList() {
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    //JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Product product = new Product();

                        product.setId(jsonObject.getString("id"));
                        product.setTitle(jsonObject.getString("title"));
                        product.setPrice(jsonObject.getString("price"));
                        product.setPprice(jsonObject.getString("pprice"));
                        product.setPic(jsonObject.getString("pic"));

                        products.add(product);
                    }

                    recyclerView.setAdapter(new ProductAdapter(ValyActivity.this, products));

                } catch (JSONException e) {
                    e.getStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ValyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // ba in khotoot darkhast anjam mishavad.
        request.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    private void setupViews() {
        products = new ArrayList<>();

        txtToolbarTitle = (TextView) findViewById(R.id.txt_myToolbar_toolbarTitle);
        recyclerView = (RecyclerView) findViewById(R.id.rcv_valy_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(ValyActivity.this));
    }

    private void setToolbarTitle() {

        String title = getIntent().getExtras().getString("title");

        txtToolbarTitle.setText(title);
    }
}
