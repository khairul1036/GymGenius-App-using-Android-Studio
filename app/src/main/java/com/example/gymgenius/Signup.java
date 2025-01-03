package com.example.gymgenius;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    private EditText signupUsernameEditTextObj, signupEmailEditTextObj, signupPasswordEditTextObj, signupConfirmPasswordEditTextObj;
    private Button signupBtnObj;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = FirebaseFirestore.getInstance();

        signupUsernameEditTextObj = findViewById(R.id.signupUsernameEditTextId);
        signupEmailEditTextObj = findViewById(R.id.signupEmailEditTextId);
        signupPasswordEditTextObj = findViewById(R.id.signupPasswordEditTextId);
        signupConfirmPasswordEditTextObj = findViewById(R.id.signupConfirmPasswordEditTextId);
        signupBtnObj = findViewById(R.id.signupBtnID);

        signupBtnObj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = signupUsernameEditTextObj.getText().toString();
        String email = signupEmailEditTextObj.getText().toString().trim();
        String password = signupPasswordEditTextObj.getText().toString().trim();
        String confirmPassword = signupConfirmPasswordEditTextObj.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupEmailEditTextObj.setError("Enter a valid email address");
            signupEmailEditTextObj.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new user with a generated unique ID
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("email", email);
        user.put("password", password);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        // Redirect to login or any other activity
                        startActivity(new Intent(Signup.this, LoginActivity.class));
                        finish(); // Close the signup activity
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Signup.this, "Error registering user: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
