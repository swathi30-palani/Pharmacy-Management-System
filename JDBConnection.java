
package com.pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBConnection {

    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/pharmacy_db",
            "root",
            "Swathi@123"
        );

        return con;
    }
}