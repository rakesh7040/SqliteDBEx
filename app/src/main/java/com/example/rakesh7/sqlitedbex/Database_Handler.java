package com.example.rakesh7.sqlitedbex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_Handler extends SQLiteOpenHelper {

    public static final String DB_NAME = "ANDROID_DB.DB";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "ListData";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String DESIGNATION = "designation";
    public static final String MOBILE = "mobile";

    public Database_Handler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + DESIGNATION + " TEXT, " + MOBILE+ " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
