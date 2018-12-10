package com.example.user1.assignmentjobseekerapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class seeker_signup extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    private static final Pattern mobile_pattern = Pattern.compile("^(((0|((\\+)?60([- ])?))|((\\((\\+)?60\\)([- ])?)))?[1-9]\\d{8})?$");



    EditText textusername;
    EditText textpwd;
    EditText textemail;
    EditText textname;
    EditText textcontact;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_signup);
        openHelper= new DataBaseHelper(this);


        textusername=(EditText)findViewById(R.id.textusername);
        textpwd=(EditText)findViewById(R.id.textpwd);
        textemail=(EditText)findViewById(R.id.textemail);
        textname=(EditText)findViewById(R.id.textname);
        textcontact=(EditText)findViewById(R.id.textcontact);








    }
    private boolean validateusername(){

        String unameinput=textusername.getEditableText().toString().trim();
        if(unameinput.isEmpty())
        {
            textusername.setError("Cant be Empty");
            return false;
        }
        else if(unameinput.length()<6)
        {
            textusername.setError("The length should larger than 5");
            return false;
        }
        else {textusername.setError(null);
            return true;

        }

    }
    private boolean validatepwd(){
        String pwd=textpwd.getEditableText().toString().trim();
        if(pwd.isEmpty())
        {
            textpwd.setError("Cant be Empty");
            return false;
        }
        else if(pwd.length()<6)
        {
            textpwd.setError("The length should larger than 5");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(pwd).matches()) {
            textpwd.setError("Password too weak");
            return false;
        }

        else {textpwd.setError(null);
            return true;

        }



    }
    private boolean validateEmail(){

        String email=textemail.getEditableText().toString().trim();
        if(email.isEmpty())
        {
            textemail.setError("Cant be Empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textemail.setError("Please enter a valid email address");
            return false;
        }
        else {textemail.setError(null);
            return true;

        }

    }
    private boolean validatename(){
        String nameinput=textname.getEditableText().toString().trim();
        if(nameinput.isEmpty())
        {
            textname.setError("Cant be Empty");
            return false;
        }

        else {textname.setError(null);
            return true;

        }
    }

    private boolean valiedatecontact(){
        String contactinput=textcontact.getEditableText().toString().trim();

        if(contactinput.isEmpty())
        {
            textcontact.setError("Cant be Empty");
            return false;
        }
        else if (!mobile_pattern.matcher(contactinput).matches()) {
            textcontact.setError("invalid phone");
            return false;

        }
        else {textcontact.setError(null);
            return true;

        }


    }



    public void home(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void confirmInput(View v) {
        String inputusername=textusername.getEditableText().toString().trim();
        String inputpwd=textpwd.getEditableText().toString().trim();
        String inputemail=textemail.getEditableText().toString().trim();
        String inputname=textname.getEditableText().toString().trim();
        String inputcontact=textcontact.getEditableText().toString().trim();
        db=openHelper.getWritableDatabase();
        if (!validateusername()|!validatepwd()|!validateEmail()|!validatename()|!valiedatecontact()) {
            return;
        }



        SQLiteDatabase sqLiteDatabase= openHelper.getWritableDatabase();

        Cursor cursor=sqLiteDatabase.query(DataBaseHelper.user_table,null,null,null,null,null,null);
        boolean hasRecord=cursor.moveToFirst();
        int c=0;
        if(hasRecord)
        {
            do{
                String uname = cursor.getString(cursor.getColumnIndex("UserName"));
            if(inputusername.equals(uname))
            {
                c++;

            }

            }while(cursor.moveToNext());
        }

if(c>0)
{
    Toast.makeText(getApplicationContext(), "Username used,please use another one",Toast.LENGTH_LONG).show();

}
else {

    String str = inputcontact + inputemail + inputname + inputpwd + inputusername;
    insertdata(inputusername, inputpwd, inputemail, inputname, inputcontact);
    Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();


}
    }
    public void insertdata(String uname,String pwd,String email,String name,String contact){

        String role="User";

        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseHelper.col_2,uname);
        contentValues.put(DataBaseHelper.col_3,pwd);
        contentValues.put(DataBaseHelper.col_4,email);
        contentValues.put(DataBaseHelper.col_5,name);
        contentValues.put(DataBaseHelper.col_6,contact);
        contentValues.put(DataBaseHelper.col_7,role);
        long id = db.insert(DataBaseHelper.user_table, null, contentValues);


    }













}
