package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private EditText userNameText,nameText,emailText,passwordText,confirmPassText;
    private String userName,name,email,password,confirmPass;

    Users users = new Users();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameText = findViewById(R.id.UserName);
        nameText = findViewById(R.id.Name);
        emailText = findViewById(R.id.Email);
        passwordText = findViewById(R.id.Password);
        confirmPassText = findViewById(R.id.ConfirmPass);

    }


    public void Register(View view) {
        userName = userNameText.getText().toString().trim();
        name = nameText.getText().toString().trim();
        email = emailText.getText().toString().trim();
        password = passwordText.getText().toString().trim();
        confirmPass = confirmPassText.getText().toString().trim();

        if (userName.isEmpty() && name.isEmpty() && email.isEmpty() && password.isEmpty() && confirmPass.isEmpty()){
            userNameText.setError("User Name Can't be Empty");
            nameText.setError("Name Can't be Empty");
            emailText.setError("Email Can't be Empty");
            passwordText.setError("Password Can't be Empty");
            confirmPassText.setError("Confirm Password Can't be Empty");
        }else if (userName.isEmpty()){
            userNameText.setError("User Name Can't be Empty");
        }else if (name.isEmpty()){
            nameText.setError("Name Can't be Empty");
        }else if (email.isEmpty()){
            emailText.setError("Email Can't be Empty");
        }else if (password.isEmpty()){
            passwordText.setError("Password Can't be Empty");
        }else if (confirmPass.isEmpty()){
            confirmPassText.setError("Confirm Password Can't be Empty");
        }else {
            if (password.equals(confirmPass)){
                users.register(getApplicationContext(),userName,name,email,password);
            }else{
                confirmPassText.setError("Invalid Input");
            }
        }

    }

    public void Back(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
