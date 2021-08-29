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
            <form class="row" action="profile-page" method="post" enctype="multipart/form-data">
                <div class="col-md-4">
                    <div class="row">
                        <div class="col-md-12">
                            <h2 class="h3 mb-3 text-black">Profile image</h2>

                            <div class="p-3 border d-flex justify-content-center">
                                <label class="m-0" for="imgInp">
                                    <figure class="d-flex justify-content-center m-0">
                                        <c:if test="${account.base64Image != null}">
                                            <img class="icon" src="data:image/jpg;base64,${account.base64Image}"
                                                 id="blah"
                                                 data-toggle="dropdown" alt="image"
                                                 style="width: 15em; height: 15em; border-radius: 50%;">
                                        </c:if>

                                        <c:if test="${account.base64Image == null}">
                                            <img class="icon" src="../static/images/blank_avatar.png"
                                                 id="blah"
                                                 data-toggle="dropdown" alt="image"
                                                 style="width: 15em; height: 15em; border-radius: 50%;">
                                        </c:if>
                                    </figure>

                                    <figcaption style="text-align: center">
                                        Click here to change profile image
                                    </figcaption>
                                </label>

                                <input name="profile-image" type="file" id="imgInp" style="display: none;">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-8 mb-5 mb-md-0">
                    <h2 class="h3 mb-3 text-black">Profile Information</h2>

                    <div class="p-3 p-lg-5 border">
                        <div class="form-group row">
                            <div class="col-md-6">
                                <label for="first-name" class="text-black">
                                    First Name <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="first-name" name="first-name"
                                       value="${account.firstName}">
                            </div>

                            <div class="col-md-6">
                                <label for="last-name" class="text-black">
                                    Last Name <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="last-name" name="last-name"
                                       value="${account.lastName}">
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-12">
                                <label for="address" class="text-black">
                                    Address <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="address" name="address"
                                       value="${account.address}">
                            </div>
                        </div>

                        <div class="form-group row mb-5">
                            <div class="col-md-6">
                                <label for="email" class="text-black">
                                    Email Address <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="email" name="email"
                                       value="${account.email}">
                            </div>
                            <div class="col-md-6">
                                <label for="phone" class="text-black">
                                    Phone <span class="text-danger">*</span>
                                </label>

                                <input type="text" class="form-control" id="phone" name="phone"
                                       value="${account.phone}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="summary" class="text-black">
                                Summary
                            </label>

                            <textarea name="summary" id="summary" cols="30" rows="5" class="form-control"
                                      placeholder="Write your notes here..."></textarea>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-lg py-3 btn-block">Update profile</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="templates/footer.jsp"/>
</div>


<script>
    imgInp.onchange = evt => {
        const [file] = imgInp.files
        if (file) {
            blah.src = URL.createObjectURL(file)
        }
    }
</script>
<jsp:include page="templates/scripts.jsp"/>
</body>
</html>