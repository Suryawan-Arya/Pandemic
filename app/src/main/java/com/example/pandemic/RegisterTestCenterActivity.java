package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterTestCenterActivity extends AppCompatActivity {

    String testCenterName;
    EditText testCenterNameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_test_center);

        testCenterNameTxt.findViewById(R.id.testCenterNameTxt);
    }

    public void RegisterTestCenter(View view) {

    }
}