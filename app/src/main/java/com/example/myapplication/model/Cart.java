package com.example.myapplication.model;

public class Cart {
    private Integer userId;
    private Integer shoeId;
    private Integer quantity;
    private boolean selected;


    public Cart(Integer userId, Integer shoeId, Integer quantity) {
        this.userId = userId;
        this.shoeId = shoeId;
        this.quantity = quantity;
        this.selected = false;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShoeId() {
        return shoeId;
    }

    public void setShoeId(Integer shoeId) {
        this.shoeId = shoeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
