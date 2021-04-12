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

public class Users {

    private String URL = "https://pandemic-bit302.000webhostapp.com/login.php";
    private String URL_REGISTER = "https://pandemic-bit302.000webhostapp.com/register.php";
    private String URL_USER_DATA = "https://pandemic-bit302.000webhostapp.com/userData.php";
    private String URL_RECORD_TESTER = "https://pandemic-bit302.000webhost.com/recordTester.php";
    private String userID;
    private String testCenterId;
    private String userName;
    private String name;
    private String email;
    private String password;
    private String status;
    private Context context;

    public static String testManagerID;
    public static String testCenterID;


    public Users() {
    }

    public Users(String userID, String userName, String name, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Users(String userID, String testCenterId, String userName, String name, String email, String password, String status) {
        this.userID = userID;
        this.testCenterId = testCenterId;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public String getTestCenterId() {
        return testCenterId;
    }

    public void setTestCenterId(String testCenterId) {
        this.testCenterId = testCenterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(final Context context, final String userName, final String password) {
        if (!userName.isEmpty() && !password.isEmpty()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                JSONArray jsonArray = jsonObject.getJSONArray("login");
                                if (success.equals("1")) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        if (object.get("position").equals("Manager")) {
                                            testManagerID = object.getString("id");
                                            testCenterID = object.getString("testCenterID");
                                            if (object.get("status").equals("p")) {
                                                Intent intent = new Intent(context, ManagerMenuActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                context.startActivity(intent);
                                            } else if (object.get("status").equals("r")) {
                                                Intent intent = new Intent(context, RegisterTestCenterActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                context.startActivity(intent);
                                            } else if (object.get("status").equals("n")) {
                                                Intent intent = new Intent(context, StatusActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                context.startActivity(intent);
                                            }
                                        } else if (object.get("position").equals("Tester")) {
                                            Intent intent = new Intent(context, TesterMenuActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent);
                                            Toast.makeText(context, "Login Success, Welcome", Toast.LENGTH_LONG).show();

                                        } else if (object.get("position").equals("Patient")) {
                                            Intent intent = new Intent(context, PatientMenuActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent);
                                            Toast.makeText(context, "Login Success, Welcome", Toast.LENGTH_LONG).show();
                                        }else if (object.get("position").equals("Admin")){
                                            Intent intent = new Intent(context, AdminMenuActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent);
                                            Toast.makeText(context, "Login Success, Welcome", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Connection Erro", Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("userName", userName);
                    params.put("password", password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(context, "User Name and Password can't be empty !!", Toast.LENGTH_LONG).show();
        }
    }

    public void registerValidation(final Context context, final String userName, final String name, final String email, final String password){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_USER_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response ------> " + response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray userArray = jsonObject.getJSONArray("User");
                            boolean status = true;
                            for (int i = 0; i < userArray.length(); i++){
                                JSONObject userObject = userArray.getJSONObject(i);
                                if (userObject.get("userName").equals(userName)){
                                    status = false;
                                }
                            }
                            if (status){
                                register(context, userName, name, email, password);
                            }else{
                                Toast.makeText(context, "User Name Already Exist", Toast.LENGTH_LONG).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(context, "Human Error ", Toast.LENGTH_LONG).show();
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

    public void register(final Context context, final String userName, final String name, final String email, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(context, StatusActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Invalid User Name or Password 1", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userName", userName);
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void recordTester(final Context context, final String userName, final String testCenterID,final String name, final String email, final String password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_RECORD_TESTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Invalid User Name or Password 1", Toast.LENGTH_LONG).show();
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
                params.put("userName", userName);
                params.put("testCenterID", testCenterID);
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
    }
}
