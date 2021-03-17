package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText userNameText,nameText,emailText,passwordText,confirmPassText;
    private String userName,name,email,password,confirmPass;

    Users users = new Users();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameText    = findViewById(R.id.UserName);
        nameText        = findViewById(R.id.Name);
        emailText       = findViewById(R.id.Email);
        passwordText    = findViewById(R.id.Password);
        confirmPassText = findViewById(R.id.ConfirmPass);

    }
}
