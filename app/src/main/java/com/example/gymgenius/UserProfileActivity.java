package com.example.gymgenius;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    private ImageView profilePicture;
    private TextView userName;
    private TextView userEmail;
    private Button editButton;
    private Button logoutButton;

    // SharedPreferences key
    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_EMAIL = "user_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize views
        profilePicture = findViewById(R.id.profile_picture);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        editButton = findViewById(R.id.edit_button);
        logoutButton = findViewById(R.id.logout_button);

        // Set user information from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String savedUserName = prefs.getString(KEY_USER_NAME, "Khairul Islam");
        String savedUserEmail = prefs.getString(KEY_USER_EMAIL, "khairul@gmail.com");
        userName.setText(savedUserName);
        userEmail.setText(savedUserEmail);

        // Set click listeners
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditProfileDialog();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void showEditProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_profile, null);
        builder.setView(dialogView);

        final EditText editUserName = dialogView.findViewById(R.id.edit_user_name);
        final EditText editUserEmail = dialogView.findViewById(R.id.edit_user_email);
        Button saveButton = dialogView.findViewById(R.id.save_button);

        // Set current user information in the edit text fields
        editUserName.setText(userName.getText().toString());
        editUserEmail.setText(userEmail.getText().toString());

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // Set click listener for the save button in the dialog
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input
                String newUserName = editUserName.getText().toString();
                String newUserEmail = editUserEmail.getText().toString();
                if (isValidInput(newUserName, newUserEmail)) {
                    // Save edited profile information
                    userName.setText(newUserName);
                    userEmail.setText(newUserEmail);

                    // Save changes to SharedPreferences
                    saveUserData(newUserName, newUserEmail);

                    // Dismiss the dialog
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(UserProfileActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidInput(String userName, String userEmail) {
        return !userName.trim().isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches();
    }

    private void saveUserData(String userName, String userEmail) {
        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_EMAIL, userEmail);
        editor.apply();
    }

    private void logout() {
        // Clear SharedPreferences
        getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit().clear().apply();

        // Show logout message
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();

        // Navigate to login screen
        Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close current activity
    }

    public void backFunction(View view) {
        finish();
    }
}
