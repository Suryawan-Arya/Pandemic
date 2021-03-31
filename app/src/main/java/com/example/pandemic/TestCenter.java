package com.example.pandemic;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestCenter {

    private String testCenterName;
    private String URL = "https://pandemic-bit302.000webhostapp.com/registerTestCenter.php";
    private String URL_GET_TEST_CENTER = "https://pandemic-bit302.000webhostapp.com/testCenterData.php";
    private String URL_UPDATE_MANAGER = "https://pandemic-bit302.000webhostapp.com/updateTestCenterManager.php";



    public TestCenter() {
    }

    public TestCenter(String testCenterName) {
        this.testCenterName = testCenterName;
    }

    public String getTestCenterName() {
        return testCenterName;
    }

    public void setTestCenterName(String testCenterName) {
        this.testCenterName = testCenterName;
    }


    public void registerTestCenter(final Context context, final String testCenterName, final String managerID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("See the Problems Here -> " + response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);


                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(context, "Human Error Error", Toast.LENGTH_LONG).show();

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
                params.put("testCenterName", testCenterName);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URL_GET_TEST_CENTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("The Response is Here -> " + response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray testCenterArray = jsonObject.getJSONArray("testCenter");
                            for (int i = 0; i < testCenterArray.length(); i++){
                                JSONObject testCenterObject = testCenterArray.getJSONObject(i);
                                if (testCenterObject.get("testCenterName").equals(testCenterName)){
                                    String testCenterId = testCenterObject.getString("ID");
                                    updateTestCenterManager(context,testCenterId);
                                }
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(context, "Human Error Error", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Connection Error", Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue2 = Volley.newRequestQueue(context);
        requestQueue2.add(stringRequest1);
    }

    public void updateTestCenterManager(final Context context, final String testCenterId){
        final String managerId = Users.testManagerID;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_MANAGER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("The Response is Here -> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Human Error", Toast.LENGTH_LONG).show();
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
                params.put("id", managerId);
                params.put("testCenterId", testCenterId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
