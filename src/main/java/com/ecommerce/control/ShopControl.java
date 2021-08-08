package com.ecommerce.control;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.dao.DAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShopControl", value = "/shop")
public class ShopControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        // Get all products from database.
        List<Product> productList = dao.getAllProducts();
        // Get all categories from database.
        List<Category> categoryList = dao.getAllCategories();
        // Set attribute active class for home in header.
        String active = "active";

        request.setAttribute("shop_active", active);
        request.setAttribute("product_list", productList);
        request.setAttribute("category_list", categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
        requestDispatcher.forward(request, response);
    }
}
