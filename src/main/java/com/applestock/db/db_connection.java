package com.applestock.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class db_connection {
    private final static String URL = "jdbc:mysql://localhost:3306/apple_stock";
    private final static String db_user = "root";
    private final static String db_pass = "";

    private Connection connection;

    //? SET CONNECTION
    public db_connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, db_user, db_pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //? GET CONNECTION
    public Connection getConnection(){
        return this.connection;
    }

    //? CLOSE CONNECTION
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
