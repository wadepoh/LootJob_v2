package com.example.user1.assignmentjobseekerapp;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class joblist extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "joblist";
    DataBaseHelper2 myDB;
private DrawerLayout mDrawerLayout;
private         long  backpresstime;

    private ActionBarDrawerToggle mToggle;
EditText searchfilter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joblist);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = (ListView) findViewById(R.id.listview);
        myDB = new DataBaseHelper2(this);


searchfilter=(EditText)findViewById(R.id.searchfilter);
        SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * FROM Job_table", null);
        boolean hasRecord = cursor.moveToFirst();


        ArrayList<job> peopleList = new ArrayList<job>();
        if (hasRecord) {
            do {
                String title = cursor.getString(cursor.getColumnIndex("Title"));
                Integer salary = cursor.getInt(cursor.getColumnIndex("Salary"));
                String desc = cursor.getString(cursor.getColumnIndex("Description"));
                Integer num_app = cursor.getInt(cursor.getColumnIndex("num_apply"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String imageURL = cursor.getString(cursor.getColumnIndex("imageURL"));

                job newjob = new job(title, salary, desc, num_app, location, imageURL);


                peopleList.add(newjob);

            } while (cursor.moveToNext());
        }

        cursor.moveToFirst();


        joblist_adapater adapter = new joblist_adapater(this, R.layout.adapter_view, peopleList);
        mListView.setAdapter(adapter);
mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open, R.string.close);
mDrawerLayout.addDrawerListener(mToggle);
mToggle.syncState();



NavigationView navigationView=(NavigationView)findViewById(R.id.navigation_view);
navigationView.setNavigationItemSelectedListener(this);
      BottomNavigationView bottomnav=findViewById(R.id.btmnav1);
            bottomnav.setOnNavigationItemSelectedListener(navListener);

searchfilter.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {



       searchFLTR();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});


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

public void searchFLTR(){
        EditText s;
        s=(EditText)findViewById(R.id.searchfilter);
        String searchtext=s.getEditableText().toString().trim();
    ListView mListView = (ListView) findViewById(R.id.listview);

    SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("Select * FROM Job_table where Title like ?", new String[] {   searchtext+ "%" });


    boolean hasRecord = cursor.moveToFirst();


    ArrayList<job> peopleList = new ArrayList<job>();
    if (hasRecord) {
        do {
            String title = cursor.getString(cursor.getColumnIndex("Title"));
            Integer salary = cursor.getInt(cursor.getColumnIndex("Salary"));
            String desc = cursor.getString(cursor.getColumnIndex("Description"));
            Integer num_app = cursor.getInt(cursor.getColumnIndex("num_apply"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            String imageURL = cursor.getString(cursor.getColumnIndex("imageURL"));

            job newjob = new job(title, salary, desc, num_app, location, imageURL);


            peopleList.add(newjob);

        } while (cursor.moveToNext());
    }

    cursor.moveToFirst();


    joblist_adapater adapter2 = new joblist_adapater(this, R.layout.adapter_view, peopleList);
    mListView.setAdapter(adapter2);
return;

}


    public void gojobapply(){
        Intent intent=new Intent(this,job_apply.class);
        startActivity(intent);
    }



private BottomNavigationView.OnNavigationItemSelectedListener navListener=
new BottomNavigationView.OnNavigationItemSelectedListener() {
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id= menuItem.getItemId();
        if(id==R.id.nav_Home){

            return true;
        }
        if(id==R.id.nav_jobapplied)
        {

gojobapply();
            return true;

        }
if(id==R.id.nav_Profile)
{

    goprofile();
    return true;
}


        return false;
    }
};

    private void goprofile() {
        Intent intent=new Intent(this,job_apply.class);
        startActivity(intent);
    }


    public boolean onOptionsItemSelected(MenuItem item){


        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
    return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
int id= menuItem.getItemId();
if(id==R.id.nav_home){
    Intent intent=new Intent(this,joblist.class);
    startActivity(intent);
    return true;
}
if(id==R.id.nav_logout)
{
    UtilsClipCodes.saveSharedSetting(joblist.this, "ClipCodes", "false");
    UtilsClipCodes.SharedPrefesSAVE(getApplicationContext(), "");
    Intent intent=new Intent(this,MainActivity.class);
    startActivity(intent);
    finish();
    return true;

}
        return false;
    }





    public void opennav(View view) {
        mDrawerLayout.openDrawer(Gravity.START);

    }
}
