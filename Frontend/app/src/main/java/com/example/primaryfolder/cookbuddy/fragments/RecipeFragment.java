package com.example.primaryfolder.cookbuddy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.app.AppController;
import com.example.primaryfolder.cookbuddy.other.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private Recipe r;
    private TextView title;
    private TextView ingred;
    private TextView desc;
    private String ingList;
    private String url = "http://proj309-sb-02.misc.iastate.edu:8080/recipesIng/{recipe_id}/all";
    private String TAG = RecipeFragment.class.getSimpleName();




    public RecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param r A recipe.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(Recipe res) {
        RecipeFragment fragment = new RecipeFragment();
        fragment.setR(res);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getView().findViewById(R.id.recipeTitle);
        title.setText(r.getRecipeName());

        ingList = "";
        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        VolleyLog.d(TAG, response.toString());

                        try{

                            for(int i = 0; i < response.length(); i++){
                                //get current Json object
                                JSONObject ing = response.getJSONObject(i);
                                ingList = ingList + ing.getString("amount") + ing.getString("name")+"\n";

                            }
                            ingred = getView().findViewById(R.id.recipeIngredients);
                            ingred.setText(ingList);

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

        desc = getView().findViewById(R.id.recipeDesc);
        desc.setText(r.getInstructions());
    }

    private String buildString () {
        String s = r.getRecipeName();
        return s;
    }


    public void setR(Recipe res) { r = res; }


}
