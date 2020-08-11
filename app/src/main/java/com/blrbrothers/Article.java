package com.blrbrothers;

public class Article {
    public String Title,Body,ImageLink;
    public long TimeStamp;

    public Article(String title, String body, String imageLink, long timeStamp) {
        Title = title;
        Body = body;
        ImageLink = imageLink;
        TimeStamp = timeStamp;
    }

    public Article() {
    }

}