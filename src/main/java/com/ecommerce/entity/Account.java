package com.ecommerce.entity;

public class Account {
    private int id;
    private String name;
    private String password;
    private int isSeller;
    private int isAdmin;

    private byte[] image;
    private String base64Image;

    public Account() {
    }

    public Account(int id, String name, String password, int isSeller, int isAdmin, String base64Image) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isSeller = isSeller;
        this.isAdmin = isAdmin;
        this.base64Image = base64Image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(int isSeller) {
        this.isSeller = isSeller;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
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

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
