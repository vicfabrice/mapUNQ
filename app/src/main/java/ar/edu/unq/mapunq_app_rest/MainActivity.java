package ar.edu.unq.mapunq_app_rest;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.stream.Collectors;

import model.Analysis;
import model.Guess;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;



public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    String usernameFile = "mapunqID"; //cambiar nombre
    String user;

    //background manager para usar scanService y despertarDispositivo
    //RemindTask está copiada de find3 find my phone
    private PendingIntent recurringLl24 = null;
    private Intent ll24 = null;
    AlarmManager alarms = null;
    WebSocketClient mWebSocketClient = null;
    Timer timer = null;
    private RemindTask oneSecondTimer = null;

    //Crea un thread donde corre la conexión webSocketClient
    class RemindTask extends TimerTask {
        private Integer counter = 0;

        String url ="http://www.google.com";

        public void resetCounter() {
            counter = 0;
        }
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    counter++;
                    if (mWebSocketClient != null) {
                        if (mWebSocketClient.isClosed()) {
                            connectWebSocket();
                        }
                    }
                }
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Chequea los permisos disponibles
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WAKE_LOCK, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.ACCESS_WIFI_STATE}, 1);
        }

        //Obtiene un GUID
        String uniqueID = UUID.randomUUID().toString();
        Log.d("uniqueID", uniqueID);
        FileOutputStream fos = null;

        //Escribe un archivo en la memoria interna de nombre usernameFile
        try {
            fos = openFileOutput(usernameFile, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(uniqueID.getBytes());
            user = uniqueID;

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        //Lee un usuario de memoria interna
        
        try {
            String us;
            FileInputStream fis = null;
            String filePath = getApplicationContext().getFilesDir().getPath() + "/mapunqID";
            Log.e("filePath", filePath);
            fis = new FileInputStream(filePath);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(fis));
            us = bfr.lines().collect(Collectors.joining());
            Log.e("usID", us);
            //String message = org.apache.commons.io.IOUtils.toString(bfr);
            user = us;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("mapunqID", user);

        // falta hacer el close del fr

        final String BASE_URL = "https://cloud.internalpositioning.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UbicacionHTTPClient service = retrofit.create(UbicacionHTTPClient.class);

        Call<Analysis> call;
        call = service.getAnalysis(user);
        call.enqueue(new Callback<Analysis>() {

            @Override
            public void onResponse(Response<Analysis> response, Retrofit retrofit) {
                final Analysis analysis = response.body();
                //Ubicacion ubicacion = response.body();
                try {
                    ArrayList<Guess> data = new ArrayList<>(analysis.getAnalysis().getGuesses());

                    //analysis.setGuesses(data);

                    TextView ubicacionIdText = (TextView) findViewById(R.id.labelUb);
                    final TextView ubicacionContentText = (TextView) findViewById(R.id.lblId);
                    ubicacionContentText.setText(analysis.getAnalysis().mostProbablyLocation());
                    TextView infoText = (TextView) findViewById(R.id.labelInfo);
                    TextView contentInfoText = (TextView) findViewById(R.id.contentOfMara);
                    TextView ubicacionCercanaIdText = (TextView) findViewById(R.id.lblCerca);
                    ubicacionCercanaIdText.setText(analysis.getAnalysis().almostProbablyLocation());
                }catch (Exception e){
                    Log.e("analysis onresponse", e.getMessage());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("HelloWorld", t.getMessage());
                Toast.makeText(MainActivity.this, "Ha ocurrido un error al llamar al servicio", Toast.LENGTH_LONG).show();
            }

        });


        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    TextView ubicacionContentText = (TextView) findViewById(R.id.lblId);
                    boolean allowGPS = ((CheckBox) findViewById(R.id.allowGPS)).isChecked();
                    Log.d(TAG, "allowGPS is checked: " + allowGPS);
                    //ubicacionContentText.setText(analysis.getAnalysis().mostProbablyLocation());


                    // 24/7 alarm
                    ll24 = new Intent(MainActivity.this, DespertarDispositivoReceiver.class);
                    Log.d(TAG, "setting familyName to unq");
                    ll24.putExtra("deviceName", user);
                    ll24.putExtra("allowGPS", allowGPS);

                    recurringLl24 = PendingIntent.getBroadcast(MainActivity.this, 0, ll24, PendingIntent.FLAG_CANCEL_CURRENT);
                    alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarms.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.currentThreadTimeMillis(), 60000, recurringLl24);
                    timer = new Timer();
                    oneSecondTimer = new RemindTask();
                    timer.scheduleAtFixedRate(oneSecondTimer, 1000, 1000);
                    connectWebSocket();

                    String scanningMessage = "buscando ubicación de " + "";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                            .setSmallIcon(R.drawable.ic_stat_name)
                            .setContentTitle(scanningMessage)
                            .setContentIntent(recurringLl24);
                    //specifying an action and its category to be triggered once clicked on the notification
                    Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
                    resultIntent.setAction("android.intent.action.MAIN");
                    resultIntent.addCategory("android.intent.category.LAUNCHER");
                    PendingIntent resultPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    notificationBuilder.setContentIntent(resultPendingIntent);

                    android.app.NotificationManager notificationManager =
                            (android.app.NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

                } else {
                    //TextView ubicacionContentText = (TextView) findViewById(R.id.lblId);
                    //ubicacionContentText.setText(analysis.getAnalysis().mostProbablyLocation());
                    Log.d(TAG, "toggle set to false");
                    alarms.cancel(recurringLl24);
                    android.app.NotificationManager mNotificationManager = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.cancel(0);
                    timer.cancel();
                }
            }
            });
    }
    private void connectWebSocket(){
        URI uri;
        try {
            String serverAddress = "https://cloud.internalpositioning.com";
            String familyName = "unq";
            String deviceName = user;
            serverAddress = serverAddress.replace("http", "ws");
            uri = new URI(serverAddress + "/ws?family=" + familyName + "&device=" + user);
            //uri = new URI("https://cloud.internalpositioning.com/unq/" + "&device=" + user);
            Log.d("Websocket", "connect to websocket at " + uri.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                mWebSocketClient.send("Hello");
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Websocket", "message: " + message);
                        JSONObject json = null;
                        JSONObject fingerprint = null;
                        JSONObject sensors = null;
                        JSONObject bluetooth = null;
                        JSONObject wifi = null;
                        String deviceName = "";
                        String locationName = "";
                        String familyName = "";
                        try {
                            json = new JSONObject(message);
                        } catch (Exception e) {
                            Log.d("Websocket", "json error: " + e.toString());
                            return;
                        }
                        try {
                            fingerprint = new JSONObject(json.get("sensors").toString());
                            Log.d("Websocket", "fingerprint: " + fingerprint);
                        } catch (Exception e) {
                            Log.d("Websocket", "json error: " + e.toString());
                        }
                        try {
                            sensors = new JSONObject(fingerprint.get("s").toString());
                            deviceName = fingerprint.get("d").toString();
                            familyName = fingerprint.get("f").toString();
                            locationName = fingerprint.get("l").toString();
                            Log.d("Websocket", "sensors: " + sensors);
                        } catch (Exception e) {
                            Log.d("Websocket", "json error: " + e.toString());
                        }
                        try {
                            wifi = new JSONObject(sensors.get("wifi").toString());
                            Log.d("Websocket", "wifi: " + wifi);
                        } catch (Exception e) {
                            Log.d("Websocket", "json error: " + e.toString());
                        }
                        try {
                            bluetooth = new JSONObject(sensors.get("bluetooth").toString());
                            Log.d("Websocket", "bluetooth: " + bluetooth);
                        } catch (Exception e) {
                            Log.d("Websocket", "json error: " + e.toString());
                        }
                        Log.d("Websocket", bluetooth.toString());
                        Integer bluetoothPoints = bluetooth.length();
                        Integer wifiPoints = wifi.length();
                        //String locData =
                        Long secondsAgo = null;
                        try {
                            secondsAgo = fingerprint.getLong("t");
                        } catch (Exception e) {
                            Log.w("Websocket", e);
                        }

                        if ((System.currentTimeMillis() - secondsAgo)/1000 > 3) {
                            return;
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd HH:mm:ss");
                        Date resultdate = new Date(secondsAgo);
//                        String message = sdf.format(resultdate) + ": " + bluetoothPoints.toString() + " bluetooth and " + wifiPoints.toString() + " wifi points inserted for " + familyName + "/" + deviceName;
                        //String message = "1 second ago: estás en " + bluetoothPoints.toString() + " bluetooth and " + wifiPoints.toString() + " wifi points for " + familyName + "/" + deviceName;
                        oneSecondTimer.resetCounter();

                        //TextView rssi_msg = (TextView) findViewById(R.id.textOutput);
                        Log.d("Websocket", message);
                        //rssi_msg.setText(message);

                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final TextView ubicacionContentText = (TextView) findViewById(R.id.lblId);
                        //ubicacionContentText.setText("cannot connect to server");
                    }
                });
            }
        };
        mWebSocketClient.connect();
    }


    @Override
    protected void onDestroy() {
        Log.d(TAG, "MainActivity onDestroy()");
        if (alarms != null) alarms.cancel(recurringLl24);
        if (timer != null) timer.cancel();
        if (mWebSocketClient != null) {
            mWebSocketClient.close();
        }
        android.app.NotificationManager mNotificationManager = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(0);
        Intent scanService = new Intent(this, ScanService.class);
        stopService(scanService);
        super.onDestroy();
    }


}
