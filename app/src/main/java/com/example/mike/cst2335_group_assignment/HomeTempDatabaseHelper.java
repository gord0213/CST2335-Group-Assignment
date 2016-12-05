package com.example.mike.cst2335_group_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Time;

/**
 * Created by Jeff McNally on 2016-12-05.
 */

public class HomeTempDatabaseHelper extends SQLiteOpenHelper {
    protected static final String ACTIVITY_NAME = "HomeTempDatabaseHelper";
    protected static final String DATABASE_NAME = "homeTempDatabase";
    protected static int VERSION_NUM = 2;
    protected static final String KEY_ID = "Temp_ID";
    protected static final String KEY_TIME = "Time";
    protected static final String KEY_TEMP = "Temperature";
    protected static final String TABLE_NAME = "Home_Temp_Schedule";



    public HomeTempDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +KEY_ID +" INTEGER PRIMARY KEY, " +KEY_TIME+ " TEXT, " +KEY_TEMP+ " TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public void insertData(String time, String temp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TIME, time);
        contentValues.put(KEY_TEMP, temp);

        long insertResult = db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

}
