package com.ecommerce.entity;

import java.util.List;

public class Order {
    private int id;
    private Account account;
    private List<CartProduct> cartProducts;

    public Order() {
    }

    public Order(int id, Account account, List<CartProduct> item) {
        this.id = id;
        this.account = account;
        this.cartProducts = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
