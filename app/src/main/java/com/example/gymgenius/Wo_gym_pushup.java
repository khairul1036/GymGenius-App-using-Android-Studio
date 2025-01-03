package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Wo_gym_pushup extends AppCompatActivity {

    EditText duration_input, repetitionCount;
    TextView timerTextView, repeateView;
    int timeInSeconds, repeatCount, currentRepeat;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wo_gym_pushup);

        duration_input = findViewById(R.id.duration_input);
        repetitionCount = findViewById(R.id.repetitionCountBtnId);

        timerTextView = findViewById(R.id.countdown_timer);
        repeateView = findViewById(R.id.displayrepetitionCountBtnId);
    }

    private void startTimer() {
        timer = new CountDownTimer(timeInSeconds * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;

                // Format the time as HH:MM:SS
                String timeLeft = String.format("%02d:%02d:%02d", hours % 24, minutes % 60, seconds % 60);
                timerTextView.setText(timeLeft);
            }

            public void onFinish() {
                currentRepeat++;
                repeateView.setText("" + currentRepeat);

                if (currentRepeat < repeatCount) {
                    startTimer(); // Start the timer again for the next repeat
                } else {
                    timerTextView.setText("00:00:00");
                }
            }
        }.start();
    }

    public void startFunction(View view) {
        // Parse EditText values when the timer starts
        timeInSeconds = Integer.parseInt(duration_input.getText().toString());
        repeatCount = Integer.parseInt(repetitionCount.getText().toString());

        // Start timer
        startTimer();
    }

    public void resetFunction(View view) {
        // Cancel the current timer
        if (timer != null) {
            timer.cancel();
        }

        // Reset TextViews
        timerTextView.setText("00:00:00");
        repeateView.setText("0");

        // Reset variables
        currentRepeat = 0;
    }

    public void backFunction(View view) {
        finish();
    }

    public void profileFunction(View view) {
        startActivities(new Intent[]{new Intent(this, UserProfileActivity.class)});
    }
}