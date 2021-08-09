package com.ecommerce.dao;

import com.ecommerce.database.Database;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // Method to get all products from database.
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product";
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
        String query = "SELECT * FROM product WHERE product_id = " + id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setImage(resultSet.getBytes(3));
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
        String query = "SELECT * FROM product WHERE fk_category_id = " + category_id;
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
        String query = "SELECT * FROM product WHERE " +
                "product_name like '%" + keyword + "%'";
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

    // Method to get login account from database.
    public Account checkLoginAccount(String username, String password) {
        Account account = new Account();
        String query = "SELECT * FROM account WHERE account_name = '" + username + "' AND account_password = '" + password + "'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                account.setId(resultSet.getInt(1));
                account.setName(resultSet.getString(2));
                account.setPassword(resultSet.getString(3));
                account.setIsSeller(resultSet.getInt(4));
                account.setIsAdmin(resultSet.getInt(5));
                return account;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Method to check is username exist or not.
    public boolean checkUsernameExists(String username) {
        String query = "SELECT * FROM account WHERE account_name = '" + username + "'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // Method to create an account.
    public void createAccount(String username, String password) {
        String query = "INSERT INTO account (account_name, account_password, account_is_seller, account_is_admin)\n" +
                "VALUES ('" + username + "', '" + password + "', 0, 0)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to get all products of a seller.
    public List<Product> getSellerProducts(int sellerId) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE fk_account_id = " + sellerId;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Blob blob = resultSet.getBlob(3);
                Double price = resultSet.getDouble(4);
                String description = resultSet.getString(5);

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while (true) {
                    try {
                        if (!((bytesRead = inputStream.read(buffer)) != -1)) break;
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                byte[] imageBytes = byteArrayOutputStream.toByteArray();

                String base64image = Base64.getEncoder().encodeToString(imageBytes);

                list.add(new Product(id, name, base64image, price, description));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Method to remove a product from database by its id.
    public void removeProduct(int productId) {
        String query = "DELETE FROM product WHERE product_id = " + productId;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to add product to database.
    public void addProduct(String productName, InputStream productImage, Double productPrice, String productDescription, int productCategory, int sellerId) {
        String query = "INSERT INTO product (product_name, product_image, product_price, product_description, fk_category_id, fk_account_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, productName);
            preparedStatement.setBinaryStream(2, productImage);
            preparedStatement.setDouble(3, productPrice);
            preparedStatement.setString(4, productDescription);
            preparedStatement.setInt(5, productCategory);
            preparedStatement.setInt(6, sellerId);

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        System.out.println(dao.checkUsernameExists("truonghoangthuan"));
    }
}
