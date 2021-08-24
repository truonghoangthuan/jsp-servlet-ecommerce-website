package com.ecommerce.control;

import com.ecommerce.dao.AccountDao;
import com.ecommerce.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginControl", value = "/login")
public class LoginControl extends HttpServlet {
    // Call DAO class to access with database.
    AccountDao accountDao = new AccountDao();

    private Account getAccountCookie(HttpServletRequest request) {
        // Get list cookies of the browser.
        Cookie[] cookies = request.getCookies();

        Account account;
        String username = "";
        String password = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            }
            if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }
        account = accountDao.checkLoginAccount(username, password);
        return account;
    }

    private void executeLogin(HttpServletRequest request, HttpServletResponse response, Account account) throws IOException {
        // Get the status of remember me checkbox.
        HttpSession session = request.getSession();
        boolean rememberMe = (request.getParameter("remember-me-checkbox") != null);

        session.setAttribute("account", account);
        if (rememberMe) {
            Cookie usernameCookie = new Cookie("username", account.getUsername());
            usernameCookie.setMaxAge(600);
            response.addCookie(usernameCookie);

            Cookie passwordCookie = new Cookie("password", account.getPassword());
            passwordCookie.setMaxAge(600);
            response.addCookie(passwordCookie);
        }
        response.sendRedirect("/");
    }

    private void checkLoginAccountFirstTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check status is typed wrong input or not.
        String status="";
        if (request.getParameter("status") != null) {
            status = request.getParameter("status");
        }
        // Get the submitted username and password.
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check account in database.
        Account account = accountDao.checkLoginAccount(username, password);
        if (account == null && status.equals("typed")) {
            // An alert to send to login page.
            String alert = "<div class=\"alert alert-danger wrap-input100\">\n" +
                    "                        <p style=\"font-family: Ubuntu-Bold; font-size: 18px; margin: 0.25em 0; text-align: center\">\n" +
                    "                            Wrong username or password!\n" +
                    "                        </p>\n" +
                    "                    </div>";
            // Set attribute for alert tag in login.jsp page.
            request.setAttribute("alert", alert);
            // Resend to login page.
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (account == null) {
            // Send to login page if the user have not typed input yet.
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Login when all information are correct.
            executeLogin(request, response, account);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check the cookies of account.
        Account account = getAccountCookie(request);
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
