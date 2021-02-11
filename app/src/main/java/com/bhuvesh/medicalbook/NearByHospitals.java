package com.bhuvesh.medicalbook;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class NearByHospitals extends AppCompatActivity {


    // Create variables for Google maps
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double presentLat = 0;
    double presentLong = 0;
    Button searchbutton;
    String place = "hospital";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_hospitals);

        // Link up the variables with the resource
        // as it is a fragment, we need to get the R.id through FragmentManager
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_nearby_hospital);

        searchbutton = (Button) findViewById(R.id.button_get_nearby_hospitals);


        // create a variable for the place type which we are going to search
        // for this project, the place will be "Hospital"


        // Initialise the fused location provider
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Check for the device permissions

        if (ActivityCompat.checkSelfPermission(NearByHospitals.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // if the device grants permission for location this section will be called

            getPresentLocation();


        } else {
            ActivityCompat.requestPermissions(NearByHospitals.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);


            //if permission denied
            // request for permission


        }

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchbutton.setText("lul");
                String urlForHospital = "https://maps.googleapis.com/maps/api/place/nearbysearch/json"
                        + "?location=" + presentLat + "," + presentLong +
                        "&radius=5000" +
                        "&type=hospital"+
                        "&sensor=true" +
                        "&key=" + getResources().getString(R.string.google_map_key);


                Log.d("URL", String.valueOf(urlForHospital));
                Log.d("lat", String.valueOf(presentLat));
                Log.d("lng", String.valueOf(presentLong));



                new PlaceTask().execute(urlForHospital);


            }
        });


    }

    private void getPresentLocation() {






        @SuppressLint("MissingPermission") Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null)
                {
                    // get lat and long coordinates
                    presentLat = location.getLatitude();
                    presentLong = location.getLongitude();

                    Log.d("lat", String.valueOf(presentLat));
                    Log.d("lng", String.valueOf(presentLong));


                    // callback method to put the co-ordinates on the map to display
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map = googleMap;

                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(presentLat,presentLong), 10));



                        }
                    });
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getPresentLocation();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String, Integer,String>{
        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {

                Log.d("in place text", String.valueOf(strings[0]));

                data = downloadURL(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return data;


        }


        @Override
        protected void onPostExecute(String s) {

            // do the parsing of data
            new ParserTask().execute(s);



        }

        private String downloadURL(String string) throws IOException {

             URL url = new URL(string);

            Log.d("im url in downaliod", String.valueOf(url));

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.connect();

            InputStream stream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(stream)));

            StringBuilder stringBuilder = new StringBuilder();
            String tempString = "";

            while((tempString = bufferedReader.readLine()) != null)

            {

                stringBuilder.append(tempString);




            }


            String data = stringBuilder.toString();
            bufferedReader.close();

            Log.d("api data", String.valueOf(data));
            return data;

        }
    }

    private class ParserTask extends  AsyncTask<String, Integer, List<HashMap<String, String>>>
    {


        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {

            JsonParser jsonParser = new JsonParser();

            List<HashMap<String,String>> mapList = null;
            JSONObject object = null;
            try {
                 object = new JSONObject(strings[0]);

                 mapList = jsonParser.parseResult(object);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("map data", String.valueOf(mapList));
            return mapList;

        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            map.clear();

            for (int i = 0; i<hashMaps.size();i++)
            {
                HashMap<String ,String> hashMapList = hashMaps.get(i);

                double lat = Double.parseDouble(hashMapList.get("lat"));
                double lng = Double.parseDouble(hashMapList.get("lng"));
                String name = hashMapList.get("name");

                LatLng latLng = new LatLng(lat,lng);

                MarkerOptions options = new MarkerOptions();

                options.position(latLng);

                options.title(name);

                map.addMarker(options);








            }

        }
    }
}