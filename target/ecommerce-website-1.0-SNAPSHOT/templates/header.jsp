<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<header class="site-navbar" role="banner">
    <div class="site-navbar-top">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-6 col-md-4 order-2 order-md-1 site-search-icon text-left">
                    <form action="search" method="get" class="site-block-top-search">
                        <span class="icon icon-search2"></span>
                        <input name="keyword" type="text" class="form-control border-0" placeholder="Search">
                    </form>
                </div>

                <div class="col-12 mb-3 mb-md-0 col-md-4 order-1 order-md-2 text-center">
                    <div class="site-logo">
                        <a href="/" class="js-logo-clone">Shoppers</a>
                    </div>
                </div>

                <div class="col-6 col-md-4 order-3 order-md-3 text-right">
                    <div class="site-top-icons">
                        <ul>
                            <c:if test="${sessionScope.account != null}">
                                <li>
                                    <img class="icon" src="../static/images/person_1.jpg" id="dropdownMenuReference"
                                         data-toggle="dropdown" alt="image"
                                         style="width: 1.5em; border-radius: 50%; margin-right: 10px; padding-bottom: 10px">
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                                        <a class="dropdown-item" href="#">Your profile</a>
                                        <a class="dropdown-item" href="logout">Logout</a>
                                    </div>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account == null}">
                                <li><a href="../login.jsp"><span class="icon icon-person"></span></a></li>
                            </c:if>
                            <li><a href="#"><span class="icon icon-heart-o"></span></a></li>
                            <li>
                                <a href="cart" class="site-cart">
                                    <span class="icon icon-shopping_cart"></span>
                                    <span class="count">2</span>
                                </a>
                            </li>
                            <li class="d-inline-block d-md-none ml-md-0"><a href="#"
                                                                            class="site-menu-toggle js-menu-toggle"><span
                                    class="icon-menu"></span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <nav class="site-navigation text-right text-md-center" role="navigation">
        <div class="container">
            <ul class="site-menu js-clone-nav d-none d-md-block">
                <li class="${home_active}"><a href="/">Home</a></li>
                <li class="${about_active}"><a href="../about.jsp">About</a></li>
                <li class="${shop_active}"><a href="shop">Shop</a></li>
                <li class="${contact_active}"><a href="../contact.jsp">Contact</a></li>

                <c:if test="${sessionScope.account.isSeller == 1}">
                    <li class="${productm_active}"><a href="product-management">Products management</a></li>
                </c:if>

                <c:if test="${sessionScope.account.isAdmin == 1}">
                    <li class="${websitem_active}"><a href="#">Website management</a></li>
                </c:if>
            </ul>
        </div>
    </nav>
</header>
