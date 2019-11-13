package com.lottery.app.helperPackages.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class InternetConnectivity {

    private static final String LOG_TAG = InternetConnectivity.class.getSimpleName();

    public static boolean isOnline(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        }
        catch (Exception exception) {
            Log.e(LOG_TAG, exception.toString());
        }
        return false;
    }
}
