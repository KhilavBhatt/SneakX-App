package com.example.myapplication.database;

import android.content.Context;

import com.example.myapplication.model.Cart;
import com.example.myapplication.model.Shoe;
import com.example.myapplication.model.Transaction;
import com.example.myapplication.model.User;

import java.util.ArrayList;

public class Database {

    private static Database instance;

    private ShoeHelper shoeHelper;
    private UserHelper userHelper;
    private CartHelper cartHelper;
    private TransactionHelper transactionHelper;

    private Database(Context context){
        userHelper = new UserHelper(context);
        shoeHelper = new ShoeHelper(context);
        cartHelper = new CartHelper(context);
        transactionHelper = new TransactionHelper(context);
    }

    public static Database getInstance(Context context){
        if(instance==null){
            synchronized (Database.class){
                if(instance==null){
                    instance = new Database(context);
                }
            }
        }
        return instance;
    }

    public void addUser( String email, String password){
        userHelper.addUser(email, password);
    }

    public User getUser(String email, String password){
        return userHelper.getUser(email, password);
    }

    public ArrayList<Shoe> getShoes(){
        return shoeHelper.getShoes();
    }

    public Shoe getShoe(Integer shoeid){
        return shoeHelper.getShoe(shoeid);
    }

    public void addShoe(Integer id, String name, Integer price, String image){
        shoeHelper.addShoe(id, name, price, image);
    }

    public void addToCart(Integer userId, Integer shoeId, Integer quantity){
        cartHelper.addToCart(userId, shoeId, quantity);
    }
    public Cart getCart(Integer userId, Integer shoeId){
        return cartHelper.getCart(userId, shoeId);
    }

    public ArrayList<Cart> getCarts(Integer userId){
        return cartHelper.getCarts(userId);
    }

    public void updateCart(Integer userId, Integer shoeId, Integer quantity){
        cartHelper.updateCart(userId, shoeId, quantity);
    }

    public void removeCart(Integer userId, Integer shoeId){
        cartHelper.removeCart(userId, shoeId);
    }

    public void addTransaction(Integer userId, ArrayList<Cart> carts){
        transactionHelper.addTransaction(userId, carts);
    }

    public ArrayList<Transaction> getTransactions(Integer userId){
        return transactionHelper.getTransactions(userId);
    }
}
