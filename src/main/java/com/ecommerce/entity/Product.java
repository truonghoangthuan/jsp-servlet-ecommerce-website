package com.ecommerce.entity;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private Category category;
    private Account account;
    private boolean isDeleted;
    private int amount;

    private byte[] image;
    private String base64Image;


    public Product() {
    }

    public Product(int id, String name, String base64Image, double price, String description, Category category, Account account, boolean isDeleted, int amount) {
        this.id = id;
        this.name = name;
        this.base64Image = base64Image;
        this.price = price;
        this.description = description;
        this.category = category;
        this.account = account;
        this.isDeleted = isDeleted;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", account=" + account +
                ", isDeleted=" + isDeleted +
                ", amount=" + amount +
                '}';
    }
}
