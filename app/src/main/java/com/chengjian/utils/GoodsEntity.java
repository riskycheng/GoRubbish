package com.chengjian.utils;

import android.graphics.drawable.Drawable;

public class GoodsEntity {
    private String code;
    private Drawable image;
    private String price;
    private String description;

    public GoodsEntity() {
    }

    public GoodsEntity(String code, Drawable image, String price, String description) {
        this.code = code;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
