package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "HomeControl", value = "")
public class HomeControl extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO dao = new DAO();
        // Get all products from database.
        List<Product> productList = dao.getAllProducts();
        // Get all categories from database.
        List<Category> categoryList = dao.getAllCategories();

        request.setAttribute("product_list", productList);
        request.setAttribute("category_list", categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
}