package com.ecommerce.dao;

import com.ecommerce.database.Database;
import com.ecommerce.entity.Category;
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

    // Method to get all products from database.
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

    // Method to get a product by its id from database.
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

    // Method to get all categories from database.
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM category";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Category(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Method to get a categories by its id from database.
    public List<Product> getAllCategoryProducts(int category_id) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = " + category_id;
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
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Method to search a product by a keyword.
    public List<Product> searchProduct(String keyword) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name like '%" + keyword + "%'";
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
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllCategoryProducts(1);
        for (Product p : list) {
            System.out.println(p);
        }
    }
}
