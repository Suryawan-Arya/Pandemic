package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;

public class RecordTestMenuActivity extends AppCompatActivity {
   RecyclerView testList;
   RecyclerView.LayoutManager layoutManager;
   private String testCenterId = Users.testCenterID;
   private ArrayList<Test> testArray = new ArrayList<>();

   private String TEST_DATA_URL = "https://pandemic-bit302.000webhostapp.com/testData.php";
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_record_test_menu);
      testList = findViewById(R.id.testList);
      layoutManager = new LinearLayoutManager(getApplicationContext());
      testList.setLayoutManager(layoutManager);
      getData();
   }
   public void getData(){
      StringRequest stringRequest = new StringRequest(Request.Method.GET, TEST_DATA_URL,
              new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                    System.out.println("=========> Response " + response);
                    System.out.println("=========> Test Center ID  " + testCenterId);

                    try {
                       JSONObject jsonObject = new JSONObject(response);
                       JSONArray testArrayJN = jsonObject.getJSONArray("testResult");
                       for (int i = 0; i < testArrayJN.length(); i++) {
                          JSONObject testObj = testArrayJN.getJSONObject(i);
                          if (testObj.getString("testCenterID").equals(testCenterId)){
                             System.out.println("=========> We are here ");
                             testArray.add(new Test(testObj.getString("resultID"),
                                     testObj.getString("testCenterID"),
                                     testObj.getString("userID"),
                                     testObj.getString("name"),
                                     testObj.getString("testDate"),
                                     testObj.getString("result"),
                                     testObj.getString("resultDate"),
                                     testObj.getString("testStatus")));
                          }
                       }
                       TestListAdapter testListAdapter = new TestListAdapter(getApplicationContext(),testArray);
                       testList.setAdapter(testListAdapter);
                    } catch (JSONException e) {
                       e.printStackTrace();
                       Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                 }
              },
              new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {

                 }
              });
      RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
      requestQueue.add(stringRequest);
   }

    public void RecordNewTest(View view) {
       Intent intent = new Intent(getApplicationContext(), RecordNewTestActivity.class);
       startActivity(intent);
    }
}