package com.ecommerce.entity;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String category;
    private String account;
    private boolean isDelete;
    private int amount;

    private byte[] image;
    private String base64Image;


    public Product() {
    }

    public Product(int id, String name, String base64Image, double price, String description, String category, String account, boolean isDelete, int amount) {
        this.id = id;
        this.name = name;
        this.base64Image = base64Image;
        this.price = price;
        this.description = description;
        this.category = category;
        this.account = account;
        this.isDelete = isDelete;
        this.amount = amount;
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

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
