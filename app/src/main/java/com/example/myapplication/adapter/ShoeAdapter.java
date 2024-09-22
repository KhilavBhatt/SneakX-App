package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.OnItemClickListener;
import com.example.myapplication.model.Shoe;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ViewHolder> {

    private ArrayList<Shoe> shoes;
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public ShoeAdapter(ArrayList<Shoe> shoes){
        this.shoes = shoes;
    }


    @NonNull
    @Override
    public ShoeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeAdapter.ViewHolder holder, int position) {
        holder.name.setText(shoes.get(position).getName());
        holder.price.setText(shoes.get(position).getPrice().toString());
        String url = shoes.get(position).getImage();
        Picasso.get().load(url).into(holder.imgv);
    }

    @Override
    public int getItemCount() {
        return shoes.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgv;
        TextView name,  price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv = itemView.findViewById(R.id.imgv);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Shoe clickedItem = shoes.get(position);
                            listener.onItemClick(clickedItem);
                        }
                    }
                }
            });
        }
    }
}
