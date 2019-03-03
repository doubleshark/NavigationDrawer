package com.example.srk.navigationdrawer;

public class GetData_From_FireBase {
    String title;
    String image;
    String description;
    String time;


    //Null Constructor for firebase
     public GetData_From_FireBase(){

     }
//Getter and setter For geeting and setting data to views

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
