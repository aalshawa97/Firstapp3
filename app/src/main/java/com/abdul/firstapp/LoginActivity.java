package com.abdul.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView resTextView;
    EditText etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        resTextView = findViewById(R.id.tvRes);
        etEmail = findViewById(R.id.etEmail);
    }

    public void setTextview(View view) {
        String email = etEmail.getText().toString();
        resTextView.setText(email);
    }
}