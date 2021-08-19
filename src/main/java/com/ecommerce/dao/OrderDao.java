package com.ecommerce.dao;

import com.ecommerce.database.Database;
import com.ecommerce.entity.CartProduct;

import java.sql.*;
import java.util.List;

public class OrderDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // Method to get last order id in database.
    public int getLastOrderId() {
        String query = "SELECT order_id FROM `order` ORDER BY order_id DESC LIMIT 1";
        int orderId = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return orderId;
    }

    // Method to insert order information to database.
    public void createOrder(int accountId, double totalPrice, List<CartProduct> cartProducts) {
        connection = new Database().getConnection();
        String query = "INSERT INTO `order` (fk_account_id, order_total) VALUES (?, ?);";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountId);
            preparedStatement.setDouble(2, totalPrice);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Create order catch:");
            System.out.println(e.getMessage());
        }

        String query2 = "INSERT INTO order_detail (fk_order_id, fk_product_id, product_quantity, product_price) VALUES (?, ?, ?, ?);";
        // Get latest orderId to insert list of cartProduct to order.
        int orderId = getLastOrderId();
        // Call ProductDao class to update product amount.
        ProductDao productDao = new ProductDao();
        for (CartProduct cartProduct : cartProducts) {
            productDao.updateProductAmount(cartProduct.getProduct().getId(), cartProduct.getQuantity());
            try {
                Class.forName("com.mysql.jdbc.Driver");
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, cartProduct.getProduct().getId());
                preparedStatement.setInt(3, cartProduct.getQuantity());
                preparedStatement.setDouble(4, cartProduct.getPrice());
                preparedStatement.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Create order_detail catch:");
                System.out.println(e.getMessage());
            }
        }
    }
}