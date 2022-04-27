package com.example.airbas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;


import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    EditText email, pass;
    Button button;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPassword);

        button = findViewById(R.id.button1);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // validating if the text field is empty or not.
                if (email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();

                }
                // calling a method to post the data and passing our name and job.
                else {
                    Login(email.getText().toString(), pass.getText().toString());

                }

            }
        });
    }


    public void Login(String email, String pass) {

        String URL = "http://10.0.2.2:8082/auth/login";

        JSONObject js = new JSONObject();
        try {
            js.put("email",email);
            js.put("password",pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, URL, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString() + " Nice");
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("email", email);
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(i);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");

                return headers;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(jsonObjReq);

    }


}
