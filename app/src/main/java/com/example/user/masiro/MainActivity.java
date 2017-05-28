package com.example.user.masiro;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private LocationManager locationManager;
    private ItemIntentReceiver mIntentReceiver;


    public void OnButton(View v){

        MapFragment fragment = new MapFragment();
        fragment.setArguments(new Bundle());
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragmentHere, fragment);
        fragmentTransaction.commit();

        //startLocationService();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startLocationService() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,
                        minDistance,gpsListener);

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,
                        minDistance,gpsListener);

                Location lastlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if(lastlocation != null){

                    Double latitude = lastlocation.getLatitude();
                    Double longitude = lastlocation.getLongitude();

                    String msg = "Latitude : " + latitude + " Longitude : " + longitude;
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

                }
                return;
            }

        }catch (SecurityException ex){
            ex.printStackTrace();
        }

    }

    private class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = "Latitude : " + latitude + " Longitude : " + longitude;
            Log.i("GPSListener",msg);

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
    }

}
