package com.bhuvesh.medicalbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManger extends SQLiteOpenHelper {



    // define database attributes like name and version number
    private static final int VERSION = 1;
    private static final String DATA_BASE_NAME = "HealthRecordDatabase";


    public DatabaseManger(@Nullable Context context) {
        super(context, DATA_BASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE Record (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)";
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQLQuery = "DROP TABLE IF EXISTS Record";
        db.execSQL(SQLQuery);
        onCreate(db);

    }
}
