package com.ecommerce.control;

import com.ecommerce.dao.DAO;
import com.ecommerce.entity.CartProduct;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartControl", value = "/cart")
public class CartControl extends HttpServlet {
    // Call DAO class to access with database.
    DAO dao = new DAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantity = 1;
        int productId;
        if (request.getParameter("product-id") != null) {
            productId = Integer.parseInt(request.getParameter("product-id"));
            Product product = dao.getProduct(productId);
            if (product != null) {
                if (request.getParameter("quantity") != null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
                HttpSession session = request.getSession();
                if (session.getAttribute("order") == null) {
                    // Create an order and list of product for it.
                    Order order = new Order();
                    List<CartProduct> list = new ArrayList<>();

                    // Create a product and its quantity for an order.
                    CartProduct cartProduct = new CartProduct();
                    cartProduct.setQuantity(quantity);
                    cartProduct.setProduct(product);
                    cartProduct.setPrice(product.getPrice());
                    // Add product to list.
                    list.add(cartProduct);

                    // Add list of cart products to order.
                    order.setCartProducts(list);

                    session.setAttribute("order", order);
                } else {
                    Order order = (Order) session.getAttribute("order");
                    List<CartProduct> list = order.getCartProducts();
                    boolean flag = false;
                    for (CartProduct cartProduct : list) {
                        if (cartProduct.getProduct().getId() == product.getId()) {
                            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
                            flag = true;
                        }
                    }
                    if (!flag) {
                        CartProduct cartProduct = new CartProduct();
                        cartProduct.setQuantity(quantity);
                        cartProduct.setProduct(product);
                        cartProduct.setPrice(product.getPrice());
                        list.add(cartProduct);
                    }
                    session.setAttribute("order", order);
                }
            }
            response.sendRedirect("cart.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
