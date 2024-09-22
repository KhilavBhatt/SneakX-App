package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String database = "HighLow";
    private static final int version = 1;

    private static final String userTable = "create table users(userId integer primary key autoincrement, email text, password text)";
    private static final String shoeTable = "create table shoes(shoeId integer primary key, name text, price integer, image text)";
    private static final String cartTable = "CREATE TABLE carts (userId INTEGER, shoeId INTEGER, quantity INTEGER, " +
            "FOREIGN KEY (userId) REFERENCES users(userId) ON UPDATE CASCADE ON DELETE CASCADE, " +
            "FOREIGN KEY (shoeId) REFERENCES shoes(shoeId) ON UPDATE CASCADE ON DELETE CASCADE, " +
            "primary key(userId, shoeId))";
    private static final String transactionTable = "CREATE TABLE transactions (" +
            "transactionId INTEGER PRIMARY KEY AUTOINCREMENT," +
            "userId INTEGER," +
            "shoeId INTEGER," +
            "quantity INTEGER," +
            "FOREIGN KEY (userId) REFERENCES users(userId) ON UPDATE CASCADE ON DELETE CASCADE," +
            "FOREIGN KEY (shoeId) REFERENCES shoes(shoeId) ON UPDATE CASCADE ON DELETE CASCADE" +
            ")";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(userTable);
        db.execSQL(shoeTable);
        db.execSQL(cartTable);
        db.execSQL(transactionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users ");
        db.execSQL("drop table if exists shoes ");
        db.execSQL("drop table if exists carts");
        db.execSQL("drop table if exists transactions");
    }
}
