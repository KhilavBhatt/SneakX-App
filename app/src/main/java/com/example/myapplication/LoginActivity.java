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
import com.example.myapplication.helper.SessionManager;
import com.example.myapplication.model.User;

public class LoginActivity extends AppCompatActivity {

    EditText eml, passwrd;
    Button lgin, signUp;
    Database database;

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        init();
        setEvent();
    }

    void init(){
        eml = findViewById(R.id.txtEmail);
        passwrd = findViewById(R.id.txtPassword);
        lgin = findViewById(R.id.loginBtn);
        signUp = findViewById(R.id.registerBtn);
        database = Database.getInstance(this);
        sessionManager = SessionManager.getInstance(this);
    }

    void setEvent(){
        signUp.setOnClickListener(e->{
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
        lgin.setOnClickListener(e->{
            String email = this.eml.getText().toString();
            String password = this.passwrd.getText().toString();
            if(email.length()==0 || password.length()==0){
                Toast.makeText(this, "All Fields Must Be Filled", Toast.LENGTH_SHORT).show();
            }
            else {
                User user = database.getUser(email, password);
                if(user!=null){
                    sessionManager.saveCredential(user);
                    startActivity(new Intent(this, DashboardActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(this, "Wrong Credential", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}