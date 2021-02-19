package edu.neu.madcourse.numad21sp_colinriley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LocatorActivity extends AppCompatActivity {
    private static final String LATITUDE_STRING = "LATITUDE_STRING";
    private static final String LONGITUDE_STRING = "LONGITUDE_STRING";
    LocationManager locationManager;
    TextView latitudeText, longitutdeText;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locator);
        latitudeText = findViewById(R.id.latitude_text);
        longitutdeText = findViewById(R.id.longitude_text);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        init(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (gpsEnabled) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10, listener);
            }
        }
    }

    private final LocationListener listener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            latitude = getString(R.string.latitude) + " " + location.getLatitude();
            longitude = getString(R.string.longitude) + " " + location.getLongitude();
            latitudeText.setText(latitude);
            longitutdeText.setText(longitude);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(listener);
    }

    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
    }

    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(LATITUDE_STRING, latitude);
        outState.putString(LONGITUDE_STRING, longitude);

        super.onSaveInstanceState(outState);
    }

    private void initialItemData(Bundle savedInstanceState) {
        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(LATITUDE_STRING)
                && savedInstanceState.containsKey(LONGITUDE_STRING)) {
            latitudeText.setText(savedInstanceState.getString(LATITUDE_STRING));
            longitutdeText.setText(savedInstanceState.getString(LONGITUDE_STRING));
        }
        // The first time to open this Activity
        else {
            Toast.makeText(this, "Searching for location", Toast.LENGTH_SHORT).show();
        }
    }
}