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

public class ManagerListActivity extends AppCompatActivity {

   private RecyclerView managerRv;
   private RecyclerView.LayoutManager layoutManager;
   private String URL_USER_DATA = "https://pandemic-bit302.000webhostapp.com/userData.php";
   private Users users;
   private ArrayList<Users> userArray = new ArrayList<>();
   


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_manager_list);

      managerRv = findViewById(R.id.managerRv);
      layoutManager = new LinearLayoutManager(getApplicationContext());
      managerRv.setLayoutManager(layoutManager);
      getData();
   }


   public void getData(){
      StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_USER_DATA,
              new Response.Listener<String>() {
                 @Override
                 public void onResponse(String response) {
                    System.out.println("---------> " + response);
                    try{
                       JSONObject jsonObject = new JSONObject(response);
                       JSONArray usersArrayJson = jsonObject.getJSONArray("users");
                       for (int i = 0; i < usersArrayJson.length(); i++) {
                          JSONObject usersObject = usersArrayJson.getJSONObject(i);
                          if (usersObject.getString("position").equals("Manager")){
                             userArray.add(new Users(usersObject.getString("id"),
                                     usersObject.getString("testCenterID"),
                                     usersObject.getString("userName"),
                                     usersObject.getString("name"),
                                     usersObject.getString("email"),
                                     usersObject.getString("password"),
                                     usersObject.getString("status")));
                          }
                       }
                       ManagerListAdapter managerListAdapter = new ManagerListAdapter(getApplicationContext(),userArray);
                       managerRv.setAdapter(managerListAdapter);
                    }catch (JSONException e){
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
      Intent intent = new Intent(getApplicationContext(), AdminMenuActivity.class);
      startActivity(intent);
   }
}