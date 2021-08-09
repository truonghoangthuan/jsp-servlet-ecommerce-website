package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "EditProductControl", value = "/edit-product")
public class EditProductControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product id from request.
        int productId = Integer.parseInt(request.getParameter("product-id"));
        System.out.println(productId);
        // Get product information from request.
        String productName = request.getParameter("product-name");
        Double productPrice = Double.valueOf(request.getParameter("product-price"));
        String productDescription = request.getParameter("product-description");
        int productCategory = Integer.parseInt(request.getParameter("product-category"));

        // Get upload image.
        Part part = request.getPart("product-image");
        InputStream inputStream = part.getInputStream();

        // Get the seller id for product.
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int sellerId = account.getId();

        // Add product to database.
        DAO dao = new DAO();
        dao.editProduct(productId, productName, inputStream, productPrice, productDescription, productCategory, sellerId);
        response.sendRedirect("product-management");
    }
}
