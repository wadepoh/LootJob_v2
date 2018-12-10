package com.example.user1.assignmentjobseekerapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotpwd extends AppCompatActivity {
    EditText textuname;
    EditText textemail;
    Button btnsend;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpwd);
        openHelper=new DataBaseHelper(this);
        db = openHelper.getReadableDatabase();

        textuname=(EditText)findViewById(R.id.txtuname);
        textemail=(EditText)findViewById(R.id.txtemail );

    }



    private boolean validatename(){
        String nameinput=textuname.getEditableText().toString().trim();
        if(nameinput.isEmpty())
        {
            textuname.setError("Cant be Empty");
            return false;
        }

        else {textuname.setError(null);
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



    public void SendEmail(View view) {
        Log.i("Send email", "");
        String[] TO = {"cstan977@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(forgotpwd.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }}

}
