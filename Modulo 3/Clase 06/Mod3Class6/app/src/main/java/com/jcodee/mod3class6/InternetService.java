package com.jcodee.mod3class6;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by johannfjs on 11/08/17.
 * Email: johann.jara@jcodee.com
 * Phone: (+51) 990 870 011
 */

public class InternetService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            if (networkInfo.isConnected() &&
                    networkInfo.isAvailable()) {
                Toast.makeText(this, "Hay internet", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No hay internet", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No hay internet", Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Se cerro el servicio", Toast.LENGTH_SHORT).show();
    }
}
