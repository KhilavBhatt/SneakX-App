package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.Cart;
import com.example.myapplication.model.Shoe;
import com.example.myapplication.model.User;

import java.util.ArrayList;

public class CartHelper {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public CartHelper(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addToCart(Integer userId, Integer shoeId, Integer quantity){
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("shoeId", shoeId);
        values.put("quantity", quantity);
        db.insert("carts", null, values);
        db.close();
    }

    public ArrayList<Cart> getCarts(Integer userId){
        db = dbHelper.getReadableDatabase();
        ArrayList<Cart> carts = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from carts where userId = "+ userId, null);
        while(cursor.moveToNext()){
            Cart cart = new Cart(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            carts.add(cart);
        }
        cursor.close();
        db.close();
        return carts;
    }

    public Cart getCart(Integer userId, Integer shoeId){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from carts where userId = ? and shoeId = ? ", new String[]{String.valueOf(userId), String.valueOf(shoeId)});
        Cart cart = null;
        if(cursor.moveToFirst()){
            cart = new Cart(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            cursor.close();
            db.close();
            return cart;
        }
        cursor.close();
        db.close();
        return cart;
    }

    public void updateCart(Integer userId, Integer shoeId, Integer quantity) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quantity", quantity);
        String whereClause = "userId = ? AND shoeId = ?";
        String[] whereArgs = {String.valueOf(userId), String.valueOf(shoeId)};
        db.update("carts", values, whereClause, whereArgs);
        db.close();
    }
    public void removeCart(Integer userId, Integer shoeId) {
        db = dbHelper.getWritableDatabase();
        String whereClause = "userId = ? AND shoeId = ?";
        String[] whereArgs = {String.valueOf(userId), String.valueOf(shoeId)};
        db.delete("carts", whereClause, whereArgs);
        db.close();
    }
}
