package com.example.user.masiro;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

public class MainActivity extends NMapActivity {

    TextView tv;
    ToggleButton tb;
    //private final Context mContext;
//    boolean isGPSEnabled = false;
//    boolean isNetworkEnabled = false;
//    boolean isGetLocation = false; //GPS 상태값

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("test", "onLocationChanged, location:" + location);
            double longitude = location.getLongitude(); //경도
            double latitude = location.getLatitude();   //위도
            double altitude = location.getAltitude();   //고도
            float accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자
            tv.setText("위치정보 : " + provider + "\n위도 : " + longitude + "\n경도 : " + latitude
                    + "\n고도 : " + altitude + "\n정확도 : "  + accuracy);
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    public void OnButton(View v){
        Intent intent = new Intent(MainActivity.this,MapActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView2);
        tv.setText("위치정보 미수신중");

        tb = (ToggleButton)findViewById(R.id.toggle1);

        final LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    if(tb.isChecked()){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,1,mLocationListener);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,100,1,mLocationListener);
                        Toast.makeText(getApplicationContext(),"Complete",Toast.LENGTH_SHORT).show();
                    }
                    else {locationManager.removeUpdates(mLocationListener);
                        tv.setText("위치정보 미수신중");}

                }
                catch (SecurityException ex){}
            }
        });

    }
}
