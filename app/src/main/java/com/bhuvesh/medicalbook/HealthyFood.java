package com.bhuvesh.medicalbook;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//import java.net.http.HttpResponse;
public class HealthyFood extends AppCompatActivity {
    TextView foodName1, foodName2, foodName3;
    TextView foodRating1, foodRating2, foodRating3;
    Button foodOrder1, foodOrder2, foodOrder3;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_food);


        foodName1 = (TextView) findViewById(R.id.textView_food_name1);
        foodName2 = (TextView) findViewById(R.id.textView_food_name2);
        foodName3 = (TextView) findViewById(R.id.textView_food_name3);

        foodRating1 = (TextView) findViewById(R.id.textView_food_rating1);
        foodRating2 = (TextView) findViewById(R.id.textView_food_rating2);
        foodRating3 = (TextView) findViewById(R.id.textView_food_rating3);

        foodOrder1 = (Button) findViewById(R.id.button_food_order1);
        foodOrder2 = (Button) findViewById(R.id.button_food_order2);
        foodOrder3 = (Button) findViewById(R.id.button_food_order3);



        final String postCode = "438611";
        final String type = "healthy";

        HttpResponse<String> response = null;
        try {
            response = (HttpResponse<String>) Unirest
                    .get("https://deliveroo.com.sg/restaurants/singapore/katong?fulfillment_method=DELIVERY")
                    .queryString("postcode", postCode)
                    .queryString("category", type)
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        final Document htmlSnippet = Jsoup.parseBodyFragment(response.getBody());
        int i;
        for (Element pp : htmlSnippet.select("#__next > div > div > div.ccl-d523c73794f30c03.ccl-917a4d5b8cd3a9e3.ccl-71fbf9dc5f85ebd4 > div > div.HomeLayout-b45e85df675abd0e > div > ul"))
        {
            for(i = 0; i<5;i++){

                Log.d("lat", String.valueOf((pp.child(i).text())));
            }
        }

















    }
}