package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class RecordNewTestActivity extends AppCompatActivity {
   private EditText userNameInput, nameInput, passwordInput, confirmPasswordInput, symptompsInput;
   private String userName, name, password, confirmPassword, symptoms;
   private Spinner patientTypeSp;
   private ArrayList<String> patientTypeArray = new ArrayList<>();
   private Users users = new Users();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_record_new_test);

      userNameInput  = findViewById(R.id.userNameInput);
      nameInput      = findViewById(R.id.nameInput);
      passwordInput  = findViewById(R.id.passwordInput);
      confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
      symptompsInput = findViewById(R.id.symptompsInput);
   }

   public void AddNewTest(View view) {
      userName           = userNameInput.getText().toString();
      name               = nameInput.getText().toString();
      password           = passwordInput.getText().toString();
      confirmPassword    = confirmPasswordInput.getText().toString();
      symptoms           = symptompsInput.getText().toString();

      if (userName.isEmpty() ){
         userNameInput.setError("User Name Can't be Empty");
      }else if (name.isEmpty()){
         nameInput.setError("Name Can't be Empty");
      }else if (password.isEmpty()){
         passwordInput.setError("Password Can't be Empty");
      }else if (confirmPassword.isEmpty()){
         confirmPasswordInput.setError("Confirm Password Can't be Empty");
      }else if (symptoms.isEmpty()){
         symptompsInput.setError(" Symptoms Can't be Empty");
      }else if (userName.isEmpty() && name.isEmpty() && password.isEmpty() && confirmPassword.isEmpty () && symptoms.isEmpty()){
         userNameInput.setError("Username cannot be empty");
         nameInput.setError("Name cannot be empty");
         passwordInput.setError("Password cannot be empty");
         passwordInput.setError("Confirm Password cannot be empty");
         symptompsInput.setError("Symptoms cannot be empty");
      }else {
         if (password.equals(confirmPassword)){
            //users.recordnewTest(getApplicationContext(),userName,testCenterId,name,email,password);
         }else{
            confirmPasswordInput.setError("Password does not match");
         }
      }
   }
}