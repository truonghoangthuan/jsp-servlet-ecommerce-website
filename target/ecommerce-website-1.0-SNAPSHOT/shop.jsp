<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.ecommerce.dao.CategoryDao" %>
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
                <div class="col-md-12 mb-0"><a href="/">Home</a> <span class="mx-2 mb-0">/</span> <strong
                        class="text-black">Shop</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">

            <div class="row mb-5">
                <div class="col-md-9 order-2">

                    <div class="row">
                        <div class="col-md-12 mb-5">
                            <div class="float-md-left mb-4"><h2 class="text-black h5">Shop All</h2></div>
                            <div class="d-flex">
                                <div class="dropdown mr-1 ml-md-auto">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-secondary btn-sm dropdown-toggle"
                                                id="dropdownMenuReference" data-toggle="dropdown">Reference
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                                            <a class="dropdown-item" href="#">Relevance</a>
                                            <a class="dropdown-item" href="#">Name, A to Z</a>
                                            <a class="dropdown-item" href="#">Name, Z to A</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="#">Price, low to high</a>
                                            <a class="dropdown-item" href="#">Price, high to low</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-5">
                        <c:forEach items="${product_list}" var="o">
                            <div class="col-sm-6 col-lg-4 mb-4" data-aos="fade-up">
                                <div class="block-4 text-center border" style="height: 100%">
                                    <figure class="block-4-image">
                                        <a href="product-detail?id=${o.id}">
                                            <img src="data:image/jpg;base64,${o.base64Image}" alt="Image placeholder"
                                                 class="img-fluid" style="height: 100%">
                                        </a>
                                    </figure>
                                    <div class="block-4-text p-4">
                                        <h3><a href="product-detail?id=${o.id}">${o.name}</a></h3>
                                        <p class="mb-0">$${o.price}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row" data-aos="fade-up">
                        <div class="col-md-12 text-center">
                            <div class="site-block-27">
                                <ul>
                                    <c:if test="${page_active > 1}">
                                        <li><a href="shop?index=${page_active - 1}">&lt;</a></li>
                                    </c:if>

                                    <c:forEach begin="1" end="${total_pages}" var="i">
                                        <li class="${(page_active == i) ? "active" : " "}"><a
                                                href="shop?index=${i}">${i}</a></li>
                                    </c:forEach>

                                    <c:if test="${page_active < total_pages}">
                                        <li><a href="shop?index=${page_active + 1}">&gt;</a></li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 order-1 mb-5 mb-md-0">
                    <div class="border p-4 rounded mb-4">
                        <h3 class="mb-3 h6 text-uppercase text-black d-block">Categories</h3>
                        <ul class="list-unstyled mb-0">
                            <c:forEach items="${category_list}" var="o">
                                <li class="mb-1 active">
                                    <a href="category?category_id=${o.id}" class="d-flex">
                                        <span>${o.name}</span>
                                        <span class="text-black ml-auto">(${o.totalCategoryProduct})</span>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
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