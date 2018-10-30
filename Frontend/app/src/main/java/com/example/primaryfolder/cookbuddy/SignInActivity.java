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
import com.android.volley.toolbox.JsonRequest;
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


    // Variable for the sign in button
    Button btnSignIn, btnSignUp;

    // Server url
    static final String SERVER_URL = "http://proj309-sb-02.misc.iastate.edu:8080/users/sign_in";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = (Button) findViewById(R.id.SignIn);
        btnSignUp = (Button) findViewById(R.id.btnGoToUserRegister);
        enteredUserEmail = (EditText) findViewById(R.id.enteredEmailAddress);
        enteredUserPassword = (EditText) findViewById(R.id.enteredPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, RegisterUser.class);
                startActivity(i);
            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String for the entered info
                final String enteredEmail, enteredPassword;
                enteredEmail = enteredUserEmail.getText().toString();
                enteredPassword = enteredUserPassword.getText().toString();

                //these are the parameters used by the back end methods
                Map<String, String> postParam= new HashMap<String, String>();
                postParam.put("email", enteredEmail);
                postParam.put("password", enteredPassword);

                //Json request using the post method
                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.POST, SERVER_URL, new JSONObject(postParam),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                Intent i = new Intent(SignInActivity.this, ViewRecipes.class);
                                startActivity(i);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
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
                AppController.getInstance().addToRequestQueue(jsonReq);
            }
        });
    }
}
