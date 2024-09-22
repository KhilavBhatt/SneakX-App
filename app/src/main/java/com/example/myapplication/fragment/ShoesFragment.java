package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.DetailActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ShoeAdapter;
import com.example.myapplication.database.Database;
import com.example.myapplication.interfaces.OnItemClickListener;
import com.example.myapplication.model.Shoe;

import java.util.ArrayList;

public class ShoesFragment extends Fragment {

    private RecyclerView rcv;
    private ShoeAdapter sa;
    private ArrayList<Shoe> shoes = new ArrayList<>();
    Database db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shoes, container, false);
        rcv = root.findViewById(R.id.rcv);
        db = Database.getInstance(requireContext());
        if(db.getShoes().isEmpty()){
            System.out.println("Data Not Fetched Yet");
        }
        else{
            shoes = db.getShoes();
            System.out.println("Shoes Total = " + shoes.size());
        }

        sa = new ShoeAdapter(shoes);
        sa.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Shoe shoe) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("shoeid", shoe.getShoeID());
                intent.putExtra("shoename", shoe.getName());
                intent.putExtra("shoeprice", shoe.getPrice().toString());
                intent.putExtra("shoeimage", shoe.getImage());
//                intent.putExtra("quantity", 0);
                startActivityForResult(intent, 1);
            }
        });
        GridLayoutManager lm = new GridLayoutManager(requireContext(), 2);
        rcv.setLayoutManager(lm);
        rcv.setAdapter(sa);
        sa.notifyDataSetChanged();
        return root;
    }
}