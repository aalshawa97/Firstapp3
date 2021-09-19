package com.abdul.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView resTextView;
    EditText etEmail;
    // Remote Config keys
    private static final String LOADING_PHRASE_CONFIG_KEY = "loading_phrase";
    private static final String WELCOME_MESSAGE_KEY = "welcome_message";
    private static final String WELCOME_MESSAGE_CAPS_KEY = "welcome_message_caps";

    //private FirebaseRemoteConfig mFirebaseRemoteConfig;
    //private ActivityMainBinding mBinding;
    private TextView mWelcomeTextView;

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