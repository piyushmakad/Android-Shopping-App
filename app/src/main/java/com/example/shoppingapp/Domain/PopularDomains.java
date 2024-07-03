package com.example.shoppingapp.Domain;

import java.io.Serializable;

public class PopularDomains implements Serializable {
    private String picUrl;
    private String title;
    private int review;
    private double score;
    private double price;
    private String description;

    private int NumberInCart;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberInCart() {
        return NumberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        NumberInCart = numberInCart;
    }


    public PopularDomains(String picUrl, String title, int review, double score, double price,String description) {
        this.picUrl = picUrl;
        this.title = title;
        this.review = review;
        this.score = score;
        this.price = price;
        this.description = description;
    }
}
