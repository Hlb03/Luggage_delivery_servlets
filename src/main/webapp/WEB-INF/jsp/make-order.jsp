<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Make order</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/carousel/">

    <script type="text/javascript">
        <%@include file="/WEB-INF/js/restict-date.js"%>
    </script>

    <style>
        <%@ include file="../css/make-order.css"%>
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
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/Luggage-delivery?cmd=make-order">Make order</a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.user eq null}">
                        <li class="nav-item">
                            <a class="nav-link disabled"
                               href="${pageContext.request.contextPath}/Luggage-delivery?cmd=user-room">View my
                                orders</a>
                        </li>
                    </c:when>
                    <c:when test="${sessionScope.user ne null}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/Luggage-delivery?cmd=user-room">View my
                                orders</a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
            <c:choose>
                <c:when test="${sessionScope.user eq null}">
                    <form class="form-inline mt-2 mt-md-0" action="Luggage-delivery"
                        style="position: relative; left: 1000px">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign in</button>
                        <input type="hidden" name="cmd" value="authorize"><br>
                </c:when>
                <c:when test="${sessionScope.user ne null}">
                    <form class="form-inline mt-2 mt-md-0" action="Luggage-delivery" method="post"
                          style="position: relative; left: 1000px">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
                        <input type="hidden" name="cmd" value="logout">
                    </c:when>
            </c:choose>
                    </form>
        </div>
    </nav>
</header>
<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  -->
<body>
<div class="container">
    <!--  &nbsp; -- means one space in html -->
    <div class="title">ORDER
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;
<%--                        Total price - 330.12--%>
        <c:if test="${requestScope.totalPrice ne null}">
            Total price - ${requestScope.totalPrice}
        </c:if>
    </div>
    <div class="content">
        <form action="Luggage-delivery" method="post">
            <input type="hidden" name="cmd" value="order-process">
            <div class="user-details">
                <div class="input-box">
                    <span class="details">Size</span>
                    <input type="number" name="luggage-size" min="5" step="0.01" placeholder="Enter luggage volume"
                           value="${requestScope.sizeParam}"
                           required>
                </div>
                <div class="input-box">
                    <span class="details">Type</span>
                    <input type="text" name="luggage-type" placeholder="Enter category" pattern="[A-Z]+"
                           title="SPORT" value="${requestScope.typeParam}" required>
                </div>
                <div class="input-box">
                    <span class="details">Weight</span>
                    <input type="number" name="luggage-weight" min="0.2" step="0.01" placeholder="Enter weight"
                           value="${requestScope.weightParam}" required>
                </div>
                <div class="input-box">
                    <span class="details">Choose the route</span>
                    <select name="routeId">
                        <optgroup label="START POINT - FINAL POINT">
                            <c:forEach items="${requestScope.allRoutes}" var="route">
                                <c:choose>
                                    <c:when test="${requestScope.routeParam eq route.id}">
                                        <option value="${route.id}" selected>${route.startPoint} - ${route.destinationPoint}</option>
                                    </c:when>
                                    <c:when test="${requestScope.routeParam ne route.id}">
                                        <option value="${route.id}">${route.startPoint} - ${route.destinationPoint}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </optgroup>
                    </select>
                </div>
                <div class="input-box">
                    <span class="details">Delivery date</span>
                    <input type="date" name="luggage-del-date" placeholder="Enter the date"
                           value="${requestScope.delDateParam}" id="minDate">
<%--                    value="${requestScope.delDateParam}"--%>
                    <script>
                        var today = new Date().toISOString().split('T')[0];
                        document.getElementsByName("luggage-del-date")[0].setAttribute('min', today);
                    </script>
                </div>
                <div class="input-box">
                    <span class="details">Address</span>
                    <input type="text" name="delivery-address" placeholder="Enter address" value="${requestScope.addressParam}"
                           required>
                </div>
            </div>

            <div class="gender-details">
                <c:choose>
                    <c:when test="${requestScope.optionParam eq 'Fragile'}">
                        <input type="radio" name="option" value="None" id="dot-1">
                        <input type="radio" name="option" value="Fragile" id="dot-2" checked>
                        <span class="gender-title">Special option</span>
                        <div class="category">
                            <label for="dot-1">
                                <span class="dot one"></span>
                                <span class="option">None</span>
                            </label>
                            <label for="dot-2">
                                <span class="dot two"></span>
                                <span class="option">Fragile</span>
                            </label>
                        </div>
                    </c:when>
                    <c:when test="${requestScope.optionPram ne 'Fragile'}">
                        <input type="radio" name="option" value="None" id="dot-1" checked>
                        <input type="radio" name="option" value="Fragile" id="dot-2">
                        <span class="gender-title">Special option</span>
                        <div class="category">
                            <label for="dot-1">
                                <span class="dot one"></span>
                                <span class="option">None</span>
                            </label>
                            <label for="dot-2">
                                <span class="dot two"></span>
                                <span class="option">Fragile</span>
                            </label>
                        </div>
                    </c:when>
                </c:choose>
            </div>
            <div class="button">
                <input type="submit" name="orderButton" value="Make an order" style="margin-bottom: 5px">
                <form method="post" action="Luggage-delivery">
                    <input type="submit" name="priceCalculateButton" value="Calculate price">
                </form>
            </div>
        </form>
    </div>
</div>
</body>
</html>