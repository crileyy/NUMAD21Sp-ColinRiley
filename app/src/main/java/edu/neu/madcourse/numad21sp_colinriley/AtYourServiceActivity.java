package edu.neu.madcourse.numad21sp_colinriley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class AtYourServiceActivity extends AppCompatActivity {

    public static final String AT_YOUR_SERVICE_ACTIVITY = "At Your Service Activity: ";
    public TextView zip;
    public TextView currentTemp;
    public Button goButton;
    private final String WEATHER_URL = "https://api.weatherapi.com/v1/current.json?key=714dffb2ca184d4291733758210503&aqi=no&q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_your_service);

        zip = findViewById(R.id.enter_zip);
        currentTemp = findViewById(R.id.display_temp_text);
        goButton = findViewById(R.id.get_weather_button);
    }

    public void callWeatherServiceButtonHandler(View view) {
        Toast.makeText(getApplication(),"Fetching current temperature",Toast.LENGTH_SHORT).show();
        PingWeatherServiceAPI api = new PingWeatherServiceAPI();
        String urlWithZip = WEATHER_URL + zip.getText();
        Log.i("weather url with zip: ", urlWithZip);
        try {
            api.execute(urlWithZip);
        } catch (Exception e) {
            Log.e("Weather API call error: ", e.toString());
            Toast.makeText(getApplication(),"Error fetching current temperature",Toast.LENGTH_SHORT).show();
        }
    }

    private class PingWeatherServiceAPI  extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(AT_YOUR_SERVICE_ACTIVITY, "Making progress...");
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            JSONObject jObject = new JSONObject();
            try {
                URL url = new URL(params[0]);
                // Get String response from the url address
                String resp = NetworkUtil.httpGETResponse(url);
                jObject = new JSONObject(resp);
                return jObject;

            } catch (MalformedURLException e) {
                Log.e(AT_YOUR_SERVICE_ACTIVITY,"MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(AT_YOUR_SERVICE_ACTIVITY,"ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(AT_YOUR_SERVICE_ACTIVITY,"IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(AT_YOUR_SERVICE_ACTIVITY,"JSONException");
                e.printStackTrace();
            }

            return jObject;
        }

        @Override
        protected void onPostExecute(JSONObject jObject) {
            super.onPostExecute(jObject);
            try {
                currentTemp.setText("Current temperature (f): ".concat(jObject.getJSONObject("current").getString("temp_f")));
            } catch (JSONException e) {
                Toast.makeText(getApplication(),"Error setting current temperature",Toast.LENGTH_SHORT).show();
            }
        }
    }
}