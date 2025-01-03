package com.example.gymgenius;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Sub_Stretching extends AppCompatActivity {

    private EditText repetitionsInput, setsInput, weightInput, notesInput, durationInput;
    private Button startButton, resetButton, saveButton;
    private TextView countdownTimer;
    private CountDownTimer timer;
    private MediaPlayer mediaPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_stretching);

        repetitionsInput = findViewById(R.id.repetitions_input);
        setsInput = findViewById(R.id.sets_input);
        weightInput = findViewById(R.id.weight_input);
        notesInput = findViewById(R.id.notes_input);
        durationInput = findViewById(R.id.duration_input);
        startButton = findViewById(R.id.start_button);
        resetButton = findViewById(R.id.reset_button);
        saveButton = findViewById(R.id.save_button);
        countdownTimer = findViewById(R.id.countdown_timer);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExercise();
            }
        });

        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer.create(Sub_Stretching.this, R.raw.alarm); //set audio music path

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
    }

    private void startTimer() {
        int durationInSeconds = Integer.parseInt(durationInput.getText().toString());
        timer = new CountDownTimer(durationInSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimerDisplay(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                updateTimerDisplay(0);
                playTimeUpSound();
            }
        }.start();
    }

    private void resetTimer() {
        if (timer != null) {
            timer.cancel(); // Cancel the current timer
            updateTimerDisplay(0); // Reset timer display
        }
    }

    private void updateTimerDisplay(long millisUntilFinished) {
        long seconds = millisUntilFinished / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;

        // Format the time as HH:MM:SS
        String timeLeft = String.format("%02d:%02d:%02d", hours % 24, minutes % 60, seconds % 60);

        countdownTimer.setText(timeLeft);
    }

    private void playTimeUpSound() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    private void saveExercise() {
        int repetitions = Integer.parseInt(repetitionsInput.getText().toString());
        int sets = Integer.parseInt(setsInput.getText().toString());
        int weight = Integer.parseInt(weightInput.getText().toString());
        String notes = notesInput.getText().toString();

        Exercise exercise = new Exercise(repetitions, sets, weight, notes);

        DatabaseReference exercisesRef = FirebaseDatabase.getInstance().getReference("exercises");

        // Generate a unique integer key
        Random random = new Random();
        int exerciseId = random.nextInt(1000000); // Change 1000000 to any desired range

        exercisesRef.child(String.valueOf(exerciseId)).setValue(exercise)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showSuccessToast();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showErrorToast();
                    }
                });
    }
    private void showSuccessToast() {
        Toast.makeText(this, "Exercise saved successfully!", Toast.LENGTH_SHORT).show();
    }

    private void showErrorToast() {
        Toast.makeText(this, "Failed to save exercise. Please try again.", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void profileFunction(View view) {
        startActivities(new Intent[]{new Intent(this, UserProfileActivity.class)});
    }
}
