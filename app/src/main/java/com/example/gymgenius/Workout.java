package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Workout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);

    }

    public void wogymFunction(View view) {
        //startActivities(new Intent[]{new Intent(this, youtube_timer.class).putExtra("key", 1)});
        startActivities(new Intent[]{new Intent(this, wo_gym.class)});
    }

    public void woHomeEquipFunction(View view) {
        startActivities(new Intent[]{new Intent(this, HomeEquipWo.class)});
    }

    public void suspensionFunction(View view) {
        startActivities(new Intent[]{new Intent(this, SuspenssionWo.class)});
    }

    public void functionalTrainingFunction(View view) {
        startActivities(new Intent[]{new Intent(this, FunctionalTrainingWo.class)});
    }

    public void calisthenicsFunction(View view) {
        startActivities(new Intent[]{new Intent(this, CalisthenicsWo.class)});
    }

    public void backFunction(View view) {
        finish();
    }
}