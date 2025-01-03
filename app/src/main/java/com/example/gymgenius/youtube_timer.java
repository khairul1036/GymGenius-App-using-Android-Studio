package com.example.gymgenius;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class youtube_timer extends AppCompatActivity {

    private TextView countdownTimer;
    private EditText durationInput;
    private Button startButton;
    private Button resetButton;
    private CountDownTimer timer;
    private MediaPlayer mediaPlayer;

    // Inside the onCreate() method or any other method where you want to extract the value


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activity Conncetion
        int defaultValue = 0;
        // Extracting value from the intent
        int value = defaultValue;
        Intent intent = getIntent();
        if (intent != null) {
            value = intent.getIntExtra("key", defaultValue);
        }

        // Set content view based on the extracted value
        if (value == 1) {
            setContentView(R.layout.bw_tuck_front_level);
        } else if (value == 2) {
            setContentView(R.layout.bw_squats);
        }else if (value == 3) {
            setContentView(R.layout.bw_crunch);
        }else if (value == 4) {
            setContentView(R.layout.bw_dips);
        }else if (value == 5) {
            setContentView(R.layout.bw_pull_ups);
        }else if (value == 6) {
            setContentView(R.layout.bw_push_ups);
        }else if (value == 7) {
            setContentView(R.layout.bw_muscle_ups);
        }else if (value == 8) {
            setContentView(R.layout.bw_half_burpee);
        }

        countdownTimer = findViewById(R.id.countdown_timer);
        durationInput = findViewById(R.id.duration_input);
        startButton = findViewById(R.id.start_button);
        resetButton = findViewById(R.id.reset_button);

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "S0Q4gqBUs7c";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

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

        // Initialize the MediaPlayer
        mediaPlayer = MediaPlayer.create(youtube_timer.this, R.raw.alarm); //set audio music path
    }

    private void startTimer() {
        int durationInSeconds = Integer.parseInt(durationInput.getText().toString());
        timer = new CountDownTimer(durationInSeconds * 1000, 1000) { // Convert seconds to milliseconds
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimerDisplay(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                updateTimerDisplay(0);
                playTimeUpSound();
            }
        }.start(); // Start the timer
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void backFunction(View view) {
        finish();
    }
}
