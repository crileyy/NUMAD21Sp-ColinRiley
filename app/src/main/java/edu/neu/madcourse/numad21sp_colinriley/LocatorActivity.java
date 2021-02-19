package edu.neu.madcourse.numad21sp_colinriley;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (gpsEnabled) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Searching for location", Toast.LENGTH_SHORT).show();
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

    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(listener);
    }
}