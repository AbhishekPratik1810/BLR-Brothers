package com.blrbrothers;

public class EventModel {
    public String time,location,title,imageLink;
    public int id;

    public EventModel() {
    }

    public EventModel(String time, String location, String title, String imageLink,int id) {
        this.time = time;
        this.location = location;
        this.title = title;
        this.imageLink = imageLink;
        this.id=id;
    }


}
