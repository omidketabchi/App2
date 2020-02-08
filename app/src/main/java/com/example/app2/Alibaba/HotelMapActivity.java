package com.example.app2.Alibaba;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app2.Alibaba.FragmentAlibaba.FragmentShowHotelList;
import com.example.app2.Alibaba.Model.AlibabaHotelModel;
import com.example.app2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

import ir.parsijoo.map.android.CallBacks.OnMapClickListener;
import ir.parsijoo.map.android.Controls.ZoomLevel;
import ir.parsijoo.map.android.Viewer;

public class HotelMapActivity extends AppCompatActivity {

    Viewer viewer;
    List<AlibabaHotelModel> hotels;
    double latitude;
    double longitude;
    ImageView imgBack;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_map);

        String city = getIntent().getExtras().getString("destination");

        setupViews();

        getHotels(city);
    }

    private void setupViews() {

        hotels = new ArrayList<>();

        viewer = (Viewer) findViewById(R.id.map_hotelMap_map);
        imgBack = (ImageView) findViewById(R.id.img_hotelMap_back);
        fab = (FloatingActionButton) findViewById(R.id.fab_hotelMap_showList);

        viewer.setFirstLoadCallBack(new MapView.OnFirstLayoutListener() {
            @Override
            public void onFirstLayout(View v, int left, int top, int right, int bottom) {
//                viewer.setStartPosition(new GeoPoint(latitude, longitude), ZoomLevel.City_3);


            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putParcelableArrayList("hotels", (ArrayList<? extends Parcelable>) hotels);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                FragmentShowHotelList fragmentShowHotelList = new FragmentShowHotelList();
                fragmentShowHotelList.setArguments(bundle);
                fragmentTransaction.add(R.id.frm_hotelMap_fragmentParent, fragmentShowHotelList);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void getHotels(String city) {

//        String url = "http://c77f6401-2b5e-47b1-9275-952519b22495.mock.pstmn.io?city=" + city;
        String url = "https://api.myjson.com/bins/ow3zu";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {

                            try {

                                JSONObject jsonObject = response.getJSONObject(i);
                                AlibabaHotelModel hotel = new AlibabaHotelModel();

                                hotel.setId(jsonObject.getString("id"));
                                hotel.setName(jsonObject.getString("name"));
                                hotel.setCity(jsonObject.getString("city"));
                                hotel.setStar(jsonObject.getString("star"));
                                hotel.setBedCount(jsonObject.getString("bed_count"));
                                hotel.setImage(jsonObject.getString("image"));
                                hotel.setLat(jsonObject.getString("lat"));
                                hotel.setLang(jsonObject.getString("lang"));
                                hotel.setPrice(jsonObject.getString("price"));

                                hotels.add(hotel);

                                latitude = Double.parseDouble(hotel.getLat());
                                longitude = Double.parseDouble(hotel.getLang());
                                viewer.addMarker(new GeoPoint(latitude, longitude));
                                viewer.setOnMapClickListener(new OnMapClickListener() {
                                    @Override
                                    public void onMapClicked(GeoPoint geoPoint, Viewer viewer) {
                                        Toast.makeText(HotelMapActivity.this, geoPoint.getLatitude()
                                                + ":" +
                                                geoPoint.getLongitude(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onLongPress(GeoPoint geoPoint, Viewer viewer) {
                                        Toast.makeText(HotelMapActivity.this, geoPoint.getLatitude()
                                                + ":" +
                                                geoPoint.getLongitude(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

//                        viewer.setStartPosition(new GeoPoint(latitude, longitude), ZoomLevel.City_3);

                        viewer.animateToPosition(new GeoPoint(latitude, longitude));
                        viewer.setZoom(ZoomLevel.City_1);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
