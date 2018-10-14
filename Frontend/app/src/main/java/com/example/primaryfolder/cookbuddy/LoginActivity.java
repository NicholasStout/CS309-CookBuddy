package com.example.primaryfolder.cookbuddy;

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
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.primaryfolder.cookbuddy.app.AppController;
import com.example.primaryfolder.cookbuddy.net_utils.HomeActivity;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    // Variables for buttons on the login screen
    Button btnRegister, btnSignIn;

    // Values for the user fields
    EditText uName, uEmail, uPassword, uPasswordConfirm;

    // The url for the server
    static final String SERVER_URL = "http://proj309-sb-02.misc.iastate.edu:8080/recipes/";

    // Alert Dialog
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize variables
        btnRegister = (Button) findViewById(R.id.Register);
        btnSignIn = (Button) findViewById(R.id.SignIn); // Takes user to another page to sign in with existing credentials

        uName = (EditText) findViewById(R.id.Name);
        uEmail = (EditText) findViewById(R.id.EmailAddress);
        uPassword = (EditText) findViewById(R.id.Password);
        uPasswordConfirm = (EditText) findViewById(R.id.ConfirmPassword);
        builder = new AlertDialog.Builder(LoginActivity.this);

        // The 'sign in' button takes you to another page
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });

        // The register button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName, userEmail, userPassword;
                    userName = uName.getText().toString();
                    userEmail = uEmail.getText().toString();
                    userPassword = uPassword.getText().toString();

                // Make string request to the server with user information
                StringRequest stringRequest = new StringRequest(Request.Method.GET, SERVER_URL+"add?userName="+userName,
                        new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                                builder.setTitle("Please wait...");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        uName.setText("");
                                        uEmail.setText("");
                                        uPassword.setText("");
                                    }
                                });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                           }

                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, "Error:", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                                uPassword.setText(error.toString());
                            }

                        }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("userName", userName);
                            params.put("userEmail", userEmail);
                            params.put("userPassword", userPassword);

                            return params;
                        }
                };

                AppController.getInstance().addToRequestQueue(stringRequest);

                // Takes you to the home page once the registration is complete
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}
