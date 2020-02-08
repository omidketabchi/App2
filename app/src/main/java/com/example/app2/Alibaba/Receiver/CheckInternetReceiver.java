package com.example.app2.Alibaba.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetReceiver extends BroadcastReceiver {

    private OnCheckConnection onCheckConnection;

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            onCheckConnection.onReceive();
        } else {
            onCheckConnection.onErrorReceive();
        }
    }

    public OnCheckConnection getOnCheckConnection() {
        return onCheckConnection;
    }

    public void setOnCheckConnection(OnCheckConnection onCheckConnection) {
        this.onCheckConnection = onCheckConnection;
    }

    public interface OnCheckConnection {

        void onErrorReceive();

        void onReceive();
    }
}
