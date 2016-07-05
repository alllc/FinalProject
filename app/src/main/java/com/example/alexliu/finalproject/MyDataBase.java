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

//    public Cursor getData()
//    {
//        SQLiteDatabase db = helper.getWritableDatabase();
//
//        String[] columns = {Constants.UID, Constants.INPUT, Constants.NAME, Constants.TYPE, Constants.AMOUNT, Constants.DATE};
//        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null, null, null);
//        return cursor;
//    }


    public Cursor getSelectedData(String input)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {Constants.UID,Constants.INPUT, Constants.NAME, Constants.TYPE, Constants.AMOUNT,Constants.DATE};

        String selection = Constants.INPUT + "='" +input+ "'";  //Constants.TYPE = 'type'
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);
        return cursor;

    }

}

