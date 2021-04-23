package com.example.pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class ManagerMenuActivity extends AppCompatActivity {

    private TextView testCenterName;
    private String testCenterId = Users.testCenterID;
    private String URL_GET_TEST_CENTER = "https://pandemic-bit302.000webhostapp.com/testCenterData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_menu);

        testCenterName = findViewById(R.id.testCenterNameTx);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_TEST_CENTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testCenterArray = jsonObject.getJSONArray("testCenter");
                            for (int i = 0; i < testCenterArray.length(); i++){
                                JSONObject testCenterObject = testCenterArray.getJSONObject(i);
                                if (testCenterObject.get("ID").equals(testCenterId)){
                                    testCenterName.setText(testCenterObject.getString("testCenterName"));
                                    System.out.println(testCenterObject.getString("testCenterName"));
                                }
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Human Error Error", Toast.LENGTH_LONG).show();
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

    public void RecordTester(View view) {
        Intent intent = new Intent(getApplicationContext(),RecordTesterActivity.class);
        startActivity(intent);
    }

    public void TestRecord(View view) {
        Intent intent = new Intent(getApplicationContext(),TestDetailActivity.class);
        startActivity(intent);
    }

    public void ManageTestKit(View view) {
        Intent intent = new Intent(getApplicationContext(),ManageTestKitActivity.class);
        startActivity(intent);
    }

    public void Profile(View view) {

    }
}