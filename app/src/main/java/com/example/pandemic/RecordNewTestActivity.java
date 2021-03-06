package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RecordNewTestActivity extends AppCompatActivity {
   private EditText userNameInput, nameInput, passwordInput, confirmPasswordInput, symptompsInput;
   private String userName, name, patientType, testDate, password, confirmPassword, symptoms;
   private Spinner patientTypeSp;
   private ArrayList<String> patientTypeArray = new ArrayList<>();
   private String testCenterID = Users.testCenterID;
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
      patientTypeSp = findViewById(R.id.patientTypeSp);

      patientTypeArray.add("-");
      patientTypeArray.add("Returnee");
      patientTypeArray.add("Quarantine");
      patientTypeArray.add("Close Contact");
      patientTypeArray.add("Infected");
      patientTypeArray.add("Suspected");

      ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,patientTypeArray);
      arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      patientTypeSp.setAdapter(arrayAdapter);

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
      String date = simpleDateFormat.format(Calendar.getInstance().getTime());
      testDate = date;


      patientTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            patientType = parent.getItemAtPosition(position).toString();
         }
         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
      });
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
      }else if(patientType.isEmpty()){
         Toast.makeText(getApplicationContext(), "Select Patient Type First!!", Toast.LENGTH_LONG).show();
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
         if (patientType.equals("-")){
            if (password.equals(confirmPassword)){
               users.recordNewTest(getApplicationContext(),userName,testCenterID,name,symptoms,patientType,password,testDate);
            }else{
               confirmPasswordInput.setError("Password does not match");
            }
         }else{
            Toast.makeText(getApplicationContext(), "Select Patient Type !!!! ", Toast.LENGTH_LONG).show();
         }
      }
   }
}