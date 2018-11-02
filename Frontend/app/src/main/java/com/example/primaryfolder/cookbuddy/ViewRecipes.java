package com.example.primaryfolder.cookbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.primaryfolder.cookbuddy.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Response.*;


public class ViewRecipes extends AppCompatActivity {

    private String TAG = ViewRecipes.class.getSimpleName();
    private Button btnGetRecipes, btnNewRecipe;
    private TextView mTextView;
    private String url = "http://proj309-sb-02.misc.iastate.edu:8080/recipes/all/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);

        btnGetRecipes = (Button) findViewById(R.id.buttonGetRecipes);
        btnNewRecipe = (Button) findViewById(R.id.buttonAddRecipe);
        mTextView = (TextView) findViewById(R.id.textViewRecipes);

        btnNewRecipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(ViewRecipes.this, AddRecipe.class);
                startActivity(i);
            }
        });

        btnGetRecipes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>(){
                            @Override
                            public void onResponse(JSONObject response){
                                VolleyLog.d(TAG, response.toString());

                                try{
                                    JSONArray jsonArray = response.getJSONArray("recipes");

                                    for(int i = 0; i < jsonArray.length(); i++){
                                        //get current Json object
                                        JSONObject recipe = jsonArray.getJSONObject(i);

                                        String recipeName = recipe.getString("recipe_name");
                                        int recipeID = recipe.getInt("id");
                                        String recipeInstructions = recipe.getString("instructions");

                                        mTextView.append("Recipe Name: " + recipeName + " ID: " + recipeID +"\n");
                                        mTextView.append("Instructions: " + recipeInstructions + "\n\n");
                                    }

                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                            }
                        });
                AppController.getInstance().addToRequestQueue(jsonReq);

            }
        });

    }
}
