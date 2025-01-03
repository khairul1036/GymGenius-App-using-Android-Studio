package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExerciseGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_guide);

    }

    public void backFunction(View view) {
        startActivities(new Intent[]{new Intent(this, MainActivity.class)});
    }

    public void BodyWeightFunction(View view) {
        startActivities(new Intent[]{new Intent(this, BodyWeight.class)});
    }

    public void battleropeFunction(View view) {
        startActivities(new Intent[]{new Intent(this, BattleRope.class)});
    }

    public void gymFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Gym.class)});
    }

    public void boxFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Box.class)});
    }

    public void suspensionFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Suspension.class)});
    }

    public void kettlebellFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Kettlebell.class)});
    }

    public void profileFunction(View view) {
        startActivities(new Intent[]{new Intent(this, UserProfileActivity.class)});
    }
}