package com.example.user1.assignmentjobseekerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
