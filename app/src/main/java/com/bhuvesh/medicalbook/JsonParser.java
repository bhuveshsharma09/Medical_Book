package com.bhuvesh.medicalbook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    /*
    * purpose of this class is to parse the
    * API output JSON data into map list
    * which can then be displayed on google maps
    * */

    private HashMap<String,String> parseJsonObject(JSONObject jsonObject)
    {
        HashMap<String,String> dataList = new HashMap<>();

        try{
            String name = jsonObject.getString("name");
            String lat = jsonObject.getJSONObject("geometry")
                    .getJSONObject("location").getString("lat");
            String lng = jsonObject.getJSONObject("geometry")
                    .getJSONObject("location").getString("lng");

            dataList.put("name",name);
            dataList.put("lat",lat);
            dataList.put("lng",lng);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return dataList;
    }


    private List<HashMap<String,String>> parseJsonArray(JSONArray jsonArray)
    {
        List<HashMap<String,String>> dataList = new ArrayList<>();
        for (int i = 0; i<jsonArray.length();i++)
        {
            try {
                HashMap<String ,String> data = parseJsonObject((JSONObject) jsonArray.get(i));
                dataList.add(data);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }


public List<HashMap<String,String >> parseResult (JSONObject object)
{
    JSONArray jsonArray = null;
    try {
        jsonArray = object.getJSONArray("results");
    } catch (JSONException e) {
        e.printStackTrace();
    }

    return parseJsonArray(jsonArray);
}
}
