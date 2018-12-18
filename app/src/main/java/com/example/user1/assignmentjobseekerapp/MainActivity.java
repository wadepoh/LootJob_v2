package com.example.user1.assignmentjobseekerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DataBaseHelper myDb;

/*    text.setText(SP.getString("Name", null));
*/
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
CheckUser();
        myDb=new DataBaseHelper(this);
    }

    public void job_seekerlogin(View view) {
        Intent intent=new Intent(this,job_seekerlogin.class);
        startActivity(intent);
    }

    public void job_providerlogin(View view) {
        Intent intent=new Intent(this,job_providerlogin.class);
        startActivity(intent);
    }
    public void CheckUser(){
String name="";
        Boolean Check = Boolean.valueOf(UtilsClipCodes.readSharedSetting(MainActivity.this, "ClipCodes", "true"));



        name=UtilsClipCodes.returnName(getApplicationContext());
        String uname="admin1";

         if(Check&&uname.equals(name)) {
            Toast.makeText(this, "Login as " + name, Toast.LENGTH_SHORT).show();
           Intent introIntent1 = new Intent(MainActivity.this, job_providerlogin.class);
            introIntent1.putExtra("ClipCodes", Check);
            startActivity(introIntent1);


        }

       else if (Check) {
          Toast.makeText(this, "Login as "+name, Toast.LENGTH_SHORT).show();

            Intent introIntent = new Intent(MainActivity.this, job_seekerlogin.class);
            introIntent.putExtra("ClipCodes", Check);

          startActivity(introIntent);
        }
    }
}
