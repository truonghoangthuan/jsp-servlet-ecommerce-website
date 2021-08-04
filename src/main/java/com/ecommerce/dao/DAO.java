package com.ecommerce.dao;

import com.ecommerce.database.Database;
import com.ecommerce.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Product getProduct(int id) {
        Product product = new Product();
        String query = "SELECT * FROM products WHERE id = " + id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setImage(resultSet.getString(3));
                product.setPrice(resultSet.getDouble(4));
                product.setDescription(resultSet.getString(5));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProducts();
        for (Product p : list) {
            System.out.println(p);
        }
    }
}
