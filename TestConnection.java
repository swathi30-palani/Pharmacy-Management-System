
package com.pharmacy;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try (Connection con = JDBConnection.getConnection()) {

            if (con != null && !con.isClosed()) {
                System.out.println("MySQL connected successfully!");
                System.out.println("Database: pharmacy_db");
            }

        } catch (Exception e) {
            System.out.println("MySQL connection failed!");
            e.printStackTrace();
        }
    }
}
