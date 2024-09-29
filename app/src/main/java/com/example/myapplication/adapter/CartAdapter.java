package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.Database;
import com.example.myapplication.interfaces.CartButtonListener;
import com.example.myapplication.model.Cart;
import com.example.myapplication.model.Shoe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<Cart> carts;
    private Context context;
    private CartButtonListener cartButtonListener;
    Database database;


    public CartAdapter(ArrayList<Cart> carts, Context context, CartButtonListener cartButtonListener) {
        this.carts = carts;
        this.context = context;
        this.cartButtonListener = cartButtonListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = carts.get(position);
        Shoe shoe = Database.getInstance(context).getShoe(cart.getShoeId());
        holder.name.setText(shoe.getName());
        holder.price.setText(shoe.getPrice().toString());
        holder.quantity.setText(cart.getQuantity().toString());
        String url = shoe.getImage();
        Picasso.get().load(url).into(holder.imgv);
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgv;
        TextView name, price, quantity;
        ImageButton add, remove;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv = itemView.findViewById(R.id.imgv);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.qty);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);
            checkBox = itemView.findViewById(R.id.checkBox);
            add.setTag(getAdapterPosition());
            remove.setTag(getAdapterPosition());
            add.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    cartButtonListener.onAddClicked(position);
                }
            });

            remove.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    cartButtonListener.onRemoveClicked(position);
                }
            });

            checkBox.setOnCheckedChangeListener(((buttonView, isChecked) -> {
                int i = getAdapterPosition();
                if(i != RecyclerView.NO_POSITION){
                    cartButtonListener.onCheckBoxClicked(i);
                }
            }));

        }
    }
}
