package com.example.primaryfolder.cookbuddy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
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
import android.view.View.OnClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import static com.android.volley.Request.Method.POST;

public class UserProfile extends AppCompatActivity {

    // The button to sign out
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize the button
        btnSignOut = (Button) findViewById(R.id.SignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO

                // Takes the user back to the sign in page
                Intent i = new Intent(UserProfile.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }
}
