package com.bhuvesh.medicalbook.nearbyhospitalfeature;

import android.util.Log;

import junit.framework.TestCase;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonParserTest extends TestCase {


    public void testParseResult() throws FileNotFoundException {
        JSONObject jsonObject;
        try {
            FileReader reader = new FileReader("map_data.json");
            Log.d("data", String.valueOf(reader));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();}
    }

    public void testParseJsonObject() {
    }

    public void testParseJsonArray() {
    }

    public void testTestParseResult() {
    }
}