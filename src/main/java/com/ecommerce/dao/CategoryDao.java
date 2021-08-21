package com.ecommerce.dao;

import com.ecommerce.database.Database;
import com.ecommerce.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // Method to get category by id.
    public Category getCategory(int categoryId) {
        Category category = new Category();
        String query = "SELECT * FROM category WHERE category_id = " + categoryId;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return category;
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
                        resultSet.getString(2),
                        resultSet.getInt(3)
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Method to decrease amount of category product.
    public void decreaseCategoryProductAmount(int categoryId, int productAmount) {
        String query = "UPDATE category SET category_number_product = category_number_product - ? WHERE category_id = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = new Database().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productAmount);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
