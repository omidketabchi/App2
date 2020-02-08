package com.example.app2.SampleProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    String id;
    String icon;
    //    String url = "http://httpbin.org/post";
    String url = "http://httpbin.org/uuid";
    TextView txtTitle;
    TextView txtDesc;
    ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupViews();

        getDetailInfo();
    }

    private void setupViews() {
        txtTitle = (TextView) findViewById(R.id.txt_detail_title);
        txtDesc = (TextView) findViewById(R.id.txt_detail_desc);

        imgIcon = (ImageView) findViewById(R.id.img_detail_icon);
    }

    private void getProductInfo() {
        id = getIntent().getExtras().getString("id");
        icon = getIntent().getExtras().getString("img");

        //Picasso.get().load(icon).into(imgIcon);
    }

    private void getDetailInfo() {

        // agar bekhahim yek string be server dadeh ya az aan yek string begirim az
        // StringRequest estefadeh mishavad.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String uuid = jsonObject.getString("uuid");
                            Spanned newUUID = Html.fromHtml(uuid);// baraye hazfe taghaye html az response-e server.
                            txtTitle.setText("UUID:");
                            txtDesc.setText(newUUID);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            Map<String, String> params = new HashMap<>();

            @Override
            public Map<String, String> getParams() {
                params.put("id2", id);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
