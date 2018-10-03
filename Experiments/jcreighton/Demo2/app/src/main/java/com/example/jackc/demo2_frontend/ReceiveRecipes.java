package com.example.jackc.demo2_frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReceiveRecipes extends AppCompatActivity {
    private Button btnGetRecipe, btnAddRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_recipes);

        btnGetRecipe = findViewById(R.id.buttonGetRecipe);
        btnAddRecipe = findViewById(R.id.buttonAddNewRecipe);


        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReceiveRecipes.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}

