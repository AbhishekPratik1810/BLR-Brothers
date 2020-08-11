package com.blrbrothers;

public class VideoModel {
    public String title,imageLink,webViewLink, videoDetails;
    public int id;

    public VideoModel() {
    }

    public VideoModel(String title, String imageLink, String webViewLink, String videoDetails, int id) {
        this.title = title;
        this.imageLink = imageLink;
        this.webViewLink = webViewLink;
        this.videoDetails = videoDetails;
        this.id=id;
    }

    public String getVideoDetails() {
        return videoDetails;
    }

    public void setVideoDetails(String videoDetails) {
        this.videoDetails = videoDetails;
    }

    public String getTitle() {
        return title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getWebViewLink() {
        return webViewLink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setWebViewLink(String webViewLink) {
        this.webViewLink = webViewLink;
    }
}
