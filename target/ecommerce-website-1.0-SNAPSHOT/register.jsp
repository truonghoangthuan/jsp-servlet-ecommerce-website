<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Sign up</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="static/images/logo.png"/>
    <link rel="stylesheet" type="text/css" href="static/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="static/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="static/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <link rel="stylesheet" type="text/css" href="static/vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="static/vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="static/vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="static/vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="static/vendor/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="static/css/util.css">
    <link rel="stylesheet" type="text/css" href="static/css/main.css">

    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/magnific-popup.css">
    <link rel="stylesheet" href="static/css/jquery-ui.css">
    <link rel="stylesheet" href="static/css/owl.carousel.min.css">
    <link rel="stylesheet" href="static/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="static/css/aos.css">
    <link rel="stylesheet" href="static/css/style.css">
</head>

<body>
<div class="limiter">
    <div class="container-login100">
        <div class="shadow-lg p-2 p-lg-5 rounded" data-aos="fade-up">
            <div class="wrap-login100 p-t-50 p-b-90">
                <form action="register" method="post"
                      class="login100-form validate-form flex-sb flex-w justify-content-center"
                      enctype="multipart/form-data">
                    <span class="login100-form-title m-b-20">
                        Create account
                    </span>

                    ${alert}

                    <div class="m-b-16">
                        <label class="m-0" for="imgInp">
                            <figure class="d-flex justify-content-center m-0">
                                <img id="blah" src="static/images/blank_avatar.png" alt="your image"
                                     style="border-radius: 50%; height: 8em; width: 8em">
                            </figure>
                            <figcaption>Click here to change profile image</figcaption>
                        </label>
                        <input name="profile-image" type="file" id="imgInp" style="display: none;">
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="Username is required">
                        <input class="input100" type="text" name="username" placeholder="Username">
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
                        <input class="input100" type="password" name="password" placeholder="Password">
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
                        <input class="input100" type="password" name="repeat-password"
                               placeholder="Repeat your password">
                        <span class="focus-input100"></span>
                    </div>

                    <div class="container-login100-form-btn m-t-17">
                        <button type="submit" class="login100-form-btn">
                            Sign up
                        </button>
                    </div>
                </form>
            </div>

            <div class="text-center">
                <p class="txt1" style="color: #999999">
                    Already have an account?
                    <a href="login.jsp" class="txt1">
                        Login here
                    </a>
                </p>
            </div>
        </div>
    </div>
</div>

<div id="dropDownSelect1"></div>

<script src="static/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="static/vendor/animsition/js/animsition.min.js"></script>
<script src="static/vendor/bootstrap/js/popper.js"></script>
<script src="static/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="static/vendor/select2/select2.min.js"></script>
<script src="static/vendor/daterangepicker/moment.min.js"></script>
<script src="static/vendor/daterangepicker/daterangepicker.js"></script>
<script src="static/vendor/countdowntime/countdowntime.js"></script>

<script src="static/js/jquery-3.3.1.min.js"></script>
<script src="static/js/jquery-ui.js"></script>
<script src="static/js/popper.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/owl.carousel.min.js"></script>
<script src="static/js/jquery.magnific-popup.min.js"></script>
<script src="static/js/aos.js"></script>
<script src="static/js/main.js"></script>

<script>
    imgInp.onchange = evt => {
        const [file] = imgInp.files
        if (file) {
            blah.src = URL.createObjectURL(file)
        }
    }
</script>
</body>
</html>
