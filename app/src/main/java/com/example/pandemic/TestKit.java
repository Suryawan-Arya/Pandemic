package com.example.pandemic;

import android.app.VoiceInteractor;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestKit {

    private String id, testCenterID, testKitName, stock;
    private String URL_UPDATE_TEST_KIT = "";

    public TestKit() {
    }

    public TestKit(String id, String testCenterID, String testKitName, String stock) {
        this.id = id;
        this.testCenterID = testCenterID;
        this.testKitName = testKitName;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestCenterID() {
        return testCenterID;
    }

    public void setTestCenterID(String testCenterID) {
        this.testCenterID = testCenterID;
    }

    public String getTestKitName() {
        return testKitName;
    }

    public void setTestKitName(String testKitName) {
        this.testKitName = testKitName;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public void addTestKitStock(final Context context, final String testCenterID, final String stock){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE_TEST_KIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response Update Here -> " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error 2", Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("testCenterID", testCenterID);
                params.put("stock", stock);
                return params;
            }
        };

    }
}
