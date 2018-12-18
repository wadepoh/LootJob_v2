package com.example.user1.assignmentjobseekerapp;

public class job {
    private String Title;
    private Integer Salary;
    private String desc;
    private Integer numb_app;
    private String location;
    private  String imageURL;
public job(String Title,Integer Salary,String desc,Integer numb_app,String location,String imageURL)
{
this.Title=Title;
this.Salary=Salary;
this.desc=desc;
this.numb_app=numb_app;
this.location=location;
this.imageURL=imageURL;

}
public String getImageURL(){

    return imageURL;
}
public void setImageURL(String imageURL){
    this.imageURL=imageURL;

}

public String getTitle(){
    return Title;

}
public void setTitle(String Title) {
this.Title=Title;
}
public Integer getSalary()
{return Salary;

}
public void setSalary(Integer Salary){
    this.Salary=Salary;
}
public String getDesc(){
    return desc;
}
public void setDesc(String desc){
    this.desc=desc;
}
public Integer getNumb_app(){
    return numb_app;

}
public void setNumb_app(Integer numb_app){
    this.numb_app=numb_app;
}
public String getLocation(){
    return location;
}
public void setLocation(String location){
    this.location=location;


}
}
