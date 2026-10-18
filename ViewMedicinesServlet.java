package com.pharmacy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewMedicines")
public class ViewMedicinesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Medicines</title>");
        out.println("<style>");
        out.println("body{font-family:Arial;margin:30px;background:#f4f7f9;}");
        out.println("table{border-collapse:collapse;width:100%;background:white;}");
        out.println("th,td{border:1px solid #ccc;padding:12px;text-align:left;}");
        out.println("th{background:#087f5b;color:white;}");
        out.println("h2{color:#087f5b;}");
        out.println(".delete{background:#dc3545;color:white;padding:7px 12px;border:0;border-radius:4px;cursor:pointer;}");
        out.println("</style></head><body>");

        out.println("<h2>Medicine Inventory</h2>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Name</th><th>Category</th><th>Price</th><th>Quantity</th><th>Action</th></tr>");

        String sql = "SELECT medicine_id, medicine_name, category, price, quantity FROM medicines";

        try (Connection con = JDBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("medicine_id");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("medicine_name") + "</td>");
                out.println("<td>" + rs.getString("category") + "</td>");
                out.println("<td>" + rs.getBigDecimal("price") + "</td>");
                out.println("<td>" + rs.getInt("quantity") + "</td>");
                out.println("<td><form method='post' action='deleteMedicine' onsubmit=\"return confirm('Delete this medicine?');\">");
                out.println("<input type='hidden' name='medicine_id' value='" + id + "'>");
                out.println("<button class='delete' type='submit'>Delete</button>");
                out.println("</form></td>");
                out.println("</tr>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<tr><td colspan='6'>Unable to load medicines. Check the Eclipse Console.</td></tr>");
        }

        out.println("</table>");
        out.println("<p><a href='medicines.html'>Back to Add Medicine</a></p>");
        out.println("</body></html>");
    }
}