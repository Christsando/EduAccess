package com.example.eduaccess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.time.LocalTime;

public class HomeActivity extends AppCompatActivity{
    Button buttonLogout;
    FirebaseUser user;
    DatabaseReference reference;
    String uid;
    CardView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set the status bar color
        int actionBarColor = ContextCompat.getColor(this, R.color.blue);
        int statusBarColor = ContextCompat.getColor(this, R.color.blue);
        setActionBarAndStatusBarColors(actionBarColor, statusBarColor);
        saveColorsToPreferences(actionBarColor, statusBarColor);

        // initialize variable
        buttonLogout = (Button) findViewById(R.id.button_logout);
        video = findViewById(R.id.videoFeature);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference();

        // get userName on db
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String fName = dataSnapshot.child("Users").child(uid).child("firstName").getValue(String.class);
                String lName = dataSnapshot.child("Users").child(uid).child("lastName").getValue(String.class);

                // Log statements for debugging
                Log.d("HomeActivity", "FirstName: " + fName);
                Log.d("HomeActivity", "LastName: " + lName);

                TextView userName = findViewById(R.id.Name);
                userName.setText(fName + " " + lName);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Please check your internet connection!", Toast.LENGTH_SHORT);
            }
        });

        // Logout button
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideoActivity();
            }
        });

        // Greeting user "Hello, *Time*
        TextView greetingUser = findViewById(R.id.greetUser);
        TextView userName = findViewById(R.id.Name);

        // Setting greet to user by time
        LocalTime currentTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentTime = LocalTime.now();
        }
        int hour = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            hour = currentTime.getHour();
        }

        if (hour >= 6 && hour < 12) {
            greetingUser.setText("Good Morning!");
        } else if (hour >= 12 && hour < 18) {
            greetingUser.setText("Good Afternoon!");
        } else {
            greetingUser.setText("Good Evening!");
        }


    }
    public void openVideoActivity() {
        Intent intent = new Intent(this, VideoFeatureActivity.class);
        startActivity(intent);
    }

    private void setActionBarAndStatusBarColors(int actionBarColor, int statusBarColor) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(actionBarColor));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(statusBarColor);
        }
    }

    private void saveColorsToPreferences(int actionBarColor, int statusBarColor) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("action_bar_color", actionBarColor);
        editor.putInt("status_bar_color", statusBarColor);
        editor.apply();
    }
}