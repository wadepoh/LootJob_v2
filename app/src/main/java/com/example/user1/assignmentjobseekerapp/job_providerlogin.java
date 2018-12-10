package com.example.user1.assignmentjobseekerapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class job_providerlogin extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    EditText _txtEmail, _txtPass;
    EditText textusername;
    EditText textpwd;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_providerlogin);
        textusername=(EditText)findViewById(R.id.textusername);
        textpwd=(EditText)findViewById(R.id.textpwd);
        btnlogin=(Button)findViewById(R.id.button3);
        openHelper=new DataBaseHelper(this);
        db = openHelper.getReadableDatabase();

    }

private boolean validateusername() {
    String unameinput=textusername.getEditableText().toString().trim();

    if(unameinput.isEmpty())
    {
        textusername.setError("Cant be Empty");
        return false;
    }

    else {
        textusername.setError(null);
        return true;

    }



    }
private boolean validatepwd() {
    String pwdinput = textpwd.getEditableText().toString().trim();

    if (pwdinput.isEmpty()) {
        textpwd.setError("Cant be Empty");
        return false;
    } else {
        textpwd.setError(null);
        return true;


    }
}


    public void signup(View view) {
        Intent intent=new Intent(this,provider_signup.class);
        startActivity(intent);
    }

    public void confirmInput(View v) {

        if (!validateusername()|!validatepwd()) {
            return;
        }

        String username = textusername.getText().toString();
        String pass = textpwd.getText().toString();
String role="Admin";
        cursor = db.rawQuery("SELECT *FROM " + DataBaseHelper.user_table + " WHERE " + DataBaseHelper.col_2 + "=? AND " + DataBaseHelper.col_3 + "=? AND "+DataBaseHelper.col_7+"=?" , new String[]{username, pass,role});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,post_job.class);
                startActivity(intent);
            } else {

                Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
            }
        }





    }


    public void forgotpwd(View view) {
        Intent intent=new Intent(this,forgotpwd.class);
        startActivity(intent);
    }
}
