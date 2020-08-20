package com.example.agilesynergy.classes;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class broadcastClass extends BroadcastReceiver {


    private boolean isConnected;

    public boolean isIsConnected() {
        return isConnected;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);//memory leakage without getApplicationContext

        if (wifi.isWifiEnabled()) {
            WifiInfo wifiInfo = wifi.getConnectionInfo();
            if (wifiInfo.getNetworkId() == -1) {
               isConnected= false;
            }
            String myName = wifiInfo.getSSID();
            Toast.makeText(context, myName, Toast.LENGTH_LONG).show();
            isConnected= true;
        }
        isConnected= false;
    }
}
