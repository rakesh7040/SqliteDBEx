package com.example.rakesh7.sqlitedbex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager  {

    private Database_Handler dbHandler;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHandler = new Database_Handler(context);
        database = dbHandler.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHandler.close();
    }

    public void insert(String name, String designation,String mobile) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(Database_Handler.NAME, name);
        contentValue.put(Database_Handler.DESIGNATION, designation);
        contentValue.put(Database_Handler.MOBILE, mobile);
        database.insert(Database_Handler.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { Database_Handler._ID, Database_Handler.NAME, Database_Handler.DESIGNATION,Database_Handler.MOBILE, Database_Handler.MOBILE  };
        Cursor cursor = database.query(Database_Handler.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String designation,String mobile) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database_Handler.NAME, name);
        contentValues.put(Database_Handler.DESIGNATION, designation);
        contentValues.put(Database_Handler.MOBILE, mobile);
        int i = database.update(Database_Handler.TABLE_NAME, contentValues, Database_Handler._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(Database_Handler.TABLE_NAME, Database_Handler._ID + "=" + _id, null);
    }

}
