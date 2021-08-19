package com.ecommerce.dao;

import com.ecommerce.database.Database;
import com.ecommerce.entity.Product;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // Call DAO class to access other entities' database.
    AccountDao accountDao = new AccountDao();
    CategoryDao categoryDao = new CategoryDao();

    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
    }

    // Method to get blob image from database.
    private String getBase64Image(Blob blob) throws SQLException, IOException {
        InputStream inputStream = blob.getBinaryStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.getEncoder().encodeToString(imageBytes);
    }

    // Method to get all products from database.
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE product_is_deleted = false";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(4);
                String description = resultSet.getString(5);
                String category = categoryDao.getCategory(resultSet.getInt(6)).getName();
                String account = accountDao.getAccount(resultSet.getInt(7)).getName();
                boolean isDelete = resultSet.getBoolean(8);
                int amount = resultSet.getInt(9);

                // Get base64 image to display.
                Blob blob = resultSet.getBlob(3);
                String base64Image = getBase64Image(blob);

                list.add(new Product(id, name, base64Image, price, description, category, account, isDelete, amount));
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
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
                product.setBase64Image(getBase64Image(resultSet.getBlob(3)));
                product.setPrice(resultSet.getDouble(4));
                product.setDescription(resultSet.getString(5));
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
        return product;
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
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(4);
                String description = resultSet.getString(5);
                String category = categoryDao.getCategory(resultSet.getInt(6)).getName();
                String account = accountDao.getAccount(resultSet.getInt(7)).getName();
                boolean isDelete = resultSet.getBoolean(8);
                int amount = resultSet.getInt(9);

                // Get base64 image to display.
                Blob blob = resultSet.getBlob(3);
                String base64Image = getBase64Image(blob);

                list.add(new Product(id, name, base64Image, price, description, category, account, isDelete, amount));
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Method to search a product by a keyword.
    public List<Product> searchProduct(String keyword) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product WHERE product_name like '%" + keyword + "%'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(4);
                String description = resultSet.getString(5);
                String category = categoryDao.getCategory(resultSet.getInt(6)).getName();
                String account = accountDao.getAccount(resultSet.getInt(7)).getName();
                boolean isDelete = resultSet.getBoolean(8);
                int amount = resultSet.getInt(9);

                // Get base64 image to display.
                Blob blob = resultSet.getBlob(3);
                String base64Image = getBase64Image(blob);

                list.add(new Product(id, name, base64Image, price, description, category, account, isDelete, amount));
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
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
                double price = resultSet.getDouble(4);
                String description = resultSet.getString(5);
                String category = categoryDao.getCategory(resultSet.getInt(6)).getName();
                String account = accountDao.getAccount(resultSet.getInt(7)).getName();
                boolean isDelete = resultSet.getBoolean(8);
                int amount = resultSet.getInt(9);

                // Get base64 image to display.
                Blob blob = resultSet.getBlob(3);
                String base64Image = getBase64Image(blob);

                list.add(new Product(id, name, base64Image, price, description, category, account, isDelete, amount));
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
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
    public void addProduct(String productName, InputStream productImage, double productPrice, String productDescription, int productCategory, int sellerId) {
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

    // Method to edit product in database.
    public void editProduct(int productId, String productName, InputStream productImage, double productPrice, String productDescription, int productCategory) {
        String query = "UPDATE product SET product_name = ?, product_image = ?, product_price = ?, product_description = ?, fk_category_id = ? WHERE product_id = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, productName);
            preparedStatement.setBinaryStream(2, productImage);
            preparedStatement.setDouble(3, productPrice);
            preparedStatement.setString(4, productDescription);
            preparedStatement.setInt(5, productCategory);
            preparedStatement.setInt(6, productId);

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to get 12 products to display on each page.
    public List<Product> get12ProductsOfPage(int index) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product LIMIT " + ((index - 1) * 12) + ", 12";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(4);
                String description = resultSet.getString(5);
                String category = categoryDao.getCategory(resultSet.getInt(6)).getName();
                String account = accountDao.getAccount(resultSet.getInt(7)).getName();
                boolean isDelete = resultSet.getBoolean(8);
                int amount = resultSet.getInt(9);

                // Get base64 image to display.
                Blob blob = resultSet.getBlob(3);
                String base64Image = getBase64Image(blob);

                list.add(new Product(id, name, base64Image, price, description, category, account, isDelete, amount));
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // Method to get total products in database.
    public int getTotalNumberOfProducts() {
        int totalProduct = 0;
        String query = "SELECT COUNT(*) FROM product";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalProduct = resultSet.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalProduct;
    }

    // Method to update new amount of products.
    public void updateProductAmount(int productId, int productAmount) {
        String query = "UPDATE product SET product_amount = product_amount - ? WHERE product_id = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productAmount);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
