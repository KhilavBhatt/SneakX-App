package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.Shoe;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoeHelper {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public ShoeHelper(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addShoe(Integer id, String name, Integer price, String image){
        db = dbHelper.getWritableDatabase();
        if(!shoeExists(id)){
            ContentValues values = new ContentValues();
            values.put("shoeid", id);
            values.put("name", name);
            values.put("price", price);
            values.put("image", image);
            db.insert("shoes", null, values);
            db.close();
        }
    }

    private boolean shoeExists(Integer id){
        db = dbHelper.getReadableDatabase();
        String[] projection = {
                "shoeId"
        };
        String selection = "shoeId = ?";
        String[] selectionargs = {id.toString()};
        Cursor cursor = db.query("shoes", projection, selection, selectionargs, null, null, null);
        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public ArrayList<Shoe> getShoes(){
        db = dbHelper.getReadableDatabase();
        ArrayList<Shoe> shoes = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from shoes", null);
        while(cursor.moveToNext()){
            Shoe shoe = new Shoe(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
            shoes.add(shoe);
        }
        cursor.close();
        db.close();
        return shoes;
    }

    public Shoe getShoe(Integer shoeId) {
        db = dbHelper.getReadableDatabase();
        String[] projection = {
                "shoeId",
                "name",
                "price",
                "image"
        };
        String selection = "shoeId = ?";
        String[] selectionArgs = {String.valueOf(shoeId)};
        Cursor cursor = db.query("shoes", projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int col1 = cursor.getColumnIndex("shoeId");
            int col2 = cursor.getColumnIndex("name");
            int col3 = cursor.getColumnIndex("price");
            int col4 = cursor.getColumnIndex("image");
            if (col1 != -1 && col2 != -1 && col3 != -1 && col4 != -1) {
                return new Shoe(cursor.getInt(col1), cursor.getString(col2), cursor.getInt(col3), cursor.getString(col4));
            }
        }
        cursor.close();
        return null;
    }
}
