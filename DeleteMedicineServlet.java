
package com.pharmacy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteMedicine")
public class DeleteMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String idText = request.getParameter("medicine_id");

        try {
            int id = Integer.parseInt(idText);

            String sql = "DELETE FROM medicines WHERE medicine_id = ?";

            try (Connection con = JDBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);
                ps.executeUpdate();
            }

            response.sendRedirect("viewMedicines");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println(
                "Unable to delete medicine. Check the Eclipse Console."
            );
        }
    }
}
