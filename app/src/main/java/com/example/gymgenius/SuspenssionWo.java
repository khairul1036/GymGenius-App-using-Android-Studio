package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SuspenssionWo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suspenssion_wo);

    }

    public void halfburpeeFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Wo_gym_pushup.class)});
    }

    public void crunchFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Wo_gym_pushup.class)});
    }

    public void pullupsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Wo_gym_pushup.class)});
    }

    public void squatsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Wo_gym_pushup.class)});
    }

    public void pushupsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Wo_gym_pushup.class)});
    }

    public void dipsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Wo_gym_pushup.class)});
    }

    public void muscleupsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Wo_gym_pushup.class)});
    }

    public void TuckFrontLeverFunction(View view) {
        startActivities(new Intent[]{new Intent(this, Wo_gym_pushup.class)});
    }

    public void backFunction(View view) {
        finish();
    }
}