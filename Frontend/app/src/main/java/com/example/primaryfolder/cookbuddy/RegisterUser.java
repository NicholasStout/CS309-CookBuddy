package com.example.primaryfolder.cookbuddy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.primaryfolder.cookbuddy.app.AppController;
import com.example.primaryfolder.cookbuddy.net_utils.Const;
import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {

    private String TAG = RegisterUser.class.getSimpleName();

    // Variables for buttons on the login screen
    Button btnSubmitInfo, btnToSignInPage;

    // Values for the user fields
    EditText uName, uEmail, uPassword, uPasswordConfirm;

    // The url for the server
    static final String SERVER_URL = "http://proj309-sb-02.misc.iastate.edu:8080/users/";

    // Progress dialog
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // Initialize variables
        btnSubmitInfo = (Button) findViewById(R.id.submitInfo); // Submits the user info to the server
        btnToSignInPage = (Button) findViewById(R.id.signInPage); // Takes user to another page to sign in with existing credentials

        uName = (EditText) findViewById(R.id.userName); // Variable for the name entered in field
        uEmail = (EditText) findViewById(R.id.userEmail); // Variable for the email entered in field
        uPassword = (EditText) findViewById(R.id.userPassword); // Variable for the password entered in field
        uPasswordConfirm = (EditText) findViewById(R.id.userPasswordConfirm); // Variable for the password in field

        pDialog = new ProgressDialog(this); // The progress dialog object for this activity
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        // The "Register" button on this activity that will submit the info entered to the server and redirect to Home
        btnSubmitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*
                 *
                 * TODO 2. CHECK TO MAKE SURE THERE ARE NO NULL PARAMETERS
                 *
                 */
                // Variables that get the string from user's entered fields
                final String userName, userEmail, userPassword;

                userName = uName.getText().toString();
                userEmail = uEmail.getText().toString();
                userPassword = uPassword.getText().toString();

                /*
                 *
                 * JSON OBJECT REQUEST USING POST METHOD
                 *
                 */
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL + "add", null,

                        // Response Listener
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                pDialog.hide();
                            }
                        },

                        // Error Listener
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                                pDialog.hide();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> params = new HashMap<String, String>();
                                params.put("name", userName);
                                params.put("email", userEmail);
                                params.put("password", userPassword);

                                return params;
                            }
                        };

                AppController.getInstance().addToRequestQueue(jsonObjReq);

                Intent j = new Intent(RegisterUser.this, Home.class);
                startActivity(j);
            }
        });

        // The "Sign In" button on this activity that will redirect to the existing user sign in activity
        btnToSignInPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterUser.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }
}
