package com.example.myapplication.model;

public class Shoe {

    private Integer shoeID;
    private String name;
    private Integer price;
    private String image;

    public Shoe(Integer shoeID, String name, Integer price, String image) {
        this.shoeID = shoeID;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Integer getShoeID() {
        return shoeID;
    }

    public void setShoeID(Integer shoeID) {
        this.shoeID = shoeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
