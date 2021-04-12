package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageTestKitActivity extends AppCompatActivity {

    RecyclerView testKitRv;

    RecyclerView.LayoutManager testKitLm;

    private String testCenterId = Users.testCenterID;

    private String URL_TEST_KIT_DATA = "https://pandemic-bit302.000webhostapp.com/testKitData.php";
    private String URL_UPDATE_TEST_KIT = "https://pandemic-bit302.000webhostapp.com/updateTestKit.php";

    private ArrayList<TestKit> testKitsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_test_kit);

        testKitRv = findViewById(R.id.testKitList);

        testKitLm = new LinearLayoutManager(getApplicationContext());
        testKitRv.setLayoutManager(testKitLm);

        getData(testCenterId);

    }

    public void getData(final String testCenterID){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_TEST_KIT_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testKitArray = jsonObject.getJSONArray("testKit");
                            for (int i = 0; i < testKitArray.length(); i++){
                                JSONObject testKitObj = testKitArray.getJSONObject(i);
                                if (testCenterID.equals(testKitObj.getString("testCenterID"))){
                                    String testKitName = testKitObj.getString("testKitName");
                                    String amount = testKitObj.getString("stock");
                                    testKitsArray.add(new TestKit(testKitObj.getString("ID"),
                                            testKitObj.getString("testCenterID"),
                                            testKitObj.getString("testKitName"),
                                            testKitObj.getString("stock")));
                                }
                            }
                            TestKitListAdapter testKitListAdapter = new TestKitListAdapter(getApplicationContext(),testKitsArray);
                            testKitRv.setAdapter(testKitListAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void Back(View view) {
        Intent intent = new Intent(getApplicationContext(),ManagerMenuActivity.class);
        startActivity(intent);
    }


    public void AddTestKit(View view) {
        Intent intent = new Intent(getApplicationContext(),AddTestKitActivity.class);
        startActivity(intent);
    }
}