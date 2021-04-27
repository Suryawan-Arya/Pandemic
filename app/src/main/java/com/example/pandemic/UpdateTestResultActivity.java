package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateTestResultActivity extends AppCompatActivity {

   private String URL_TEST_DATA = "https://pandemic-bit302.000webhostapp.com/testData.php";
   private String URL_UPDATE_TEST_DATA = "https://pandemic-bit302.000webhostapp.com/updateTestData.php";
   private TextView testIdTv, patientNameTv, symptomsTv, patientTypeTv, testResultTv;
   private EditText resultInput;
   private TextInputLayout resultLayout;
   private String testID = Test.resultID;
   private Test test = new Test();
   private String today;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_update_test_result);

      testIdTv = findViewById(R.id.testIDTv);
      patientNameTv = findViewById(R.id.patientNameTv);
      symptomsTv = findViewById(R.id.symptomtsTv);
      patientTypeTv = findViewById(R.id.patientTypeTv);
      testResultTv = findViewById(R.id.testResultTv);
      resultInput = findViewById(R.id.resultInput);
      resultLayout = findViewById(R.id.resultLayout);
      getData();

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
      String date = simpleDateFormat.format(Calendar.getInstance().getTime());
      today = date;
      System.out.println("------------> Today " + today);
   }

   public void getData(){
      StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TEST_DATA,
              new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                    System.out.println("=============>" + response);
                    try{
                       JSONObject jsonObject = new JSONObject(response);
                       JSONArray testResultArray = jsonObject.getJSONArray("testResult");
                       for (int i = 0; i < testResultArray.length(); i++){
                          JSONObject testResultObj = testResultArray.getJSONObject(i);
                          if(testResultObj.getString("resultID").equals(testID)){
                             testIdTv.setText(testResultObj.getString("resultID"));
                             patientNameTv.setText(testResultObj.getString("name"));
                             symptomsTv.setText(testResultObj.getString("symptoms"));
                             patientTypeTv.setText(testResultObj.getString("patientType"));
                             testResultTv.setVisibility(View.VISIBLE);
                             if (testResultObj.getString("testStatus").equals("Complete")){
                                resultInput.setVisibility(View.GONE);
                                resultLayout.setVisibility(View.GONE);
                                testResultTv.setText(testResultObj.getString("result"));
                             }else{
                                resultLayout.setVisibility(View.VISIBLE);
                                resultInput.setVisibility(View.VISIBLE);
                                testResultTv.setVisibility(View.GONE);
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
      Intent intent = new Intent(getApplicationContext(),FindTestActivity.class);
      startActivity(intent);
   }

   public void Update(View view) {
      String testID = testIdTv.getText().toString();
      String result = resultInput.getText().toString();
      if (!result.isEmpty()){
         test.updateData(getApplicationContext(), testID, result, today);
      }else {
         resultInput.setError("Result Can't be Empty!!");
      }
   }
}