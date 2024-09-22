package com.example.myapplication.interfaces;

public interface CartButtonListener {
    void onAddClicked(int position);
    void onRemoveClicked(int position);

    void onCheckBoxClicked(int position);
}
