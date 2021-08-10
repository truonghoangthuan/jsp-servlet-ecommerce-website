package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(name = "EditProductControl", value = "/edit-product")
@MultipartConfig
public class EditProductControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get request product from database.
        int productId = Integer.parseInt(request.getParameter("product-id"));
        // Get product from database.
        DAO dao = new DAO();
        Product product = dao.getProduct(productId);
        // Get category for product.
        List<Category> categoryList = dao.getAllCategories();

        request.setAttribute("product", product);
        request.setAttribute("category_list", categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("edit-product.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product id from request.
        int productId = Integer.parseInt(request.getParameter("product-id"));
        // Get product information from request.
        String productName = request.getParameter("product-name");
        double productPrice = Double.parseDouble((request.getParameter("product-price")));
        String productDescription = request.getParameter("product-description");
        int productCategory = Integer.parseInt(request.getParameter("product-category"));

        // Get upload image.
        Part part = request.getPart("product-image");
        InputStream inputStream = part.getInputStream();

        // Add product to database.
        DAO dao = new DAO();
        dao.editProduct(productId, productName, inputStream, productPrice, productDescription, productCategory);
        response.sendRedirect("product-management");
    }
}
