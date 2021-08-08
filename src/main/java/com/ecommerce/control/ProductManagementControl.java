package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductManagementControl", value = "/product-management")
public class ProductManagementControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the seller id from session.
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int sellerId = account.getId();
        // Get products of seller from database.
        DAO dao = new DAO();
        List<Product> productList = dao.getSellerProducts(sellerId);
        // Get all category for category selection.
        List<Category> categoryList = dao.getAllCategories();
        // Set attribute active class for home in header.
        String active = "active";

        request.setAttribute("category_list", categoryList);
        request.setAttribute("product_list", productList);
        request.setAttribute("productm_active", active);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product-management.jsp");
        requestDispatcher.forward(request, response);
    }
}
