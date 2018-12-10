package com.example.user1.assignmentjobseekerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DataBaseHelper extends SQLiteOpenHelper {
public static final String DATABASE_NAME="dbone.db";
public static final String user_table="User_table";
    public static final String col_1="ID";
    public static final String col_2="UserName";
    public static final String col_3="Password";
    public static final String col_4="Email";

    public static final String col_5="Name";
    public static final String col_6="Contact";
    public static final String col_7="Role";



    public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table "+user_table+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,UserName Text,Password Text,Email Text,Name Text,Contact Text,Role Text ) ");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS "+user_table);
onCreate(db);
    }


}
