package com.ecommerce.control;

import com.ecommerce.dao.CategoryDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShopControl", value = "/shop")
public class ShopControl extends HttpServlet {
    // Call DAO class to access with database.
    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get page number from request.
        String index = request.getParameter("index");
        if (index == null) {
            index = "1";
        }

        // Get 12 products from database to display on each page.
        List<Product> productList = productDao.get12ProductsOfPage(Integer.parseInt(index));

        // Get all categories from database.
        List<Category> categoryList = categoryDao.getAllCategories();

        // Get total products to count pages.
        int totalProduct = productDao.getTotalNumberOfProducts();
        int totalPages = totalProduct / 12;
        if (totalProduct % 12 != 0) {
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
