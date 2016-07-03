package com.example.alexliu.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alexliu on 2016-07-03.
 */
public class MyDataBase {
    private SQLiteDatabase db;
    private Context context;
    private final MyHelper helper;

    public MyDataBase (Context c){
        context = c;
        helper = new MyHelper(context);
    }

    public long insertData (String input, String name, String type, String amount, String date)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.INPUT, input);
        contentValues.put(Constants.NAME, name);
        contentValues.put(Constants.TYPE, type);
        contentValues.put(Constants.AMOUNT, amount);
        contentValues.put(Constants.DATE, date);
        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.INPUT, Constants.NAME, Constants.TYPE, Constants.AMOUNT, Constants.DATE};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }


    public Cursor getSelectedData(String type)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.UID, Constants.INPUT,Constants.NAME, Constants.TYPE, Constants.AMOUNT, Constants.DATE};

        String selection = Constants.TYPE + "='" +type+ "'";  //Constants.TYPE = 'type'
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);
        return cursor;
//        StringBuffer buffer = new StringBuffer();
//        while (cursor.moveToNext()) {
//
//            int index1 = cursor.getColumnIndex(Constants.NAME);
//            int index2 = cursor.getColumnIndex(Constants.TYPE);
//            int index3 = cursor.getColumnIndex(Constants.LOCATION);
//            int index4 = cursor.getColumnIndex(Constants.LATIN);
//            String plantName = cursor.getString(index1);
//            String plantType = cursor.getString(index2);
//            String plantLoc = cursor.getString(index3);
//            String plantLat = cursor.getString(index4);
//            buffer.append(plantName + " " + plantType + plantLoc + "" + plantLat +"\n");
//        }
//        return buffer.toString();
    }

}

