package org.example.supermarketmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conx = DriverManager.getConnection("jdbc:mysql://localhost/market", "root", "");
            return conx;
        } catch (Exception e) {
            System.out.println("va allumer XAMPP! ");
            e.printStackTrace();
            return null;
        }

            //Dans mon cas
        /*private  static final String DB_URL="jdbc:mysql://localhost/market";
        private  static final String DB_USER="root";
        private  static final String PASSWORD ="";*/
    }

}