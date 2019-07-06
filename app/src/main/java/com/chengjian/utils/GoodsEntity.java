package com.chengjian.utils;

import android.graphics.drawable.Drawable;

public class GoodsEntity {
    private String code;
    private Drawable image;
    private String imgLink;
    private double price;
    private String description;

    public GoodsEntity() {
    }

    public GoodsEntity(String code, Drawable image, String imgLink, double price, String description) {
        this.code = code;
        this.image = image;
        this.imgLink = imgLink;
        this.price = price;
        this.description = description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                "code='" + code + '\'' +
                ", image=" + image +
                ", imgLink='" + imgLink + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
