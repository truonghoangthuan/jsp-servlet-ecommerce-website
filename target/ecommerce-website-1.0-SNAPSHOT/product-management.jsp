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
                <div class="col-md-12 mb-0"><a href="/">Home</a> <span class="mx-2 mb-0">/</span> <strong
                        class="text-black">Products management</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row mb-5">
                <form class="col-md-12" method="post">
                    <div class="site-blocks-table">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="product-price">ID</th>
                                <th class="product-name">Product name</th>
                                <th class="product-thumbnail">Image</th>
                                <th class="product-price">Price</th>
                                <th class="product-remove">Remove</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${product_list}" var="o">
                                <tr>
                                    <td>
                                        ${o.id}
                                    </td>

                                    <td class="product-name">
                                        <h2 class="h5 text-black">${o.name}</h2>
                                    </td>

                                    <td class="product-thumbnail">
                                        <img src="${o.image}" alt="Image" class="img-fluid">
                                    </td>

                                    <td>
                                        $${o.price}
                                    </td>

                                    <td><a href="#" class="btn btn-primary btn-sm">X</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </form>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="row mb-5">
                        <div class="col-md-6 mb-3 mb-md-0">
                            <button class="btn btn-primary btn-sm btn-block">Add product</button>
                        </div>

                        <div class="col-md-6">
                            <button class="btn btn-outline-primary btn-sm btn-block">Delete</button>
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