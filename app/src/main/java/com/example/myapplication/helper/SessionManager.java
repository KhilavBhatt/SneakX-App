package com.example.myapplication.helper;

import android.content.Context;

import com.example.myapplication.database.Database;
import com.example.myapplication.model.User;

public class SessionManager {

    private static SessionManager instance;
    private Context context;
    private User user1;


    public static SessionManager getInstance(Context context){
        if(instance==null){
            synchronized (Database.class){
                if(instance==null){
                    instance = new SessionManager(context);
                }
            }
        }
        return instance;
    }

    private SessionManager(Context context){
        this.context = context;
    }

    public void saveCredential(User user){
        this.user1 = user;
    }

    public User getUser(){
        return user1;
    }
    public void setUser(User user){
        this.user1 = user;
    }


}
