<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="templates/head.jsp"/>

<body>
<div class="site-wrap">
    <% request.setAttribute("about_active", "active"); %>
    <jsp:include page="templates/header.jsp"/>

    <div class="bg-light py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-0"><a href="index.jsp">Home</a> <span class="mx-2 mb-0">/</span> <strong
                        class="text-black">About</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section border-bottom" data-aos="fade">
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-6">
                    <div class="block-16">
                        <figure>
                            <img src="static/images/blog_1.jpg" alt="Image placeholder" class="img-fluid rounded">
                            <a href="https://vimeo.com/channels/staffpicks/93951774" class="play-button popup-vimeo">
                                <span class="ion-md-play"></span>
                            </a>
                        </figure>
                    </div>
                </div>

                <div class="col-md-1">

                </div>

                <div class="col-md-5">
                    <div class="site-section-heading pt-3 mb-4">
                        <h2 class="text-black">How We Started</h2>
                    </div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eius repellat, dicta at laboriosam,
                        nemo exercitationem itaque eveniet architecto cumque, deleniti commodi molestias repellendus
                        quos sequi hic fugiat asperiores illum. Atque, in, fuga excepturi corrupti error corporis
                        aliquam unde nostrum quas.</p>
                    <p>Accusantium dolor ratione maiores est deleniti nihil? Dignissimos est, sunt nulla illum autem in,
                        quibusdam cumque recusandae, laudantium minima repellendus.</p>

                </div>
            </div>
        </div>
    </div>

    <div class="site-section border-bottom" data-aos="fade">
        <div class="container">
            <div class="row justify-content-center mb-5">
                <div class="col-md-7 site-section-heading text-center pt-4">
                    <h2>The Team</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-lg-3">

                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="static/images/person_1.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Elizabeth Graham</h3>
                                <p class="block-38-subheading">CEO/Co-Founder</p>
                            </div>
                            <div class="block-38-body">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae aut minima nihil sit
                                    distinctio recusandae doloribus ut fugit officia voluptate soluta. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="static/images/person_2.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Jennifer Greive</h3>
                                <p class="block-38-subheading">Co-Founder</p>
                            </div>
                            <div class="block-38-body">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae aut minima nihil sit
                                    distinctio recusandae doloribus ut fugit officia voluptate soluta. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="static/images/person_3.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Patrick Marx</h3>
                                <p class="block-38-subheading">Marketing</p>
                            </div>
                            <div class="block-38-body">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae aut minima nihil sit
                                    distinctio recusandae doloribus ut fugit officia voluptate soluta. </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="block-38 text-center">
                        <div class="block-38-img">
                            <div class="block-38-header">
                                <img src="static/images/person_4.jpg" alt="Image placeholder" class="mb-4">
                                <h3 class="block-38-heading h4">Mike Coolbert</h3>
                                <p class="block-38-subheading">Sales Manager</p>
                            </div>
                            <div class="block-38-body">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Vitae aut minima nihil sit
                                    distinctio recusandae doloribus ut fugit officia voluptate soluta. </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="site-section site-section-sm site-blocks-1 border-0" data-aos="fade">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-truck"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">Free Shipping</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at iaculis quam. Integer
                            accumsan tincidunt fringilla.</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="100">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-refresh2"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">Free Returns</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at iaculis quam. Integer
                            accumsan tincidunt fringilla.</p>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 d-lg-flex mb-4 mb-lg-0 pl-4" data-aos="fade-up" data-aos-delay="200">
                    <div class="icon mr-4 align-self-start">
                        <span class="icon-help"></span>
                    </div>
                    <div class="text">
                        <h2 class="text-uppercase">Customer Support</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus at iaculis quam. Integer
                            accumsan tincidunt fringilla.</p>
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