package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchControl", value = "/search")
public class SearchControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        // Get the search keyword from request.
        String keyword = request.getParameter("keyword");
        // Get all products with the given keyword from database.
        List<Product> productList = dao.searchProduct(keyword);
        // Get all categories from database.
        List<Category> categoryList = dao.getAllCategories();

        request.setAttribute("product_list", productList);
        request.setAttribute("category_list", categoryList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
