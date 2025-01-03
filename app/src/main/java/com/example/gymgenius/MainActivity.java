package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void exerciseguideFunction(View view) {
        startActivities(new Intent[]{new Intent(this, ExerciseGuide.class)});
    }
    public void bmimeterFunction(View view) {
        startActivities(new Intent[]{new Intent(this, BMImeter.class)});
    }

    public void workoutFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Workout.class)});
    }

    public void stretchingFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Stretching.class)});
    }

    public void dietPlanFunction(View view) {
        startActivities(new Intent[]{new Intent(this, DietPlans.class)});
    }

    public void TipsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Tips.class)});
    }

    public void profileFunction(View view) {
        startActivities(new Intent[]{new Intent(this, UserProfileActivity.class)});
    }
}