package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CheckoutControl", value = "/checkout")
public class CheckoutControl extends HttpServlet {
    // Call DAO class to access with database.
    DAO dao = new DAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
        }
        else {
//            double totalPrice = (double) session.getAttribute("total_price");
//            Order order = (Order) session.getAttribute("order");
//            Account account = (Account) session.getAttribute("account");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("checkout.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
