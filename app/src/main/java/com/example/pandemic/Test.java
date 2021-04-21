package com.example.pandemic;

import android.content.Context;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class Test {

   private String testID, testCenterID, userID, patientName, testDate, result, resultDate, testStatus;
   private String URL_UPDATE_TEST_DATA = "https://pandemic-bit302.000webhostapp.com/updateTestData.php";
   private String URL_TEST_DATA = "https://pandemic-bit302.000webhostapp.com/testData.php";
   private String URL_UPDATE_TEST = "https://pandemic-bit302.000webhostapp.com/updateTest.php";
   public static String resultID;

   public Test() {
   }

   public Test(String testID, String testCenterID, String userID,String patientName , String testDate, String result, String resultDate, String testStatus) {
      this.testID = testID;
      this.testCenterID = testCenterID;
      this.userID = userID;
      this.patientName = patientName;
      this.testDate = testDate;
      this.result = result;
      this.resultDate = resultDate;
      this.testStatus = testStatus;
   }

   public String getPatientName() {
      return patientName;
   }

   public void setPatientName(String patientName) {
      this.patientName = patientName;
   }

   public String getTestID() {
      return testID;
   }

   public void setTestID(String testID) {
      this.testID = testID;
   }

   public String getTestCenterID() {
      return testCenterID;
   }

   public void setTestCenterID(String testCenterID) {
      this.testCenterID = testCenterID;
   }

   public String getUserID() {
      return userID;
   }

   public void setUserID(String userID) {
      this.userID = userID;
   }

   public String getTestDate() {
      return testDate;
   }

   public void setTestDate(String testDate) {
      this.testDate = testDate;
   }

   public String getResult() {
      return result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public String getResultDate() {
      return resultDate;
   }

   public void setResultDate(String resultDate) {
      this.resultDate = resultDate;
   }

   public String getTestStatus() {
      return testStatus;
   }

   public void setTestStatus(String testStatus) {
      this.testStatus = testStatus;
   }

   public void findTestResult(final Context context, final String testID){
      StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TEST_DATA,
              new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                    try{
                       JSONObject jsonObject = new JSONObject(response);
                       JSONArray testResultArray = jsonObject.getJSONArray("testResult");
                       for (int i = 0; i < testResultArray.length(); i++){
                          JSONObject testResultObj = testResultArray.getJSONObject(i);
                          if (testResultObj.getString("resultID").equals(testID)){
                             Intent intent = new Intent(context,UpdateTestResultActivity.class);
                             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             resultID = testResultObj.getString("resultID");
                             context.startActivity(intent);
                          }
                       }
                    }catch (JSONException e){
                       e.printStackTrace();
                       Toast.makeText(context, "Test Not Found", Toast.LENGTH_LONG).show();
                    }
                 }
              },
              new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Connection Error", Toast.LENGTH_LONG).show();
                 }
              });
      RequestQueue requestQueue = Volley.newRequestQueue(context);
      requestQueue.add(stringRequest);
   }

   public void updateData(final Context context, final String testID, final String result, final String today){
      StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_TEST_DATA,
              new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                    System.out.println("=============>" + response);
                    try{
                       JSONObject jsonObject = new JSONObject(response);
                       Toast.makeText(context, "Update Success", Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(context,FindTestActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       context.startActivity(intent);
                    }catch (JSONException e){
                       e.printStackTrace();
                       Toast.makeText(context, "Update Fail", Toast.LENGTH_LONG).show();
                    }
                 }
              },
              new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Connection Error", Toast.LENGTH_LONG).show();
                 }
              }){
         @Override
         protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("ID", testID);
            params.put("result", result);
            params.put("resultDate", today);
            return params;
         }
      };
      RequestQueue requestQueue = Volley.newRequestQueue(context);
      requestQueue.add(stringRequest);
   }

   public void updateTest (final Context context, final String userID, final String patientType, final String symptoms){
      StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_TEST,
              new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                     System.out.println("Update Text Response =============>"+ response);
                     try {
                        JSONObject jsonObject= new JSONObject(response);
                        Toast.makeText(context, "Update Success", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context,RecordTestMenuActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                     }
                     catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(context,   "Update Fail", Toast.LENGTH_LONG).show();

                     }
                 }

              },
              new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,  "Connection Error", Toast.LENGTH_LONG).show();
                 }
              }){
         @Override
         protected  Map<String, String> getParams() throws AuthFailureError{
            Map<String, String> params = new HashMap<>();
            params.put("id", userID);
            params.put("patientType",patientType);
            params.put("symptoms", symptoms);
            return params;

         }
      };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
   }
}
