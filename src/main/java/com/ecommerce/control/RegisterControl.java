package com.ecommerce.control;

import com.ecommerce.dao.DAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterControl", value = "/register")
public class RegisterControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username and password from request.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");

        DAO dao = new DAO();
        // Check password and repeatPassword are the same.
        if (!password.equals(repeatPassword)) {
            String alert = "<div class=\"alert alert-danger wrap-input100\">\n" +
                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                    "                            Incorrect password!\n" +
                    "                        </p>\n" +
                    "                    </div>";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        // Check username is existed or not from database.
        else if (dao.checkUsernameExists(username)) {
            String alert = "<div class=\"alert alert-danger wrap-input100\">\n" +
                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                    "                            Username already exist!\n" +
                    "                        </p>\n" +
                    "                    </div>";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        // Insert username, password to database and create account.
        else {
            dao.createAccount(username, password);
            String alert = "<div class=\"alert alert-success wrap-input100\">\n" +
                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                    "                            Create account successfully!\n" +
                    "                        </p>\n" +
                    "                    </div>";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
