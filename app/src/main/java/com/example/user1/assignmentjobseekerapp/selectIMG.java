package com.example.user1.assignmentjobseekerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class selectIMG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_img);

    }

    public void img1(View view) {
String value="1";

        Intent intent=new Intent(this,post_job.class);
        intent.putExtra ("url",value);

        startActivity(intent);
    }

    public void img2(View view) {

        String value="2";

        Intent intent=new Intent(this,post_job.class);
        intent.putExtra ("url",value);

        startActivity(intent);
    }
    public void img3(View view) {
        String value="3";

        Intent intent=new Intent(this,post_job.class);
        intent.putExtra ("url",value);

        startActivity(intent);
    }
    public void img4(View view) {

        String value="4";

        Intent intent=new Intent(this,post_job.class);
        intent.putExtra ("url",value);

        startActivity(intent);
    }
    public void img5(View view) {
        String value="5";

        Intent intent=new Intent(this,post_job.class);
        intent.putExtra ("url",value);

        startActivity(intent);
    }
    public void img6(View view) {
        String value="6";

        Intent intent=new Intent(this,post_job.class);
        intent.putExtra ("url",value);

        startActivity(intent);
    }
    public void img7(View view) {

        String value="7";

        Intent intent=new Intent(this,post_job.class);
        intent.putExtra ("url",value);

        startActivity(intent);
    }
    public void img8(View view) {


        String value="8";

        Intent intent=new Intent(this,post_job.class);
        intent.putExtra ("url",value);

        startActivity(intent);
    }


}
