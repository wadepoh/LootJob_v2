package com.example.user1.assignmentjobseekerapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class joblist_adapater extends ArrayAdapter<job> {
    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;


    private static class ViewHolder {

       TextView Title;
        TextView Salary;
        TextView desc;
        TextView numb_app;
        TextView location;
    }
    public joblist_adapater(Context context, int resource, ArrayList<job> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String title = getItem(position).getTitle();
        String desc = getItem(position).getDesc();
        String location=getItem(position).getLocation();
        Integer num_app=getItem(position).getNumb_app();
        Integer salary = getItem(position).getSalary();

        //Create the person object with the information
        job person = new job(title,salary,desc,num_app,location);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();



            holder.Title = (TextView) convertView.findViewById(R.id.text_Job);
            holder.desc = (TextView) convertView.findViewById(R.id.text_Desc);
            holder.Salary= (TextView) convertView.findViewById(R.id.text_Salary);
            holder.location= (TextView) convertView.findViewById(R.id.text_Location);
            holder.numb_app= (TextView) convertView.findViewById(R.id.textNum_APP);



            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.Title.setText(person.getTitle());
        holder.desc.setText(person.getDesc());
        holder.location.setText(person.getLocation());
        holder.Salary.setText(person.getSalary().toString());
        holder.numb_app.setText(person.getNumb_app().toString());





        return convertView;
    }

}
