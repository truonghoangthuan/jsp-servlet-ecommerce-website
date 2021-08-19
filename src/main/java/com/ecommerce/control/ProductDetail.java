package com.ecommerce.control;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductDetail", value = "/product-detail")
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the id of selected product.
        int id = Integer.parseInt(request.getParameter("id"));

        ProductDao productDao = new ProductDao();
        // Get product from database with the given id.
        Product product = productDao.getProduct(id);
        // Get all products for feature section.
        List<Product> productList = productDao.getAllProducts();
        // Set attribute active class for home in header.
        String active = "active";

        request.setAttribute("shop_active", active);
        request.setAttribute("product", product);
        request.setAttribute("product_list", productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product-detail.jsp");
        requestDispatcher.forward(request, response);
    }
}
