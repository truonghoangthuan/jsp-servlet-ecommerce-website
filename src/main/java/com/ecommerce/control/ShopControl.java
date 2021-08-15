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
        // Get page number from request.
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }

        DAO dao = new DAO();
        // Get 9 products from database to display on each page.
        List<Product> productList = dao.get9ProductsOfPage(Integer.parseInt(index));

        // Get all categories from database.
        List<Category> categoryList = dao.getAllCategories();

        // Get total products to count pages.
        int totalProduct = dao.getAmountOfTotalProducts();
        int totalPages = totalProduct / 10;
        if (totalProduct % 10 != 0) {
            totalPages++;
        }

        // Set attribute active class for home in header and page number.
        String active = "active";

        request.setAttribute("product_list", productList);
        request.setAttribute("category_list", categoryList);
        request.setAttribute("total_pages", totalPages);
        request.setAttribute("shop_active", active);
        request.setAttribute("page_active", index);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
        requestDispatcher.forward(request, response);
    }
}
