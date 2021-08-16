<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/head.jsp"/>

<body>
<div class="site-wrap">
    <jsp:include page="templates/header.jsp"/>

    <div class="bg-light py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-0"><a href="/">Home</a> <span class="mx-2 mb-0">/</span> <a
                        href="cart.jsp">Cart</a> <span class="mx-2 mb-0">/</span> <strong
                        class="text-black">Checkout</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row mb-5">
                        <div class="col-md-12">
                            <h2 class="h3 mb-3 text-black">Your Order</h2>
                            <form class="p-3 p-lg-5 border" method="post" action="ckeckout-confirm">
                                <table class="table site-block-order-table mb-5">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center">Product</th>
                                        <th style="text-align: center">Price</th>
                                        <th style="text-align: center">Quantity</th>
                                        <th style="text-align: center">Total</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${order.cartProducts}" var="o">
                                        <tr>
                                            <td>
                                                <input name="product-name" class="form-control-plaintext h5 text-black"
                                                       value="${o.product.name}" style="text-align: center" readonly>
                                            </td>

                                            <td>
                                                <input name="product-price" class="form-control-plaintext h5 text-black"
                                                       value="${o.price}" style="text-align: center" readonly>
                                            </td>

                                            <td>
                                                <input name="product-quantity"
                                                       class="form-control-plaintext h5 text-black"
                                                       value="${o.quantity}" style="text-align: center" readonly>
                                            </td>

                                            <td>
                                                <input name="product-total" class="form-control-plaintext h5 text-black"
                                                       value="${o.price * o.quantity}" style="text-align: center"
                                                       readonly>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    <tr>
                                        <td class="text-black font-weight-bold" colspan="2"><strong>Order Total</strong></td>
                                        <td class="text-black font-weight-bold" colspan="2">
                                            <input name="order-total-price" class="form-control-plaintext h5 text-black"
                                                   value="${total_price}" style="text-align: center" readonly>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-lg py-3 btn-block">
                                        Place Order
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="templates/footer.jsp"/>
</div>

<jsp:include page="templates/scripts.jsp"/>
</body>
</html>