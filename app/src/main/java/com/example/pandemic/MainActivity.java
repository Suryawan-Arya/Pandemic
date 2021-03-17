package com.example.pandemic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    private EditText userNameTxt, passwordTxt;
    private String userName, password;
    private String URL = "https://pandemic-bit302.000webhostapp.com/login.php";


    Users users = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameTxt = findViewById(R.id.userName);
        passwordTxt = findViewById(R.id.password);



    }

    public void Register(View view) {
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }

    public void Login(View view) {
        boolean status = false;
        userName = userNameTxt.getText().toString().trim();
        password = passwordTxt.getText().toString().trim();
        
        status = users.login(getApplicationContext(), userName, password);

        if (status){
            System.out.println("True");
        }else {
            System.out.println("false");
        }




    }
}
