package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.Database;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password;
    Button login, signUp;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);
        init();
        setEvent();
    }
    void init(){
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        login = findViewById(R.id.loginBtn);
        signUp = findViewById(R.id.registerBtn);
        db = Database.getInstance(this);
    }

    void setEvent(){
        login.setOnClickListener(e->{

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
        signUp.setOnClickListener(e->{
            String email = this.email.getText().toString();
            String password = this.password.getText().toString();
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "All Fields Must Be Filled", Toast.LENGTH_SHORT).show();
            }
            else {
                if(db.getUser(email, password)==null){
                    db.addUser(email, password);
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(this, "Email and Password already taken", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}