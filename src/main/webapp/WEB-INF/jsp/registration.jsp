<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">

    <style>
        <%@include file="../css/registration.css"%>
    </style>
</head>

<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Luggage-delivery">Luggage delivery</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Luggage-delivery">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/Luggage-delivery?cmd=make-order">Make order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">View my orders</a>
                </li>
            </ul>
        </div>
    </nav>
</header>

<body>
<section class="vh-100 bg-image image-background">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 15px;">
                        <div class="card-body p-5">
                            <h2 class="text-uppercase text-center mb-5">Create an account</h2>

                            <form method="post" action="Luggage-delivery">

                                <input type="hidden" name="cmd" value="addNewUser">
                                <div class="form-outline mb-4">
                                    <input type="text" class="form-control form-control-lg"
                                           placeholder="Your first name" required/>
                                </div>

                                <div class="form-outline mb-4">
                                    <input type="text" class="form-control form-control-lg"
                                           placeholder="Your last name" required/>
                                </div>

                                <div class="form-outline mb-4">
                                    <input type="email" class="form-control form-control-lg"
                                           placeholder="Your email" required/>
                                </div>

                                <div class="form-outline mb-4">
                                    <input type="password" class="form-control form-control-lg"
                                           placeholder="Enter your password" required/>
                                </div>

                                <div class="form-outline mb-4">
                                    <input type="password" class="form-control form-control-lg"
                                           placeholder="Repeat your password" required/>
                                </div>

                                <div class="form-check d-flex justify-content-center mb-5">
                                    <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3cg"/>
                                    <label class="form-check-label" for="form2Example3cg">
                                        I agree all statements in <a href="#!" class="text-body"><u>Terms of service</u></a>
                                    </label>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <input type="submit"
                                           class="btn btn-success btn-block btn-lg gradient-custom-4 text-body"
                                           value="Registrate">
                                </div>

                                <p class="text-center text-muted mt-5 mb-0">Have already an account?
                                    <a href="${pageContext.request.contextPath}/Luggage-delivery?cmd=authorize"
                                       class="fw-bold text-body">
                                        <u>Login here</u>
                                    </a>
                                </p>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>