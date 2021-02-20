package com.bhuvesh.medicalbook;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather extends AsyncTask<String, Void, String> {



    @Override
    protected String doInBackground(String... strings) {

        StringBuilder resultStringBuilder = new StringBuilder();

        try {
                String result = "";
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();

                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                 while ((result = bufferedReader.readLine()) != null)
            {
                resultStringBuilder.append(result).append("\n");
            }


            JSONObject jsonObject = new JSONObject(resultStringBuilder.toString());
            //String weatherDetailsTemp = jsonObject.getString("temp");

            JSONObject newJsonObject = jsonObject.getJSONObject("main");
           String weatherDetailsTemp = newJsonObject.getString("temp");




            String weatherDetailsHum = newJsonObject.getString("humidity");
            weatherDetailsTemp = weatherDetailsTemp + " C S"+weatherDetailsHum;


            Log.d("data ji",resultStringBuilder.toString());
            return weatherDetailsTemp;




        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


}

