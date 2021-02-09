package com.bhuvesh.medicalbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RecordHandler extends DatabaseManger {
    public RecordHandler(@Nullable Context context) {
        super((Context) context);
    }


    public boolean createRecord(Record record) {
        ContentValues values = new ContentValues();
        values.put("title", record.getTitle());
        values.put("description", record.getDescription());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // check if database has been successfully created
        boolean result = sqLiteDatabase.insert("Record", null, values) > 0;
        return result;

    }

    public ArrayList<Record> readFromRecords() {
        ArrayList<Record> records = new ArrayList<>();

        String SQLQuery = "SELECT * FROM Record ORDER BY id ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQLQuery, null);

        if (cursor.moveToFirst()) {


///
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                Record record = new Record(title, description);
                record.setId(id);
                records.add(record);
            } while (cursor.moveToNext());

            ///















            cursor.close();
            db.close();
        }
        return records;


    }

    public Record readOneRecord(int id) {
        Record record = null;

        String SQLQuery = "SELECT * FROM Record WHERE id =" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQLQuery, null);

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                int recordId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String columne_tittle = cursor.getString(cursor.getColumnIndex("tittle"));
                String columne_description = cursor.getString(cursor.getColumnIndex("description"));

                record = new Record(columne_tittle, columne_description);
                record.setId(recordId);


            }
            cursor.close();
            db.close();


        }
        return record;
    }



    public boolean update(Record record){
        ContentValues values = new ContentValues();
        values.put("title",record.getTitle());
        values.put("description",record.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean result = db.update("Record", values,"id='"+record.getId(),null) >0;

       db.close();
       return result;


    }



    public boolean remove(int id){
        boolean isRemoved;
        SQLiteDatabase db = this.getWritableDatabase();

        isRemoved = db.delete("Record","id='" + id + "'",null) >0;

        db.close();
        return isRemoved;


    }











}

