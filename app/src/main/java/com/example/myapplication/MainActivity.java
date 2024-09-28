package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.database.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
     Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        db = Database.getInstance(this);
        fetchAndStoreData(() -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
    public void fetchAndStoreData(FetchDataCallback callback) {
        String url = "https://raw.githubusercontent.com/Stupidism/goat-sneakers/master/api.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    System.out.println("Calling Parse JSON");
                    parseJsonResponse(response);
                    callback.onFetchDataCompleted();
                },
                error -> {
                    error.printStackTrace();
                });
        Volley.newRequestQueue(this).add(request);
    }

    private void parseJsonResponse(JSONObject jsonObject) {
        try {
            System.out.println("Parse JSON");
            JSONArray sneakersArray = jsonObject.getJSONArray("sneakers");
            for (int i = 0; i < sneakersArray.length(); i++) {
                JSONObject sneakerObj = sneakersArray.getJSONObject(i);
                Integer id = sneakerObj.getInt("id");
                String brandName = sneakerObj.getString("brand_name");
                String name = sneakerObj.getString("name");
                String image = sneakerObj.getString("main_picture_url");
                Integer price = 0;
                if(!sneakerObj.isNull("retail_price_cents")){
                    price = sneakerObj.getInt("retail_price_cents");
                }
                db.addShoe(id, name, price, image);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    interface FetchDataCallback {
        void onFetchDataCompleted();
    }
}