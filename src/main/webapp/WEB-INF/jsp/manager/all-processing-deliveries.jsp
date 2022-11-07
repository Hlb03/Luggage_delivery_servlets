<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Approve/Decline delivery</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">


    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/carousel/">

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
                       href="${pageContext.request.contextPath}/Luggage-delivery?cmd=manager-room">View customer
                        orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/Luggage-delivery?cmd=report-view">Reports</a>
                </li>
            </ul>
            <c:choose>
            <c:when test="${sessionScope.user eq null}">
            <form class="form-inline mt-2 mt-md-0" action="Luggage-delivery"
                  style="position: relative; left: 1050px">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign in</button>
                <input type="hidden" name="cmd" value="authorize"><br>
                </c:when>
                <c:when test="${sessionScope.user ne null}">
                <form class="form-inline mt-2 mt-md-0" action="Luggage-delivery" method="post"
                      style="position: relative; left: 1030px">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
                    <input type="hidden" name="cmd" value="logout">
                    </c:when>
                    </c:choose>
                </form>
        </div>
    </nav>
</header>
<br>
<br>
<br>
<h2 style="text-align: center">All orders with status 'PROCESSING' </h2>
<body>

<table class="table table-striped table-dark">
    <thead>
    <tr>
        <th scope="col">Order id</th>
        <th scope="col">User login</th>
        <th scope="col">Size</th>
        <th scope="col">Weight</th>
        <th scope="col">Type</th>
        <th scope="col">Order date</th>
        <th scope="col">Delivery date</th>
        <th scope="col">Address</th>
        <th scope="col">Route</th>
        <th scope="col">Price</th>
        <th scope="col">Approve</th>
        <th scope="col">Reject</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.processDeliveries}" var="delivery">
        <tr>
            <th>${delivery.id}</th>
            <th>${delivery.user.login}</th>
            <th>${delivery.size}</th>
            <th>${delivery.weight}</th>
            <th>${delivery.luggageType}</th>
            <th>${delivery.startDate}</th>
            <th>${delivery.deliveryDate}</th>
            <th>${delivery.deliveryAddress}</th>
            <th>${delivery.route.startPoint} -- ${delivery.route.destinationPoint}</th>
            <th>${delivery.totalPrice}</th>
            <th>
                <form action="Luggage-delivery" method="post">
                    <input type="hidden" name="cmd" value="changeStatus">
                    <input type="hidden" name="processOrder" value="approve">
                    <input type="hidden" name="orderId" value="${delivery.id}">
                    <input style="background-color: green" type="submit" value="&#9745;">
                </form>
            </th>
            <th>
                <form action="Luggage-delivery" method="post">
                    <input type="hidden" name="cmd" value="changeStatus">
                    <input type="hidden" name="processOrder" value="reject">
                    <input type="hidden" name="orderId" value="${delivery.id}">
                    <input style="background-color: red" type="submit" value="&#9746;">
                </form>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Previous</a>
        </li>

        <%--            <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
        <%--            <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
        <%--            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>

        <li class="page-item disabled">
            <a class="page-link" href="#">Next</a>
        </li>
    </ul>
</nav>

</body>
</html>