package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.database.Database;
import com.example.myapplication.helper.SessionManager;
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.User;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView name, price, qty;
    private ImageButton add, remove, back;
    private Button addToCart;
    Integer quantity = 0;
    Intent intent;
    Database database;

    void init(){
        imageView = findViewById(R.id.imgv);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        addToCart = findViewById(R.id.cart);

        database = Database.getInstance(this);

        intent = getIntent();
        Picasso.get().load(intent.getStringExtra("shoeimage")).into(imageView);
        name.setText(intent.getStringExtra("shoename"));
        price.setText(intent.getStringExtra("shoeprice"));
        quantity = intent.getIntExtra("quantity", 0);
        refreshQuantity();
    }

    void setEvent(){
        back.setOnClickListener(e->{
            finishAndRemoveTask();
        });
        add.setOnClickListener(e->{
            quantity += 1;
            refreshQuantity();
        });
        remove.setOnClickListener(e->{
            if(quantity!=0){
                quantity -= 1;
                refreshQuantity();
            }
        });
        addToCart.setOnClickListener(e->{
            if(quantity > 0){
                User user = SessionManager.getInstance(this).getUser();
                Integer shoeId = intent.getIntExtra("shoeid", 0);
                Cart cart = database.getCart(user.getUserId(),shoeId);
                if(cart !=null){
                    database.updateCart(user.getUserId(), shoeId, quantity + cart.getQuantity());
                }
                else{
                    Toast.makeText(this, "Product Success Add To Cart", Toast.LENGTH_SHORT).show();
                    database.addToCart(user.getUserId(), shoeId, quantity);
                }
            }
            else{
                Toast.makeText(this, "Quantity must be more than 0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_detail);
        init();
        setEvent();
    }

    void refreshQuantity(){
        qty.setText(quantity.toString());
    }
}