package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class UpdateTestActivity extends AppCompatActivity {
   private TextView patientNameTextView;
   private EditText symptopmsInputEditText;
   private String name,patientType,symptoms;
   private Spinner patientTypeSpinner;
   private ArrayList<String> patientTypeArray = new ArrayList<>();




   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_update_test);

      patientTypeArray.add("-");
      patientTypeArray.add("Returnee");
      patientTypeArray.add("Quarantine");
      patientTypeArray.add("Close Contact");
      patientTypeArray.add("Infected");
      patientTypeArray.add("Suspected");

      patientNameTextView  = findViewById(R.id.patientNameTextView);
      symptopmsInputEditText = findViewById(R.id.symptompsInput);
      patientTypeSpinner = findViewById(R.id.patientTypeSpinner);

      ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,patientTypeArray);
      arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      patientTypeSpinner.setAdapter(arrayAdapter);

      patientTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             patientType = parent.getItemAtPosition(position).toString();

         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
      }) ;

   }


   public void UpdateTest(View view){

   }
}