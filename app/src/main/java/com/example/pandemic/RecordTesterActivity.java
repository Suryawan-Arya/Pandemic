package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RecordTesterActivity extends AppCompatActivity {
    private String testCenterId = Users.testCenterID;
    private Users users = new Users();
    private EditText testerUserNameInput,testerNameInput,testerInputEmail,testerInputPassword,testerInputConfirmPassword;
    private String userName,name,email,password,confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_tester);

        testerUserNameInput = findViewById(R.id.testerUserNameInput);
        testerNameInput = findViewById (R.id.testerNameInput);
        testerInputEmail = findViewById (R.id.testerInputEmail);
        testerInputPassword = findViewById (R.id.testerInputPassword);
        testerInputConfirmPassword = findViewById (R.id.testerInputConfirmPassword);
    }

    public void AddTester(View view) {
        userName = testerUserNameInput.getText().toString();
        name = testerNameInput.getText().toString();
        email = testerInputEmail.getText().toString();
        password = testerInputPassword.getText().toString();
        confirmPass = testerInputConfirmPassword.getText().toString();

        if (userName.isEmpty() ){
            testerUserNameInput.setError("User Name Can't be Empty");
        }else if (name.isEmpty()){
            testerNameInput.setError("Name Can't be Empty");
        }else if (email.isEmpty()){
            testerInputEmail.setError("Email Can't be Empty");
        }else if (password.isEmpty()){
            testerInputPassword.setError("Password Can't be Empty");
        }else if (confirmPass.isEmpty()){
            testerInputConfirmPassword.setError("Confirm Password Can't be Empty");
        }else if (userName.isEmpty() && name.isEmpty() && email.isEmpty() && password.isEmpty() && confirmPass.isEmpty()){

            testerUserNameInput.setError("Username cannot be empty");
            testerNameInput.setError("Name cannot be empty");
            testerInputEmail.setError("Email cannot be empty");
            testerInputPassword.setError("Password cannot be empty");
            testerInputConfirmPassword.setError("Conform Password cannot be empty");

        }else {
            if (password.equals(confirmPass)){
                users.recordTester(getApplicationContext(),userName,testCenterId,name,email,password);
            }else{
                testerInputConfirmPassword.setError("Password does not match");
            }
        }
    }
}
