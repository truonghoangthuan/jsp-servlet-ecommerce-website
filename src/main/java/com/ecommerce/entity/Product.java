package com.ecommerce.entity;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.jdbc.BlobFromLocator;

import java.io.InputStream;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;

    private byte[] image;
    private InputStream inputStream;
    private String base64image;


    public Product() {
    }

    public Product(int id, String name, InputStream inputStream, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.inputStream = inputStream;
    }

    public Product(int id, String name, String base64image, double price, String description) {
        this.id = id;
        this.name = name;
        this.base64image = base64image;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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

    public String getBase64image() {
        return base64image;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
