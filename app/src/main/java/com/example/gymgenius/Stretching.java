package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Stretching extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stretching);


    }

    public void subStretchingFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Sub_Stretching.class)});
    }

    public void profileFunction(View view) {
        startActivities(new Intent[]{new Intent(this, UserProfileActivity.class)});
    }
}