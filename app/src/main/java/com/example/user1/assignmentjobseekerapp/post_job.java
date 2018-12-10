package com.example.user1.assignmentjobseekerapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class post_job extends AppCompatActivity {


    EditText texttitle;
    EditText textsalary;
    EditText textdesc;
    EditText textlocation;


    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
        openHelper= new DataBaseHelper2(this);


        texttitle=(EditText)findViewById(R.id.text_title);
        textsalary=(EditText)findViewById(R.id.text_salary);
        textlocation=(EditText)findViewById(R.id.text_location);
        textdesc=(EditText)findViewById(R.id.text_desc);








    }
    private boolean validatetitle(){

        String unameinput=texttitle.getEditableText().toString().trim();
        if(unameinput.isEmpty())
        {
            texttitle.setError("Cant be Empty");
            return false;
        }

        else {texttitle.setError(null);
            return true;

        }

    }
    private boolean validatesalary(){
        String salary=textsalary.getEditableText().toString().trim();
        if(salary.isEmpty())
        {
            textsalary.setError("Cant be Empty");
            return false;
        }



        else {textsalary.setError(null);
            return true;

        }



    }
    private boolean validatelocation(){

        String location=textlocation.getEditableText().toString().trim();
        if(location.isEmpty())
        {
            textlocation.setError("Cant be Empty");
            return false;
        }

        else {textlocation.setError(null);
            return true;

        }

    }
    private boolean validatedesc(){
        String nameinput=textdesc.getEditableText().toString().trim();
        if(nameinput.isEmpty())
        {
            textdesc.setError("Cant be Empty");
            return false;
        }

        else {textdesc.setError(null);
            return true;

        }
    }









    public void insertdata(View v) {

        if (!validatetitle()|!validatedesc()|!validatesalary()|!validatelocation()) {
            return;
        }

        db=openHelper.getWritableDatabase();
        String title=texttitle.getEditableText().toString().trim();
        String salary=textsalary.getEditableText().toString().trim();
        String desc=textdesc.getEditableText().toString().trim();
        String location=textlocation.getEditableText().toString().trim();


        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseHelper2.col_2,title);
        contentValues.put(DataBaseHelper2.col_3,salary);
        contentValues.put(DataBaseHelper2.col_4,desc);
        contentValues.put(DataBaseHelper2.col_6,location);
     long result=db.insert(DataBaseHelper2.TABLE_NAME, null, contentValues);
        if(result==-1) {
            Toast.makeText(getApplicationContext(), "Job Post unsuccessful", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Job Post  successful", Toast.LENGTH_LONG).show();
        }

    }
}
