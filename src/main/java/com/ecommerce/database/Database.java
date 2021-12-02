package com.ecommerce.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp-servlet-ecommerce-website", "root", "root");
            return conn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Database().getConnection());
    }
}
