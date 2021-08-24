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
                <div class="col-md-12 mb-0">
                    <a href="/">Home</a> <span class="mx-2 mb-0">/</span>
                    <strong class="text-black">Profile</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-12">
                            <h2 class="h3 mb-3 text-black">Profile image</h2>

                            <div class="p-3 border">
                                <c:if test="${account.base64Image != null}">
                                    <img class="icon" src="data:image/jpg;base64,${account.base64Image}"
                                         id="dropdownMenuReference"
                                         data-toggle="dropdown" alt="image"
                                         style="width: 20em; border-radius: 50%;">
                                </c:if>

                                <c:if test="${account.base64Image == null}">
                                    <img class="icon" src="../static/images/blank_avatar.png"
                                         id="dropdownMenuReference"
                                         data-toggle="dropdown" alt="image"
                                         style="width: 20em; border-radius: 50%;">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-8 mb-5 mb-md-0">
                    <h2 class="h3 mb-3 text-black">Profile information</h2>

                    <div class="p-3 p-lg-5 border">
                        <div class="form-group row">
                            <div class="col-md-6">
                                <label for="c_fname" class="text-black">
                                    First Name <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="c_fname" name="c_fname"
                                       value="${account.firstName}">
                            </div>
                            <div class="col-md-6">
                                <label for="c_lname" class="text-black">
                                    Last Name <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="c_lname" name="c_lname"
                                       value="${account.lastName}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-12">
                                <label for="c_address" class="text-black">
                                    Address <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="c_address" name="c_address"
                                       value="${account.address}">
                            </div>
                        </div>

                        <div class="form-group row mb-5">
                            <div class="col-md-6">
                                <label for="c_email_address" class="text-black">
                                    Email Address <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="c_email_address" name="c_email_address"
                                       value="${account.email}">
                            </div>
                            <div class="col-md-6">
                                <label for="c_phone" class="text-black">
                                    Phone <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="c_phone" name="c_phone"
                                       value="${account.phone}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="c_order_notes" class="text-black">
                                Order Notes
                            </label>

                            <textarea name="c_order_notes" id="c_order_notes" cols="30" rows="5" class="form-control"
                                      placeholder="Write your notes here..."></textarea>
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