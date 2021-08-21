package com.ecommerce.control;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.CartProduct;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "CartControl", value = "/cart")
public class CartControl extends HttpServlet {
    // Call DAO class to access with database.
    ProductDao productDao = new ProductDao();

    // Method to remove a product from cart.
    private void removeCartProduct(int productId, Order order, double totalPrice) {
        // Get list of products from the existing order.
        List<CartProduct> list = order.getCartProducts();

        // Iterator.remove is the only safe way to modify a collection during iteration
        for (Iterator<CartProduct> iterator = list.iterator(); iterator.hasNext();) {
            // Get the cart product object from list.
            CartProduct cartProduct = iterator.next();

            // Delete the product if its id equals the id of deleting product.
            if (cartProduct.getProduct().getId() == productId) {
                // Remove price of deleting product from total price.
                totalPrice -= (cartProduct.getPrice() * cartProduct.getQuantity());

                // Remove product from cart.
                iterator.remove();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if request is remove product from cart or not.
        if (request.getParameter("remove-product-id") != null) {
            Order order = (Order) session.getAttribute("order");
            double totalPrice = (double) session.getAttribute("total_price");
            int productId = Integer.parseInt(request.getParameter("remove-product-id"));
            removeCartProduct(productId, order, totalPrice);
            response.sendRedirect("cart.jsp");
            return;
        }

        // Initialize default value for quantity and productId.
        int quantity = 1;
        int productId;
        // Check is the total price of order exist or not.
        double totalPrice;
        if (session.getAttribute("total_price") == null) {
            totalPrice = 0;
        } else {
            totalPrice = (double) session.getAttribute("total_price");
        }

        // Generate if product exist in database.
        if (request.getParameter("product-id") != null) {
            // Get the id of product from request.
            productId = Integer.parseInt(request.getParameter("product-id"));

            // Get product information from database.
            Product product = productDao.getProduct(productId);
            if (product != null) {
                // Get the quantity of the adding product.
                if (request.getParameter("quantity") != null) {
                    // Get the quantity of the product if the quantity is more than 1.
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                    // Check if the request quantity is more than the number of products left or not.
                    if (product.getAmount() - quantity < 0) {
                        response.sendRedirect("product-detail?id="+product.getId()+"&invalid-quantity=1");
                        return;
                    }
                }
                // Check the product has been added to cart yet.
                if (session.getAttribute("order") == null) {
                    // Create an order and list of product for it.
                    Order order = new Order();
                    List<CartProduct> list = new ArrayList<>();

                    // Create a product and its quantity for the order.
                    CartProduct cartProduct = new CartProduct();
                    cartProduct.setQuantity(quantity);
                    cartProduct.setProduct(product);
                    cartProduct.setPrice(product.getPrice());

                    // Count the total price of the order.
                    totalPrice += product.getPrice() * quantity;

                    // Add product to list.
                    list.add(cartProduct);

                    // Add list of cart products to order.
                    order.setCartProducts(list);

                    session.setAttribute("total_price", totalPrice);
                    session.setAttribute("order", order);
                } else {
                    // Get exist order from session.
                    Order order = (Order) session.getAttribute("order");
                    // Get the list of products from order.
                    List<CartProduct> list = order.getCartProducts();

                    // Increase the product quantity if it is already exist in cart.
                    boolean flag = false;
                    for (CartProduct cartProduct : list) {
                        if (cartProduct.getProduct().getId() == product.getId()) {
                            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
                            totalPrice += product.getPrice() * quantity;
                            flag = true;
                        }
                    }

                    // Add new product to existing cart.
                    if (!flag) {
                        CartProduct cartProduct = new CartProduct();
                        cartProduct.setQuantity(quantity);
                        cartProduct.setProduct(product);
                        cartProduct.setPrice(product.getPrice());
                        totalPrice += product.getPrice() * quantity;
                        list.add(cartProduct);
                    }

                    session.setAttribute("total_price", totalPrice);
                    session.setAttribute("order", order);
                }
            }
            response.sendRedirect("product-detail?id=" + productId);
        }
    }
}
