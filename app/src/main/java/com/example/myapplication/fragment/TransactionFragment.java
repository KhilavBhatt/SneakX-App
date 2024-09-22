package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.TransactionAdapter;
import com.example.myapplication.database.Database;
import com.example.myapplication.helper.SessionManager;
import com.example.myapplication.model.Transaction;

import java.util.ArrayList;


public class TransactionFragment extends Fragment {

    private RecyclerView rcv;
    private TransactionAdapter ta;
    private ArrayList<Transaction> tr = new ArrayList<>();
    Database db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transaction, container, false);
        rcv = root.findViewById(R.id.rcv);
        db = Database.getInstance(requireContext());
        tr = db.getTransactions(SessionManager.getInstance(requireContext()).getUser().getUserId());
        ta = new TransactionAdapter(tr, requireContext());
        LinearLayoutManager lm = new LinearLayoutManager(requireContext());
        rcv.setLayoutManager(lm);
        rcv.setAdapter(ta);
        ta.notifyDataSetChanged();
        return root;
    }
}