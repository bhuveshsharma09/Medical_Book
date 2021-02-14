package com.bhuvesh.medicalbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RecordHandler extends DatabaseManger {
    /*
    Purpose of record handler is to help DailyMedicalRecord
    activity and database communicate and perform Create, Update, and delete operations
    * */

    public RecordHandler( Context context) {
        super(context);
    }

    public boolean createRecord(Record record) {
        // add a new record in database table
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", record.getRecordTitle());
        contentValues.put("description", record.getRecordDescription());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // check if database has been successfully created
        boolean result = sqLiteDatabase.insert("Record", null, contentValues) > 0;
        sqLiteDatabase.close();
        //Log.d("record added",String.valueOf(result));
        return result;
    }

    public ArrayList<Record> readFromRecords() {
        // this function is to read all the records from SQLite database
        // these records can be displayed in recyclyer view by DailyMedicalRecord activity
        ArrayList<Record> records = new ArrayList<>();
        String SQLQuery = "SELECT * FROM Record ORDER BY id ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQLQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String description = cursor.getString(cursor.getColumnIndex("description"));

                Record record = new Record(title, description);
                record.setId(id);
                // add record in the records array
                records.add(record);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return records;
    }

    public Record readOneRecord(int id) {
        // this function is to read a particular record
        // based on ID
        Record record = null;

        String SQLQuery = "SELECT * FROM Record WHERE id =" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQLQuery, null);

        if (cursor.moveToFirst()) {
                int recordId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String columne_tittle = cursor.getString(cursor.getColumnIndex("title"));
                String columne_description = cursor.getString(cursor.getColumnIndex("description"));

                record = new Record(columne_tittle, columne_description);
                record.setId(recordId);
            }
            cursor.close();
            db.close();
        return record;
    }

    public boolean update(Record record){
        //function to update record
        ContentValues values = new ContentValues();
        values.put("title",record.getRecordTitle());
        values.put("description",record.getRecordDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean result = db.update("Record", values,"id='"+record.getId(),null) >0;

       db.close();
       return result;
    }



    public boolean remove(int id){
        //function to remove record from database
        boolean isRemoved;
        SQLiteDatabase db = this.getWritableDatabase();
        isRemoved = db.delete("Record","id='" + id + "'",null) >0;
        db.close();
        return isRemoved;
    }











}

