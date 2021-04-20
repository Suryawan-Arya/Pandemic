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

public class PatientMenuActivity extends AppCompatActivity {

    private RecyclerView testRecycleView;
    private RecyclerView.LayoutManager layoutManager;
    private String patientID = Users.testManagerID;
    private String URL_TEST_DATA = "https://pandemic-bit302.000webhostapp.com/testData.php";
    private ArrayList<Test> arrayTest = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_menu);

        testRecycleView = findViewById(R.id.testHistoryList);
        layoutManager = new LinearLayoutManager(this);
        testRecycleView.setLayoutManager(layoutManager);
        getData(patientID);
    }

    public void getData(final String patientID){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TEST_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Get Data Response -----> " + response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testResultArray = jsonObject.getJSONArray("testResult");
                            for (int i = 0; i < testResultArray.length(); i++){
                                JSONObject testObj = testResultArray.getJSONObject(i);
                                if (patientID.equals(testObj.getString("userID"))){
                                    arrayTest.add(new Test(testObj.getString("resultID"),
                                            testObj.getString("testCenterID"),
                                            testObj.getString("userID"),
                                            testObj.getString("name"),
                                            testObj.getString("testDate"),
                                            testObj.getString("result"),
                                            testObj.getString("resultDate"),
                                            testObj.getString("testStatus")));
                                }
                            }
                            TestAdapter testAdapter = new TestAdapter(getApplicationContext(),arrayTest);
                            testRecycleView.setAdapter(testAdapter);
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
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

    public void LogOut(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}