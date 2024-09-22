package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.Cart;
import com.example.myapplication.model.Transaction;

import java.util.ArrayList;

public class TransactionHelper {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public TransactionHelper(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addTransaction(Integer userId, ArrayList<Cart> carts){
        db = dbHelper.getWritableDatabase();
        for (Cart c:carts) {
            ContentValues values = new ContentValues();
            values.put("userId", userId);
            values.put("shoeId", c.getShoeId());
            values.put("quantity", c.getQuantity());
            db.insert("transactions", null, values);
        }
        db.close();
    }
    public ArrayList<Transaction> getTransactions(Integer userId){
        db = dbHelper.getReadableDatabase();
        ArrayList<Transaction> transactions = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from transactions where userId = " + userId, null);
        while(cursor.moveToNext()){
            Transaction tr = new Transaction(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
            transactions.add(tr);
        }
        cursor.close();
        db.close();
        return transactions;
    }
}
