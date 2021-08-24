package com.ecommerce.control;

import com.ecommerce.dao.AccountDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "RegisterControl", value = "/register")
@MultipartConfig
public class RegisterControl extends HttpServlet {
    // Call DAO class to access with database.
    AccountDao accountDao = new AccountDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username and password from request.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");

        // Set default profile image for account.
        Part part = request.getPart("profile-image");
        InputStream inputStream = part.getInputStream();

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
        else if (accountDao.checkUsernameExists(username)) {
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
            accountDao.createAccount(username, password, inputStream);
            String alert = "<div class=\"alert alert-success wrap-input100\">\n" +
                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                    "                            Create account successfully!\n" +
                    "                        </p>\n" +
                    "                    </div>";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
