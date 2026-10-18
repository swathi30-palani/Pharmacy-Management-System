
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

@WebServlet("/searchMedicine")
public class SearchMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Search Medicine</title>");
        out.println("<style>");
        out.println("body{font-family:Arial;margin:30px;background:#f4f7f9;}");
        out.println("input,button{padding:10px;margin:5px;}");
        out.println("table{border-collapse:collapse;width:100%;background:white;margin-top:20px;}");
        out.println("th,td{border:1px solid #ccc;padding:12px;text-align:left;}");
        out.println("th{background:#087f5b;color:white;}");
        out.println("</style></head><body>");

        out.println("<h2>Search Medicine</h2>");
        out.println("<form method='get' action='searchMedicine'>");
        out.println("<input type='text' name='medicine_name' placeholder='Enter medicine name' required>");
        out.println("<button type='submit'>Search</button>");
        out.println("</form>");

        String name = request.getParameter("medicine_name");

        if (name != null && !name.trim().isEmpty()) {
            String sql = "SELECT medicine_id, medicine_name, category, price, quantity "
                       + "FROM medicines WHERE medicine_name LIKE ?";

            try (Connection con = JDBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, "%" + name.trim() + "%");

                try (ResultSet rs = ps.executeQuery()) {
                    out.println("<table>");
                    out.println("<tr><th>ID</th><th>Name</th><th>Category</th><th>Price</th><th>Quantity</th></tr>");

                    boolean found = false;

                    while (rs.next()) {
                        found = true;
                        out.println("<tr>");
                        out.println("<td>" + rs.getInt("medicine_id") + "</td>");
                        out.println("<td>" + rs.getString("medicine_name") + "</td>");
                        out.println("<td>" + rs.getString("category") + "</td>");
                        out.println("<td>" + rs.getBigDecimal("price") + "</td>");
                        out.println("<td>" + rs.getInt("quantity") + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</table>");

                    if (!found) {
                        out.println("<p>No matching medicine found.</p>");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Search failed. Check the Eclipse Console.</p>");
            }
        }

        out.println("<p><a href='viewMedicines'>Back to Medicine Inventory</a></p>");
        out.println("<p><a href='medicines.html'>Back to Add Medicine</a></p>");
        out.println("</body></html>");
    }
}
