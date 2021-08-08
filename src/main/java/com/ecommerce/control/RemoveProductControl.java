package com.ecommerce.control;

import com.ecommerce.dao.DAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveProduct", value = "/remove-product")
public class RemoveProductControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the id of the product that need to remove from request.
        int productId = Integer.parseInt(request.getParameter("product-id"));
        // Remove product from database.
        DAO dao = new DAO();
        dao.removeProduct(productId);

        response.sendRedirect("product-management");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
