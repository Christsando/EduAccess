package com.example.eduaccess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.eduaccess.databinding.ActivityVideoFeatureBinding;

import java.util.ArrayList;

public class VideoFeatureActivity extends AppCompatActivity {
    ActivityVideoFeatureBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_feature);

        // Get the saved ActionBar color and status bar color from preferences
        int actionBarColor = getActionBarColorFromPreferences();
        int statusBarColor = getStatusBarColorFromPreferences();

        // Set the ActionBar color and status bar color
        setActionBarAndStatusBarColors(actionBarColor, statusBarColor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize the VideoRepository
        VideoRepository videoRepository = new VideoRepository();
        ArrayList<Video> video= new ArrayList();
        video.add(new Video(0,"Python in 2 hours", "Bro Code", "https://img.youtube.com/vi/XKHEtdqhLK8/0.jpg","XKHEtdqhLK8"));
        video.add(new Video(1,"Java getter and setter", "Kelas Terbuka", "https://img.youtube.com/vi/zwDMHJzTUzs/0.jpg","zwDMHJzTUzs"));
        video.add(new Video(2,"Java tutorial full course", "Telusko", "https://img.youtube.com/vi/BGTx91t8q50/0.jpg","BGTx91t8q50"));
        video.add(new Video(3,"Firebase tutorial", "Easy Tuto", "https://img.youtube.com/vi/TAEbP_ccjsk/0.jpg","TAEbP_ccjsk"));

        // Save all video
        videoRepository.saveVideo(video);

        // Adapter
        TypeAdapter adapter = new TypeAdapter(this, video);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        // The recyclerview
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // Handle the back button click
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int getActionBarColorFromPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt("action_bar_color", Color.TRANSPARENT);
    }

    private int getStatusBarColorFromPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt("status_bar_color", Color.TRANSPARENT);
    }

    private void setActionBarAndStatusBarColors(int actionBarColor, int statusBarColor) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(actionBarColor));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(statusBarColor);
        }
    }
    public void openVideoActivity(){
        // Sending current data to new page

        // Change view to video play
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }
}