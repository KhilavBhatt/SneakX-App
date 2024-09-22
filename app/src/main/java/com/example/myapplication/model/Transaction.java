package com.example.myapplication.model;

public class Transaction {

    private Integer transactionId;
    private Integer userId;
    private Integer shoeId;
    private Integer quantity;

    public Transaction(Integer transactionId, Integer userId, Integer shoeId, Integer quantity) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.shoeId = shoeId;
        this.quantity = quantity;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
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
