package com.example.srk.navigationdrawer.Others;

public class Getter_Setter {
    String title;
    String image;
    String description;
    String time;
    String url;


    //Null Constructor for firebase
     public Getter_Setter(){

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
