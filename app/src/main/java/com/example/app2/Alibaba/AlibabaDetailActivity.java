package com.example.app2.Alibaba;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app2.Alibaba.Adapter.AlibabaBusTicketAdapter;
import com.example.app2.Alibaba.Adapter.AlibabaFlightTicketAdapter;
import com.example.app2.Alibaba.Adapter.AlibabaTrainTicketAdapter;
import com.example.app2.Alibaba.Model.AlibabaBusTicketModel;
import com.example.app2.Alibaba.Model.AlibabaChairModel;
import com.example.app2.Alibaba.Model.AlibabaFlightTicketModel;
import com.example.app2.Alibaba.Model.AlibabaTrainTicketModel;
import com.example.app2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlibabaDetailActivity extends AppCompatActivity {

    TextView txtSource;
    TextView txtDestination;
    TextView txtDate;
    RecyclerView recyclerView;
    ImageView imgBack;
    ImageView imgIcon;
    TextView txtOrder;
    TextView txtFilter;

    AlibabaBusTicketAdapter busTicketAdapter;

    List<AlibabaFlightTicketModel> flightModels;
    List<AlibabaTrainTicketModel> trainModels;
    List<AlibabaBusTicketModel> busModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alibaba_detail);

        setupViews();
        getMyIntent();
    }

    private void setupViews() {

        flightModels = new ArrayList<>();
        trainModels = new ArrayList<>();
        busModels = new ArrayList<>();

        imgBack = (ImageView) findViewById(R.id.img_alibabaDetail_back);
        imgIcon = (ImageView) findViewById(R.id.img_alibabaDetail_icon);
        txtSource = (TextView) findViewById(R.id.txt_alibabaDetail_source);
        txtDestination = (TextView) findViewById(R.id.txt_alibabaDetail_destination);
        txtDate = (TextView) findViewById(R.id.txt_alibabaDetail_date);
        txtOrder = (TextView) findViewById(R.id.txt_alibabaDetail_sort);
        txtFilter = (TextView) findViewById(R.id.txt_alibabaDetail_filter);
        recyclerView = (RecyclerView) findViewById(R.id.rv_alibabaDetail_tickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.widget.PopupMenu popupMenu = new PopupMenu(AlibabaDetailActivity.this, txtOrder);

//                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.inflate(R.menu.popup_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_popupMenu_default:
                                Collections.sort(busModels, new Comparator<AlibabaBusTicketModel>() {
                                    @Override
                                    public int compare(AlibabaBusTicketModel o1, AlibabaBusTicketModel o2) {
//                                        return Integer.parseInt(o1.getCapacity()) - Integer.parseInt(o2.getCapacity());
                                        return o1.getCapacity().compareTo(o2.getCapacity());
                                    }
                                });

                                break;
                            case R.id.action_popupMenu_hour:
                                Collections.sort(busModels, new Comparator<AlibabaBusTicketModel>() {
                                    @Override
                                    public int compare(AlibabaBusTicketModel o1, AlibabaBusTicketModel o2) {
                                        return o1.getTime().compareTo(o2.getTime());
                                    }
                                });
                                break;
                            case R.id.action_popupMenu_mostPrice:
                                Collections.sort(busModels, new Comparator<AlibabaBusTicketModel>() {
                                    @Override
                                    public int compare(AlibabaBusTicketModel o1, AlibabaBusTicketModel o2) {
                                        return o2.getPrice().compareTo(o1.getPrice());
                                    }
                                });
                                break;
                            case R.id.action_popupMenu_lessPrice:
                                Collections.sort(busModels, new Comparator<AlibabaBusTicketModel>() {
                                    @Override
                                    public int compare(AlibabaBusTicketModel o1, AlibabaBusTicketModel o2) {
                                        return o1.getPrice().compareTo(o2.getPrice());
                                    }
                                });
                                break;
                            default:
                                break;
                        }

                        busTicketAdapter.notifyDataSetChanged();
                        return true;
                    }
                });

                popupMenu.show();
            }
        });
    }

    private void getMyIntent() {

        String type = getIntent().getExtras().getString("type");
        String source = getIntent().getExtras().getString("source");
        String destination = getIntent().getExtras().getString("destination");
        String date = getIntent().getExtras().getString("date");

        txtSource.setText(source);
        txtDestination.setText(destination);
        txtDate.setText(date);

        if (type.equals("flight")) {
            imgIcon.setImageResource(R.drawable.ic_airplanemode_active_white_24dp);
            getAllFlightTicket(source, destination, date);
        } else if (type.equals("train")) {
            imgIcon.setImageResource(R.drawable.ic_train_white_24dp);
            getAllTrainTicket(source, destination, date);
        } else if (type.equals("bus")) {
            imgIcon.setImageResource(R.drawable.ic_directions_bus_white_24dp);
            getAllBusTicket(source, destination, date);
        }
    }

    private void getAllFlightTicket(String source, String destination, String date) {

        String url = "https://43aa1bff-3adc-4b2a-b908-617a7afb71b9.mock.pstmn.io/stuff/?" + "source=" + source + "&destination=" + destination + "&date=" + date;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    int kk = jsonArray.length();
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        AlibabaFlightTicketModel model = new AlibabaFlightTicketModel();

                        model.setId(jsonObject.getString("id"));
                        model.setSource(jsonObject.getString("source"));
                        model.setDestination(jsonObject.getString("destination"));
                        model.setSourceAirport(jsonObject.getString("source_airport"));
                        model.setDestinationAirport(jsonObject.getString("destination_airport"));
                        model.setDate(jsonObject.getString("date"));
                        model.setType(jsonObject.getString("type"));
                        model.setCompany(jsonObject.getString("company"));
                        model.setFlightTime(jsonObject.getString("flight_time"));
                        model.setLandTime(jsonObject.getString("land_time"));
                        model.setCapacity(jsonObject.getString("capacity"));
                        model.setFlightId(jsonObject.getString("flight_id"));
                        model.setPriceYoung(jsonObject.getString("price_young"));
                        model.setPriceChild(jsonObject.getString("price_child"));
                        model.setPriceBaby(jsonObject.getString("price_baby"));

                        String[] kinds = jsonObject.getString("kind").split("/");
                        model.setKind1(kinds[0]);
                        model.setKind2(kinds[1]);

                        flightModels.add(model);
                    }

                    recyclerView.setAdapter(new AlibabaFlightTicketAdapter(AlibabaDetailActivity.this, flightModels));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("LOG", "onResponse: " + e.getMessage().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onResponse: " + error.toString());
            }
        });
//        {
        // if send method has been GET, we should not send it like this, it is related to
        // sending parameters with POST.
//            Map<String, String> params = new HashMap<>();
//
//            @Override
//            public Map<String, String> getParams() {
//                params.put("source", source);
//                params.put("destination", destination);
//                params.put("date", date);
//                return params;
//            }
//        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void getAllTrainTicket(String source, String destination, String date) {

        String url = "https://1fc12221-ab17-4437-a934-a40d76961f01.mock.pstmn.io/?" + "source=" + source + "&destination=" + destination + "&date=" + date;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        AlibabaTrainTicketModel model = new AlibabaTrainTicketModel();

                        model.setId(jsonObject.getString("id"));
                        model.setTrainId(jsonObject.getString("train_id"));
                        model.setSource(jsonObject.getString("source"));
                        model.setDestination(jsonObject.getString("destination"));

                        model.setStartTime(jsonObject.getString("start_time"));
                        model.setEndTime(jsonObject.getString("end_time"));
                        model.setDate(jsonObject.getString("date"));
                        model.setType(jsonObject.getString("type"));
                        model.setCapacity(jsonObject.getString("capacity"));
                        model.setCoupeCapacity(jsonObject.getString("coupe_capacity"));
                        model.setPrice(jsonObject.getString("price"));

                        trainModels.add(model);
                    }

                    recyclerView.setAdapter(new AlibabaTrainTicketAdapter(AlibabaDetailActivity.this, trainModels));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("LOG", "onResponse: " + e.getMessage().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onResponse: " + error.toString());
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void getAllBusTicket(String source, String destination, String date) {

        String url = "https://ff6b20d9-f63c-4c39-9cdc-7e678c46fa48.mock.pstmn.io/?" + "source=" + source + "&destination=" + destination + "&date=" + date;
//        String url = "https://api.myjson.com/bins/m0y3u";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        AlibabaBusTicketModel model = new AlibabaBusTicketModel();

                        model.setId(jsonObject.getString("id"));
                        model.setTicketId(jsonObject.getString("ticket_id"));
                        model.setSource(jsonObject.getString("source"));
                        model.setDestination(jsonObject.getString("destination"));

                        model.setSourceTerminal(jsonObject.getString("sourceTerminal"));
                        model.setDestinationTerminal(jsonObject.getString("destinationTerminal"));
                        model.setDate(jsonObject.getString("date"));
                        model.setTime(jsonObject.getString("time"));
                        model.setType(jsonObject.getString("type"));
                        model.setDistance(jsonObject.getString("distance"));
                        model.setCapacity(jsonObject.getString("capacity"));
                        model.setPrice(jsonObject.getString("price"));

                        JSONArray chairsArray = jsonObject.getJSONArray("chairs");

                        List<AlibabaChairModel> chairModels = new ArrayList<>();

                        for (int j = 0; j < chairsArray.length(); j++) {

                            AlibabaChairModel chairModel = new AlibabaChairModel();
                            JSONObject chair = chairsArray.getJSONObject(j);
                            chairModel.setChair(chair.getString("chair"));
                            chairModel.setStatus(chair.getString("status"));

                            chairModels.add(chairModel);
                        }

                        model.setChairModels(chairModels);
                        busModels.add(model);
                    }

                    busTicketAdapter = new AlibabaBusTicketAdapter(AlibabaDetailActivity.this, busModels);

                    recyclerView.setAdapter(busTicketAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("LOG", "onResponse: " + e.getMessage().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onResponse: " + error.toString());
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
