package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        editTextEmail = findViewById(R.id.email_input);
        editTextPassword = findViewById(R.id.password_input);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate user input
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Authenticate user with Firebase Authentication
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    checkUserInDatabase(user.getUid());
                                }
                            } else {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        });
            }
        });
    }

    private void checkUserInDatabase(String userId) {
        db.collection("users").document(userId).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // User exists in the database, proceed to next activity
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            // User does not exist in the database
                            Toast.makeText(LoginActivity.this, "User not found in database", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Error checking user in database: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void goSignupFunction(View view) {
        startActivity(new Intent(LoginActivity.this, Signup.class));
    }
}
