package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TesterMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester_menu);
    }

   public void UpdateTestResult(View view) {
      Intent intent = new Intent(getApplicationContext(),FindTestActivity.class);
      startActivity(intent);
   }

    public void RecordTest(View view) {
        Intent intent = new Intent(getApplicationContext(),RecordTestMenuActivity.class);
        startActivity(intent);
    }
}