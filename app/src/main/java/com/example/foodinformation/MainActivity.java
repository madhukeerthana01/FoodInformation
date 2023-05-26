package com.example.foodinformation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText mobileEditText;
    private EditText emailEditText;
    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        emailEditText = findViewById(R.id.emailEditText);
        enterButton = findViewById(R.id.enterButton);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String mobile = mobileEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) || TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    nameEditText.setError("Name is required.");
                    nameEditText.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(mobile)) {
                    mobileEditText.setError("Mobile number is required.");
                    mobileEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Email is required.");
                    emailEditText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEditText.setError("Invalid email format.");
                    emailEditText.requestFocus();
                    return;
                }

                if (mobile.length() != 10) {
                    mobileEditText.setError("Invalid mobile number.");
                    mobileEditText.requestFocus();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                intent.putExtra("Name", name);
                intent.putExtra("Email", email);
                intent.putExtra("Mobile", mobile);
                startActivity(intent);
                finish();
            }
        });
    }
}

