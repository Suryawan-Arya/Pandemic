package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddTestKitActivity extends AppCompatActivity {

   private String URL_ADD_TEST_KIT = "https://pandemic-bit302.000webhostapp.com/addTestKit.php";
   private EditText testKitNameInput, testKitAmountInput;
   private String testCenterId = Users.testCenterID;
   private String testKitName, testKitAmount;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_add_test_kit);

      testKitNameInput = findViewById(R.id.testKitNameInput);
      testKitAmountInput = findViewById(R.id.testKitAmountInput);
   }

   public void addTestKit(final String testCenterId, final String testKitName, final String testKitAmount){
      if (!testKitName.isEmpty() && !testKitAmount.isEmpty()) {
         StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ADD_TEST_KIT,
                 new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       System.out.println("----------> " + response);
                       try{
                          JSONObject jsonObject = new JSONObject(response);
                          Toast.makeText(getApplicationContext(), "New Test Kit Added!!", Toast.LENGTH_LONG).show();
                       }catch (JSONException e){
                          e.printStackTrace();
                          Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_LONG).show();
                       }
                    }
                 },
                 new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
                    }
                 }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("testCenterID", testCenterId);
               params.put("testKitName", testKitName);
               params.put("stock", testKitAmount);
               return params;
            }
         };
         RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
         requestQueue.add(stringRequest);
      }else{
         if (testKitName.isEmpty()){
            testKitNameInput.setError("Test Kit Name Can't be Empty!!");
            Toast.makeText(getApplicationContext(), "Test Kit Name Can't be Empty", Toast.LENGTH_LONG).show();
         }else if (testKitAmount.isEmpty()){
            testKitAmountInput.setError("Test Kit Amount Can't be Empty!!");
            Toast.makeText(getApplicationContext(), "Test Kit Amount Can't be Empty", Toast.LENGTH_LONG).show();
         }else{
            testKitNameInput.setError("Test Kit Name Can't be Empty!!");
            testKitAmountInput.setError("Test Kit Amount Can't be Empty!!");
            Toast.makeText(getApplicationContext(), "Test Kit Name and Amount Can't be Empty", Toast.LENGTH_LONG).show();
         }
      }

   }

   public void Back(View view) {
      Intent intent = new Intent(getApplicationContext(),ManageTestKitActivity.class);
      startActivity(intent);
   }

   public void Add(View view) {
      testKitName = testKitNameInput.getText().toString();
      testKitAmount = testKitAmountInput.getText().toString();
      addTestKit(testCenterId, testKitName, testKitAmount);
      testKitNameInput.setText("");
      testKitAmountInput.setText("");
      Intent intent = new Intent(getApplicationContext(),ManageTestKitActivity.class);
      startActivity(intent);
   }
}