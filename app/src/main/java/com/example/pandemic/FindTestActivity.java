package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FindTestActivity extends AppCompatActivity {

   private String URL_TEST_DATA = "https://pandemic-bit302.000webhostapp.com/testData.php";
   private EditText testIdInput;
   private String testId;
   private Test test = new Test();
   public static String resultID;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_find_test);

      testIdInput = findViewById(R.id.testIdInput);
   }

//   public void findTestResult(final String testID){
//      StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TEST_DATA,
//              new Response.Listener<String>() {
//                 @Override
//                 public void onResponse(String response) {
//                    try{
//                       JSONObject jsonObject = new JSONObject(response);
//                       JSONArray testResultArray = jsonObject.getJSONArray("testResult");
//                       for (int i = 0; i < testResultArray.length(); i++){
//                          JSONObject testResultObj = testResultArray.getJSONObject(i);
//                          if (testResultObj.getString("resultID").equals(testID)){
//                             resultID = testResultObj.getString("resultID");
//                             Intent intent = new Intent(getApplicationContext(),UpdateTestResultActivity.class);
//                             startActivity(intent);
//                          }
//                       }
//                    }catch (JSONException e){
//                       e.printStackTrace();
//                       Toast.makeText(getApplicationContext(), "Test Not Found", Toast.LENGTH_LONG).show();
//                    }
//                 }
//              },
//              new Response.ErrorListener() {
//                 @Override
//                 public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
//                 }
//              });
//      RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//      requestQueue.add(stringRequest);
//   }

   public void Back(View view) {
      Intent intent = new Intent(getApplicationContext(),TesterMenuActivity.class);
      startActivity(intent);
   }

   public void findTest(View view) {
      testId = testIdInput.getText().toString();
      if (!testId.isEmpty()){
         test.findTestResult(getApplicationContext(),testId);
      }else{
         testIdInput.setError("Test Kit ID Can't be Empty!!");
      }
   }
}