package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginControl", value = "/login")
public class LoginControl extends HttpServlet {
    // Call DAO class to access with database.
    DAO dao = new DAO();

    private Account getAccountCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        Account account = null;
        String username = "";
        String password = "";
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals("username")) {
                System.out.println(cookie.getName() + " " + cookie.getValue());
                username = cookie.getValue();
            }
            if (cookie.getName().equals("password")) {
                System.out.println(cookie.getName() + " " + cookie.getValue());
                password = cookie.getValue();
            }
        }
        account = dao.checkLoginAccount(username, password);
        return account;
    }

    private void executeLogin(HttpServletRequest request, HttpServletResponse response, Account account) throws IOException {
        HttpSession session = request.getSession();
        // Get the submitted username and password.
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = (request.getParameter("remember-me-checkbox") != null);

        session.setAttribute("account", account);
        if (rememberMe) {
            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(300);
            response.addCookie(usernameCookie);

            Cookie passwordCookie = new Cookie("password", password);
            passwordCookie.setMaxAge(300);
            response.addCookie(passwordCookie);
        }
        response.sendRedirect("/");
    }

    private void checkLoginAccountFirstTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the submitted username and password.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = dao.checkLoginAccount(username, password);
        if (account == null) {
            String alert = "<div class=\"alert alert-danger wrap-input100\">\n" +
                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                    "                            Wrong username or password!\n" +
                    "                        </p>\n" +
                    "                    </div>";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            executeLogin(request, response, account);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check the cookies of account.
        Account account = getAccountCookie(request);
        System.out.println(account);
        if (account == null) {
            // Check if account login first time or not.
            checkLoginAccountFirstTime(request, response);
        } else {
            // Execute login if exist account cookie.
            executeLogin(request, response, account);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }
}
