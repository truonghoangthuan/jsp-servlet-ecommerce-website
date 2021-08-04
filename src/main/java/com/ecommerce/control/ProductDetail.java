package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductDetail", value = "/product-detail")
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = dao.getProduct(id);

        request.setAttribute("product", product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product-detail.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
