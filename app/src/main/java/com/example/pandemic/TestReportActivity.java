package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class TestReportActivity extends AppCompatActivity {
    private RecyclerView testReport;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Test> testArray = new ArrayList<>();
    private String testCenterID = Users.testCenterID;
    private String URL_TEST_DATA = "https://pandemic-bit302.000webhostapp.com/testData.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report);

        testReport = findViewById(R.id.testReport);
        layoutManager = new LinearLayoutManager(this);
        testReport.setLayoutManager(layoutManager);
        getData(testCenterID);
    }

    public void getData(final String testCenterID){
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
                                if (testCenterID.equals(testObj.getString("testCenterID"))){
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
                            TestAdapter testAdapter = new TestAdapter(getApplicationContext(),testArray);
                            testReport.setAdapter(testAdapter);
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
}
