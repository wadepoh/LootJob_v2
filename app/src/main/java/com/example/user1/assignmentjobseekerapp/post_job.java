package com.example.user1.assignmentjobseekerapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class post_job extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private         long  backpresstime;

    EditText texttitle;
    EditText textsalary;
    EditText textdesc;
    EditText textlocation;
  EditText texturl;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

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
        texturl=(EditText) findViewById(R.id.editimg);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("url");
            texturl.setText(j);
        }

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer2);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();



        NavigationView navigationView=(NavigationView)findViewById(R.id.nav2);
        navigationView.setNavigationItemSelectedListener(this);






    }






    public boolean onOptionsItemSelected(MenuItem item){


        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id= menuItem.getItemId();
        if(id==R.id.nav_home){
            Intent intent=new Intent(this,post_job.class);
            startActivity(intent);
            return true;
        }
        if(id==R.id.nav_logout)
        {
            UtilsClipCodes.saveSharedSetting(post_job.this, "ClipCodes", "false");
            UtilsClipCodes.SharedPrefesSAVE(getApplicationContext(), "");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            return true;

        }




        return false;
    }

    public void opennav2(View view) {
        mDrawerLayout.openDrawer(Gravity.START);

    }
    @Override
    public void onBackPressed() {
        if(backpresstime+2000>System.currentTimeMillis()){
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(startMain);

            super.onBackPressed();
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Please back again to logout", Toast.LENGTH_SHORT).show();
            backpresstime = System.currentTimeMillis();
        }
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
        String url=texturl.getEditableText().toString().trim();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseHelper2.col_2,title);
        contentValues.put(DataBaseHelper2.col_3,salary);
        contentValues.put(DataBaseHelper2.col_4,desc);
        contentValues.put(DataBaseHelper2.col_7,url);

        contentValues.put(DataBaseHelper2.col_6,location);
     long result=db.insert(DataBaseHelper2.TABLE_NAME, null, contentValues);
        if(result==-1) {
            Toast.makeText(getApplicationContext(), "Job Post unsuccessful", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Job Post  successful", Toast.LENGTH_LONG).show();
        }

    }

    public void selectimg(View view) {
        Intent intent=new Intent(this,selectIMG.class);
        startActivity(intent);
    }
}
