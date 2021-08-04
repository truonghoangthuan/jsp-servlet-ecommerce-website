package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Product;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "HomeControl", value = "")
public class HomeControl extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProducts();

        request.setAttribute("list_products", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}