package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Box extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_box);

    }

    public void TuckFrontLeverFunction(View view) {
        startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 1)});
    }

    public void squatsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 2)});
    }

    public void crunchFunction(View view) {
        startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 3)});
    }

    public void dipsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 4)});
    }

    public void pullupsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 5)});
    }

    public void pushupsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 6)});
    }

    public void muscleupsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 7)});
    }

    public void halfburpeeFunction(View view) {
        startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 8)});
    }

    public void backFunction(View view) {
        finish();
    }

    public void profileFunction(View view) {
        startActivities(new Intent[]{new Intent(this, UserProfileActivity.class)});
    }
}