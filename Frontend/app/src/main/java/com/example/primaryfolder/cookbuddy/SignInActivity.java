package com.example.primaryfolder.cookbuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.primaryfolder.cookbuddy.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    private String TAG = SignInActivity.class.getSimpleName();


    // Variables for the entered email and password
    EditText enteredUserEmail, enteredUserPassword;

    //Strings for comparing the entered info with the info in the database
    String enteredEmail, enteredPassword, userEmail, actualPassword;

    // Variable for the sign in button
    Button btnSignIn;

    // Server url
    static final String SERVER_URL = "http://proj309-sb-02.misc.iastate.edu:8080/user//all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = (Button) findViewById(R.id.SignIn);
        enteredUserEmail = (EditText) findViewById(R.id.enteredEmailAddress);
        enteredUserPassword = (EditText) findViewById(R.id.enteredPassword);


    }

}
