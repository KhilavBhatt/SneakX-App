package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.User;

public class UserHelper {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public UserHelper(Context context){
        dbHelper = new DatabaseHelper(context);
    }
    public void addUser(String email, String password){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        db.insert("users", null, values);
        db.close();
    }

    public User getUser(String email, String password){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email = ? and password = ?", new String[]{email, password});
        User user = null;
        if(cursor.moveToFirst()){
            user = new User(cursor.getInt(0), cursor.getString(1),cursor.getString(2) );
        }
        return user;
    }
}
