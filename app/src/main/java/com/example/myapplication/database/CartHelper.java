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

    private final DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    public CartHelper(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addToCart(Integer userId, Integer shoeId, Integer quantity){
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userId", userId);
        values.put("shoeId", shoeId);
        values.put("quantity", quantity);
        database.insert("carts", null, values);
        database.close();
    }

    public ArrayList<Cart> getCarts(Integer userId){
        database = dbHelper.getReadableDatabase();
        ArrayList<Cart> carts = new ArrayList<>();
        Cursor cursor = database.rawQuery("Select * from carts where userId = "+ userId, null);
        while(cursor.moveToNext()){
            Cart cart = new Cart(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            carts.add(cart);
        }
        cursor.close();
        database.close();
        return carts;
    }

    public Cart getCart(Integer userId, Integer shoeId){
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from carts where userId = ? and shoeId = ? ", new String[]{String.valueOf(userId), String.valueOf(shoeId)});
        Cart cart = null;
        if(cursor.moveToFirst()){
            cart = new Cart(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            cursor.close();
            database.close();
            return cart;
        }
        cursor.close();
        database.close();
        return cart;
    }

    public void updateCart(Integer userId, Integer shoeId, Integer quantity) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quantity", quantity);
        String whereClause = "userId = ? AND shoeId = ?";
        String[] whereArgs = {String.valueOf(userId), String.valueOf(shoeId)};
        database.update("carts", values, whereClause, whereArgs);
        database.close();
    }
    public void removeCart(Integer userId, Integer shoeId) {
        database = dbHelper.getWritableDatabase();
        String whereClause = "userId = ? AND shoeId = ?";
        String[] whereArgs = {String.valueOf(userId), String.valueOf(shoeId)};
        database.delete("carts", whereClause, whereArgs);
        database.close();
    }
}
