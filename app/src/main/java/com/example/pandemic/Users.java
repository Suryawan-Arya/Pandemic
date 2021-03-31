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
    private String userID;
    private String userName;
    private String name;
    private String email;
    private String password;
    private Context context;

    public Users() {
    }

    public Users(String userID, String userName, String name, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public void login(final Context context, final String userName, final String password){
        if (!userName.isEmpty() && !password.isEmpty()){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                JSONArray jsonArray = jsonObject.getJSONArray("login");

                                if (success.equals("1")){
                                    for (int i = 0; i < jsonArray.length();i++){
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        if (object.get("position").equals("Manager")){
                                            if (object.get("status").equals("p")){
                                                Toast.makeText(context, "Login Success, Welcome 2", Toast.LENGTH_LONG).show();
                                            }else{
                                                Intent intent = new Intent(context,RegisterTestCenterActivity.class);
                                                context.startActivity(intent);                                            }
                                            Toast.makeText(context, "Login Success, Welcome ", Toast.LENGTH_LONG).show();
                                        }else if(false){
                                            Toast.makeText(context, "Login Success, Welcome 4", Toast.LENGTH_LONG).show();

                                        }else {
                                            Toast.makeText(context, "Login Success, Welcome 5", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "Invalid User Name or Password 1", Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Invalid User Name or Password 2", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("userName", userName);
                    params.put("password", password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(context, "User Name and Password can't be empty !!", Toast.LENGTH_LONG).show();
        }

    }

    public void register (final Context context , final String userName, final String name, final String email, final String password, String confirmPass) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

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
}
