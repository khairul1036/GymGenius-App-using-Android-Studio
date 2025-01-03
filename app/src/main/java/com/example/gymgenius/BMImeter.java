package com.example.gymgenius;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class BMImeter extends AppCompatActivity {

    EditText heightEditText, weightEditText, ageEditText;
    TextView bmiResult;
    Double height, weight, age, result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmimeter);

        ageEditText = findViewById(R.id.ageBtnId);
        heightEditText = findViewById(R.id.heightBtnId);
        weightEditText = findViewById(R.id.weightBtnId);

        bmiResult = findViewById(R.id.bmiResultId);

    }
    public void bmiCalculationFunction(View view) {
        age = Double.parseDouble(ageEditText.getText().toString());
        height = Double.parseDouble(heightEditText.getText().toString());
        weight = Double.parseDouble(weightEditText.getText().toString());
        result = (weight/(height*height))*10000;
        if(result<18.5){
            String displayResult = String.format("%.2f",result);
            bmiResult.setText("BMI = " + displayResult + " kg/m2 (UnderWeight)");
        }
        else if(result>=18.5 && result<=24.9){
            String displayResult = String.format("%.2f",result);
            bmiResult.setText("BMI = " + displayResult + " kg/m2 (Nomal)");
        }
        else if(result>=25 && result<=29.9){
            String displayResult = String.format("%.2f",result);
            bmiResult.setText("BMI = " + displayResult + " kg/m2 (OverWeight)");
        }
        else if(result>=30 && result<=34.9){
            String displayResult = String.format("%.2f",result);
            bmiResult.setText("BMI = " + displayResult + " kg/m2 (Obese)");
        }
        else{
            String displayResult = String.format("%.2f",result);
            bmiResult.setText("BMI = " + displayResult + " kg/m2 (ExtremelyObese)");
        }

    }

    public void bmibackFunction(View view) {
        finish();
    }

    public void profileFunction(View view) {
        startActivities(new Intent[]{new Intent(this, UserProfileActivity.class)});
    }
}