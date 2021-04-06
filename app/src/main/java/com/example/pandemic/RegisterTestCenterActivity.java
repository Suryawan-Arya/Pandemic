package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterTestCenterActivity extends AppCompatActivity {

    String testCenterName;
    EditText testCenterNameTx;

    TestCenter testCenter = new TestCenter();

    String testManagerID = Users.testManagerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_test_center);

        testCenterNameTx = findViewById(R.id.testCenterNameTxt);
    }

    public void RegisterTestCenter(View view) {
        testCenterName = testCenterNameTx.getText().toString().trim();

        //testCenter.registerTestCenter(getApplicationContext(),testCenterName,testManagerID);
        testCenter.validation(getApplicationContext(),testCenterName,testManagerID);
    }
}