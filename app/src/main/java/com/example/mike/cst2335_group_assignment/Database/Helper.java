package com.example.mike.cst2335_group_assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mike on 2016-12-01.
 */

public class Helper  extends SQLiteOpenHelper{
    public static final String ACTIVITY_NAME = "Database.Helper";
    public static final String DATABASE_NAME = "FavChannelDatabase";
    public static int VERSION_NUMBER = 1;
    public static final String KEY_ID = "Favorite_ID";
    public static final String KEY_CHANNEL = "Favorite_Channel";
    public static final String TABLE_NAME = "Chat_Message";

    public Helper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUMBER);
    }

    public void onCreate(SQLiteDatabase db){
        Log.i(ACTIVITY_NAME, ">> onCreate");
        String CREATE_FAVORITE_CHANNEL_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_CHANNEL + " TEXT)";
        db.execSQL(CREATE_FAVORITE_CHANNEL_TABLE);
    }
    public void inserData(int ChannelNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CHANNEL, ChannelNumber);
        long insertResult = db.insert(TABLE_NAME, null, contentValues);
        Log.i(ACTIVITY_NAME, "insert data result :" + insertResult);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i(ACTIVITY_NAME, "onUpdate version " + oldVersion + " to new database version: " + newVersion);
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor curser = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return curser;
    }
}
