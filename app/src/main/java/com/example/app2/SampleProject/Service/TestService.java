package com.example.app2.SampleProject.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class TestService extends Service /*IntentService*/ {

    Timer timer = new Timer();
    int counter = 0;
    private String url = "https://newsapi.org/v2/everything?q=bitcoin&from=2019-11-21&sortBy=publishedAt&apiKey=ff10de54d66e474181463c4384865326";
    NotificationManager notificationManager;

//    /**
//     * Creates an IntentService.  Invoked by your subclass's constructor.
//     *
//     * @param name Used to name the worker thread, important only for debugging.
//     */
//    // this constructor is related to IntentService.
//    public TestService(String name) {
//        super(name);
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // this method is related to IntentService.
    // it is same as onStartCommand.
    // karhaye sangin marboot be service dar in method anjam mishavad.
//    @Override
//    protected void onHandleIntent(@Nullable Intent intent) {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                getDataFromServer();
//                createNotification();
//                stopSelf();
//                timer.cancel();
//                Log.i("TTT", "Notification is ready");
//            }
//        }, 5000, 1000);
    //   }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                getDataFromServer();
//                createBackgroundNotification();
//                stopSelf();
//                timer.cancel();
//                Log.i("TTT", "Notification is ready");
//            }
//        }, 5000, 1000);
//
//        return START_STICKY;
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                startForeground(1001, createForegroundNotification());
                getDataFromServer();
                stopSelf();// agar in line comment shavad, notification close nemishavad.
                timer.cancel();
                Log.i("TTT", "Notification is ready");
            }
        }, 5000, 1000);

        return START_STICKY;
    }

    private void getDataFromServer() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray articles = response.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject jsonObject = articles.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        Log.i("LOG", "title:" + title);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    private void createBackgroundNotification() {
        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this).
                        setSmallIcon(android.R.drawable.stat_notify_chat).
                        setContentTitle("update is ready").
                        setContentText("the last news exist for download");

        Notification notification = builder.build();
        notificationManager.notify(1001, notification);
    }

    private Notification createForegroundNotification() {
        notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this).
                        setSmallIcon(android.R.drawable.stat_notify_chat).
                        setContentTitle("update is ready").
                        setContentText("the last news exist for download");

        Notification notification = builder.build();
        notificationManager.notify(1001, notification);

        return notification;
    }
}
