<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">

    <style>
        <%@include file="../css/sign-in-form.css"%>
        .incorrect-data {
            border: red;
        }
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
                    <a class="nav-link active" href="${pageContext.request.contextPath}/Luggage-delivery">Home</a>
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
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <%--                            <div class="mb-md-5 mt-md-4 pb-5">--%>
                        <div class="incorrect-data">
                            <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                            <p class="text-white-50 mb-5">Please enter your login and password!</p>

                            <form action="Luggage-delivery" method="post">
                                <input type="hidden" name="cmd" value="user-check">
                                <div class="form-outline form-white mb-4 incorrect-data">
                                    <input type="email" id="typeEmailX" name="userEmail" minlength="10" maxlength="45"
                                           value="${requestScope.userEmail}"
                                            <c:choose>
                                                <c:when test="${requestScope.emailException eq null}">
                                                    placeholder="Input your email"
                                                </c:when>
                                                <c:when test="${requestScope.emailException ne null}">
                                                    placeholder="${requestScope.emailException}"
                                                    style="border: 1px solid red"
                                                </c:when>
                                            </c:choose>

                                           class="form-control form-control-lg" required/>
                                    <label class="form-label" for="typeEmailX">Email</label>
                                </div>

                                <div class="form-outline form-white mb-4">
                                    <input type="password" id="typePasswordX" name="userPass" minlength="4"
                                           maxlength="45"

                                            <c:choose>
                                                <c:when test="${requestScope.passwordException eq null}">
                                                    placeholder="Input your password"
                                                </c:when>
                                                <c:when test="${requestScope.passwordException ne null}">
                                                    placeholder="${requestScope.passwordException}"
                                                    style="border: 1px solid red"
                                                </c:when>
                                            </c:choose>

                                           class="form-control form-control-lg" required/>
                                    <label class="form-label" for="typePasswordX">Password</label>
                                </div>

                                <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a>
                                </p>
                                <button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
                            </form>
                        </div>
                        <div>
                            <p class="mb-0">Don't have an account? <a href="#!" class="text-white-50 fw-bold">Sign
                                Up</a>
                            </p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>