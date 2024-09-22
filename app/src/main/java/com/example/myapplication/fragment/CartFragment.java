package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CartAdapter;
import com.example.myapplication.adapter.ShoeAdapter;
import com.example.myapplication.database.Database;
import com.example.myapplication.helper.SessionManager;
import com.example.myapplication.interfaces.CartButtonListener;
import com.example.myapplication.interfaces.OnItemClickListener;
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.Shoe;

import java.util.ArrayList;


public class CartFragment extends Fragment implements CartButtonListener {

    private RecyclerView rcv;
    private Button buy;
    private TextView total;
    private CartAdapter ca;
    private ArrayList<Cart> carts = new ArrayList<>();
    private ArrayList<Cart> selectedCarts = new ArrayList<>();
    Database db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        rcv = root.findViewById(R.id.rcv);
        buy = root.findViewById(R.id.buy);
        total = root.findViewById(R.id.total);
        total.setText("0");
        db = Database.getInstance(requireContext());
        carts = db.getCarts(SessionManager.getInstance(requireContext()).getUser().getUserId());
        refreshSelected();
        buy.setOnClickListener(e->{
            db.addTransaction(SessionManager.getInstance(requireContext()).getUser().getUserId(), selectedCarts);
            for (Cart c: selectedCarts) {
                db.removeCart(c.getUserId(), c.getShoeId());
                carts.remove(c);
            }
            ca.notifyDataSetChanged();
        });
        ca = new CartAdapter(carts, requireContext(), this);
        LinearLayoutManager lm = new LinearLayoutManager(requireContext());
        rcv.setLayoutManager(lm);
        rcv.setAdapter(ca);
        ca.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onAddClicked(int position) {
        Cart cart = carts.get(position);
        int userId = SessionManager.getInstance(requireContext()).getUser().getUserId();
        int shoeId = cart.getShoeId();
        int quantity = cart.getQuantity() + 1;
        db.updateCart(userId, shoeId, quantity);
        cart.setQuantity(quantity);
        calculateTotalPrice();
        ca.notifyDataSetChanged();
    }

    @Override
    public void onRemoveClicked(int position) {
        Cart cart = carts.get(position);
        int userId = SessionManager.getInstance(requireContext()).getUser().getUserId();
        int shoeId = cart.getShoeId();
        int quantity = cart.getQuantity() - 1;
        if(quantity==0){
            db.removeCart(userId, shoeId);
            carts.remove(position);
        }
        else{
            db.updateCart(userId, shoeId, quantity);
            cart.setQuantity(quantity);
        }
        calculateTotalPrice();
        ca.notifyDataSetChanged();
    }

    @Override
    public void onCheckBoxClicked(int position) {
        Cart c = carts.get(position);
        c.setSelected(!c.isSelected());
        calculateTotalPrice();
        refreshSelected();
    }

    private void calculateTotalPrice(){
        Integer totalPrice = 0;
        for(Cart c: carts){
            if(c.isSelected()){
                int shoeId = c.getShoeId();
                Shoe shoe = db.getShoe(shoeId);
                int quantity = c.getQuantity();
                int price = shoe.getPrice();
                totalPrice += quantity * price;
            }
        }
        total.setText(totalPrice.toString());
    }

    private void refreshSelected(){
        selectedCarts.removeAll(selectedCarts);
        for (Cart cart : carts) {
            if (cart.isSelected()) {
                selectedCarts.add(cart);
            }
        }
    }
}