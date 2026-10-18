package com.pharmacy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addMedicine")
public class AddMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("medicine_name");
        String category = request.getParameter("category");
        String priceText = request.getParameter("price");
        String quantityText = request.getParameter("quantity");

        try {
            if (name == null || name.trim().isEmpty()
                    || category == null || category.trim().isEmpty()
                    || priceText == null || priceText.trim().isEmpty()
                    || quantityText == null || quantityText.trim().isEmpty()) {

                response.getWriter().println("Please fill in all fields.");
                return;
            }

            double price = Double.parseDouble(priceText);
            int quantity = Integer.parseInt(quantityText);

            if (price <= 0 || quantity < 0) {
                response.getWriter().println(
                    "Price must be greater than zero and quantity cannot be negative.");
                return;
            }

            String sql = "INSERT INTO medicines "
                    + "(medicine_name, category, price, quantity) "
                    + "VALUES (?, ?, ?, ?)";

            try (Connection con = JDBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, name.trim());
                ps.setString(2, category.trim());
                ps.setDouble(3, price);
                ps.setInt(4, quantity);

                int rows = ps.executeUpdate();
                System.out.println("ADD MEDICINE SERVLET RUNNING");
                System.out.println("Rows inserted: " + rows);
                System.out.println("Database: " + con.getCatalog());

                if (rows == 1) {
                    response.getWriter().println(
                        "<h2>Medicine added successfully!</h2>");
                    response.getWriter().println(
                        "<p>Medicine: " + name + "</p>");
                    response.getWriter().println(
                        "<a href='medicines.html'>Back to Medicines</a>");
                } else {
                    response.getWriter().println(
                        "Medicine was not saved. Please try again.");
                }
            }

        } catch (NumberFormatException e) {
            response.getWriter().println(
                "Enter a valid price and quantity.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(
                "<h3>Database error: "
                + e.getClass().getSimpleName()
                + "</h3><p>"
                + e.getMessage()
                + "</p>");
        }
    }
}