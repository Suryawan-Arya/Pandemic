package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class TestDetailActivity extends AppCompatActivity {

   private String URL_TEST_DATA = "https://pandemic-bit302.000webhostapp.com/testData.php";
   private TextView testIDTv, patientNameTv, symptomTv, patientTypeTv, statusTv, resultTv, testDateTv, resultDateTv;
   private String testID, patientName, symptom, patientType, status, result, testDate, resultDate;
   private String userType = Users.userType;
   private String userID = Users.testManagerID;
   private String testCenterID = Users.testCenterID;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_test_detail);

      testIDTv = findViewById(R.id.testIdDetailTv);
      patientNameTv = findViewById(R.id.patientNameDetailTv);
      symptomTv = findViewById(R.id.symptomDetailTv);
      patientTypeTv = findViewById(R.id.patinetTypeDetailTv);
      statusTv = findViewById(R.id.statusDetailTv);
      resultTv = findViewById(R.id.resultDetailTv);
      testDateTv = findViewById(R.id.testDateDetailTv);
      resultDateTv = findViewById(R.id.resultDateDetailTv);

      displayData();
   }

   public void displayData(){
      StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TEST_DATA,
              new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                    System.out.println("Display Data -----> " + response);
                    try{
                       JSONObject jsonObject = new JSONObject(response);
                       JSONArray testResultArray = jsonObject.getJSONArray("testResult");
                       for (int i = 0; i < testResultArray.length(); i++){
                          JSONObject testResultObj = testResultArray.getJSONObject(i);
                          if(userType.equals("Tester") || userType.equals("Manager")){
                             if (testResultObj.getString("testCenterID").equals(testCenterID)){
                                testIDTv.setText(testResultObj.getString("resultID"));
                                patientNameTv.setText(testResultObj.getString("name"));
                                symptomTv.setText(testResultObj.getString("symptoms"));
                                patientTypeTv.setText(testResultObj.getString("patientType"));
                                statusTv.setText(testResultObj.getString("testStatus"));
                                resultTv.setText(testResultObj.getString("result"));
                                testDateTv.setText(testResultObj.getString("testDate"));
                                resultDateTv.setText(testResultObj.getString("resultDate"));
                             }
                          }else{
                             if (testResultObj.getString("userID").equals(userID)){
                                testIDTv.setText(testResultObj.getString("resultID"));
                                patientNameTv.setText(testResultObj.getString("name"));
                                symptomTv.setText(testResultObj.getString("symptoms"));
                                patientTypeTv.setText(testResultObj.getString("patientType"));
                                statusTv.setText(testResultObj.getString("testStatus"));
                                resultTv.setText(testResultObj.getString("result"));
                                testDateTv.setText(testResultObj.getString("testDate"));
                                resultDateTv.setText(testResultObj.getString("resultDate"));
                             }
                          }
                       }
                    }catch (JSONException e){
                       e.printStackTrace();
                       Toast.makeText(getApplicationContext(), "Test Not Found", Toast.LENGTH_LONG).show();
                    }
                 }
              },
              new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
                 }
              });
      RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
      requestQueue.add(stringRequest);
   }

   public void Back(View view) {
      if (userType.equals("Tester") || userType.equals("Manager")){
         Intent intent = new Intent(getApplicationContext(),TesterMenuActivity.class);
         startActivity(intent);
      }else {
         Intent intent = new Intent(getApplicationContext(),PatientMenuActivity.class);
         startActivity(intent);
      }
   }
}