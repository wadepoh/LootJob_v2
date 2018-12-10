package com.example.user1.assignmentjobseekerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DataBaseHelper2 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="dbtwo.db";
    public static final String TABLE_NAME="Job_table";
    public static final String col_1="ID";
    public static final String col_2="Title";
    public static final String col_3="Salary";
    public static final String col_4="Description";
    public static final String col_5="num_apply";
    public static final String col_6="location";

    public DataBaseHelper2(Context context) {
        super(context,DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,Title Text,Salary INTEGER,Description Text,num_apply INTEGER,location Text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="Select * FROM "+TABLE_NAME;
        Cursor data=db.rawQuery(query,null);
        return data;


    }

}
