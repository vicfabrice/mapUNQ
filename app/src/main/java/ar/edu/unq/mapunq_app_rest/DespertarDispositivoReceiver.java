package ar.edu.unq.mapunq_app_rest;

import android.content.BroadcastReceiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by zacks on 3/2/2018.
 */

public class DespertarDispositivoReceiver extends BroadcastReceiver {
    private static PowerManager.WakeLock wakeLock;

    private static final String TAG = "AlarmReceiverLife";
    static Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "Recurring alarm");

        // get data, respeta lo especificado en https://www.internalpositioning.com/doc/api.md
        String familyName = "unq";
        String deviceName = intent.getStringExtra("deviceName");
        String locationName = intent.getStringExtra("locationName");
        String serverAddress = "https://cloud.internalpositioning.com";
        boolean allowGPS = intent.getBooleanExtra("allowGPS",false);
        Log.d(TAG,"familyName: "+ familyName);

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "WakeLock");
        wakeLock.acquire();
        Intent scanService = new Intent(context, ScanService.class);
        scanService.putExtra("familyName",familyName);
        scanService.putExtra("deviceName",deviceName);
        scanService.putExtra("locationName",locationName);
        scanService.putExtra("serverAddress",serverAddress);
        scanService.putExtra("allowGPS",allowGPS);
        //scanService.getExtras().locData;
        try {
            context.startService(scanService);
        } catch (Exception e) {
            Log.w(TAG,e.toString());
        }
        Log.d(TAG,"Releasing wakelock");
        if (wakeLock != null) wakeLock.release();
        wakeLock = null;
    }


}
