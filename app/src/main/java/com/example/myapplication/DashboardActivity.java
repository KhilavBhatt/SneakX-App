package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.myapplication.fragment.CartFragment;
import com.example.myapplication.fragment.ShoesFragment;
import com.example.myapplication.fragment.TransactionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ShoesFragment shoesFragment = new ShoesFragment();
    CartFragment cartFragment = new CartFragment();
    TransactionFragment transactionFragment= new TransactionFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView = findViewById(R.id.bottomnav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, shoesFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.shoe:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, shoesFragment).commit();
                        return true;
                    case R.id.cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, cartFragment).commit();
                        return true;
                    case R.id.tr:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, transactionFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}