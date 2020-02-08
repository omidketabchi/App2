package com.example.app2.Alibaba;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app2.Alibaba.Adapter.PenaltyAdapter;
import com.example.app2.Alibaba.Model.AlibabaFlightTicketModel;
import com.example.app2.Alibaba.Model.PenaltyModel;
import com.example.app2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlibabaInformationActivity extends AppCompatActivity {

    TextView txtDate;
    TextView txtTime;
    TextView txtSource;
    TextView txtDestination;
    TextView txtFlightId;
    TextView txtKind1;
    TextView txtKind2;
    TextView txtYoungPrice;
    TextView txtChildPrice;
    TextView txtBabyPrice;
    ImageView imgBack;
    RecyclerView recyclerView;
    String id = "";
    List<PenaltyModel> penaltyModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alibaba_information);

        setupViews();

        getTicket();

        getPenaltyFromServer(id);
    }

    private void getTicket() {

        AlibabaFlightTicketModel model = getIntent().getParcelableExtra("model");

        id = model.getId();

        txtDate.setText(model.getDate());
        txtTime.setText(model.getFlightTime());
        txtSource.setText(model.getSource() + "-" + model.getSourceAirport());
        txtDestination.setText(model.getDestination() + "-" + model.getDestinationAirport());
        txtFlightId.setText(model.getFlightId());
        txtKind1.setText(model.getKind1());
        txtKind2.setText(model.getKind2());
        txtYoungPrice.setText(model.getPriceYoung());
        txtChildPrice.setText(model.getPriceChild());
        txtBabyPrice.setText(model.getPriceBaby());
    }

    private void setupViews() {

        penaltyModels = new ArrayList<>();

        txtDate = (TextView) findViewById(R.id.txt_alibabaInformation_time);
        txtTime = (TextView) findViewById(R.id.txt_alibabaInformation_flightTime);
        txtSource = (TextView) findViewById(R.id.txt_alibabaInformation_source);
        txtDestination = (TextView) findViewById(R.id.txt_alibabaInformation_destination);
        txtFlightId = (TextView) findViewById(R.id.txt_alibabaInformation_flightId);
        txtKind1 = (TextView) findViewById(R.id.txt_alibabaInformation_kind);
        txtKind2 = (TextView) findViewById(R.id.txt_alibabaInformation_class);
        txtYoungPrice = (TextView) findViewById(R.id.txt_alibabaInformation_youngPrice);
        txtChildPrice = (TextView) findViewById(R.id.txt_alibabaInformation_childPrice);
        txtBabyPrice = (TextView) findViewById(R.id.txt_alibabaInformation_babyPrice);
        imgBack = (ImageView) findViewById(R.id.img_alibabaInformation_back);
        recyclerView = (RecyclerView) findViewById(R.id.rv_alibabaInformation_penalty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getPenaltyFromServer(String id) {

//        String url = "https://5b39ff39-4657-4aed-bc65-d6aca197963b.mock.pstmn.io/?" + "id=" + id;
        String url = "https://api.myjson.com/bins/d9gp6";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            PenaltyModel penaltyModel = new PenaltyModel();
                            penaltyModel.setRuleTitle(jsonObject.getString("rule_title"));
                            penaltyModel.setPenaltyPercentage(jsonObject.getString("penalty_percentage"));

                            penaltyModels.add(penaltyModel);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setAdapter(new PenaltyAdapter(AlibabaInformationActivity.this, penaltyModels));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AlibabaInformationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

}
