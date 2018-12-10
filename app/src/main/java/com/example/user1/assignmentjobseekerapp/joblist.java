package com.example.user1.assignmentjobseekerapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class joblist extends AppCompatActivity {
    private static final String TAG = "joblist";
    DataBaseHelper2 myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joblist);
       Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listview);
        myDB = new DataBaseHelper2(this);

        SQLiteDatabase sqLiteDatabase= myDB.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * FROM Job_table",null);
        boolean hasRecord=cursor.moveToFirst();


        ArrayList<job> peopleList = new ArrayList<job>();
       if(hasRecord)
        {
            do{
                String title = cursor.getString(cursor.getColumnIndex("Title"));
                Integer salary = cursor.getInt(cursor.getColumnIndex("Salary"));
                String desc = cursor.getString(cursor.getColumnIndex("Description"));
                Integer num_app=cursor.getInt(cursor.getColumnIndex("num_apply"));
                String location=cursor.getString(cursor.getColumnIndex("location"));

                job newjob = new job(title,salary,desc,num_app,location);



                peopleList.add(newjob);

            }while(cursor.moveToNext());
        }

        cursor.moveToFirst();


/*
        while (cursor.moveToNext()) {
    String title = cursor.getString(cursor.getColumnIndex("Title"));
    Integer salary = cursor.getInt(cursor.getColumnIndex("Salary"));
    String desc = cursor.getString(cursor.getColumnIndex("Description"));
    Integer num_app=cursor.getInt(cursor.getColumnIndex("num_apply"));
    String location=cursor.getString(cursor.getColumnIndex("location"));

job newjob= new job(title,salary,desc,num_app,location);



    peopleList.add(newjob);
}

        job newjob= new job("2",3,"test",2,"test");

        peopleList.add(newjob);




*/


        joblist_adapater adapter = new joblist_adapater(this, R.layout.adapter_view, peopleList);
        mListView.setAdapter(adapter);


    }
}
