package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.database.Database;
import com.example.myapplication.model.Shoe;
import com.example.myapplication.model.Transaction;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private ArrayList<Transaction> tr;
    private Database db;
    public TransactionAdapter(ArrayList<Transaction> tr, Context context){
        this.tr = tr;
        this.db = Database.getInstance(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shoe shoe = db.getShoe(tr.get(position).getShoeId());
        Integer shoePrice = shoe.getPrice();
        Integer total = 0;
        total = shoePrice * tr.get(position).getQuantity();
        System.out.println(total);
        System.out.print(shoePrice);
        holder.name.setText(shoe.getName());
        holder.qty.setText(tr.get(position).getQuantity().toString());
        holder.total.setText("Total Price: " + total.toString());
        String url = shoe.getImage();
        Picasso.get().load(url).into(holder.imgv);

    }

    @Override
    public int getItemCount() {
        return tr.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView name, total, qty;
        ImageView imgv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            qty = itemView.findViewById(R.id.qty);
            total = itemView.findViewById(R.id.price);
            imgv = itemView.findViewById(R.id.imgv);

        }
    }


}
