package com.example.eduaccess;


public class Video {
    public static final String JSON_KEY_VIDEO_ID = "videoId";
    public static final String JSON_KEY_VIDEO_TITLE = "videoTitle";
    public static final String JSON_KEY_VIDEO_TEACHER = "videoTeacher";
    public static final String JSON_KEY_IMAGE_LINK = "imageLink";
    public static final String JSON_KEY_VIDEO_LINK = "videoLink";
    int videoId;
    String videoTitle;
    String videoTeacher;
    String imageLink;
    String videoLink;

    public Video(String JSON_KEY_VIDEO_ID, String JSON_KEY_VIDEO_TITLE, String JSON_KEY_VIDEO_TEACHER, String JSON_KEY_IMAGE_LINK, String JSON_KEY_VIDEO_LINK) {
    }

    // Constructor, make object -> store the object to json file
    public Video(int videoId, String videoTitle, String videoTeacher, String imageLink, String videoLink) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.videoTeacher = videoTeacher;
        this.imageLink = imageLink;
        this.videoLink = videoLink;
    }

    // Getter and setter object

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoTeacher() {
        return videoTeacher;
    }

    public void setVideoTeacher(String videoTeacher) {
        this.videoTeacher = videoTeacher;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
