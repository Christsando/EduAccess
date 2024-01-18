package com.example.eduaccess;

import static com.example.eduaccess.Video.JSON_KEY_VIDEO_ID;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VideoRepository {

    private DatabaseReference databaseReference;

    public VideoRepository() {
        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Ref. to my db
        databaseReference = firebaseDatabase.getReference("Videos");
    }

    // Save a single video to the database
    public void saveVideo(ArrayList<Video> video) {
       Video videos = new Video(JSON_KEY_VIDEO_ID, Video.JSON_KEY_VIDEO_TITLE, Video.JSON_KEY_VIDEO_TEACHER, Video.JSON_KEY_IMAGE_LINK, Video.JSON_KEY_VIDEO_LINK);
       databaseReference.child(Video.JSON_KEY_VIDEO_ID).setValue(video);
    }
}
