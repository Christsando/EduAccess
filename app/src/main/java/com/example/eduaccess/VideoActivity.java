package com.example.eduaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Get the saved ActionBar color and status bar color from preferences
        int actionBarColor = getActionBarColorFromPreferences();
        int statusBarColor = getStatusBarColorFromPreferences();

        // Set the ActionBar color and status bar color
        setActionBarAndStatusBarColors(actionBarColor, statusBarColor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // find views ID from my activity
        WebView videoPLayerView = findViewById(R.id.videoView);
        TextView videoPlayerTitle = findViewById(R.id.videoPlayerTitle);
        TextView videoPlayerTeacher = findViewById(R.id.videoPlayerTeacher);

        // Retrieve data from Intent TypeAdapter Recyclerview
        Intent intent = getIntent();
        String videoId = intent.getStringExtra("videoId");
        String videoLink = intent.getStringExtra("videoLink");
        String videoTitleText = intent.getStringExtra("videoTitle");
        String videoTeacherText = intent.getStringExtra("videoTeacher");

        // Set data to user view
        videoPlayerTitle.setText(videoTitleText);
        videoPlayerTeacher.setText(videoTeacherText);

        // Set web view
       String video = "<iframe width=\"100%\" height=\"100%\" " +
               "src=\"https://www.youtube.com/embed/" + videoLink + "\" " +
               "title=\"YouTube video player\" frameborder=\"0\" " +
               "allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" " +
               "allowfullscreen></iframe>";
       videoPLayerView.loadData(video, "text/html", "utf-8");
       videoPLayerView.getSettings().setJavaScriptEnabled(true);
    }
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
}