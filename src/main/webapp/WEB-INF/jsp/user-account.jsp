<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>it
    <title>User room</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">

    <script type="text/javascript">
        <%@include file="/WEB-INF/js/balance-form.js"%>
<%--        <%@include file="/WEB-INF/js/buy-ticket-balance.js"%>--%>
    </script>

    <style>
        <%@include file="../css/order-cards.css"%>
        <%@include file="../css/balance-form.css"%>
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
                    <a class="nav-link active" href="#">View my orders</a>
                </li>
            </ul>

            <button  class="btn btn-outline-success my-2 my-sm-0" style="position: relative; left: 830px"
                     onclick="openForm()" type="submit">Add balance</button>

            <form class="form-inline mt-2 mt-md-0" action="Luggage-delivery" method="post"
                  style="position: relative; left: 860px">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
                <input type="hidden" name="cmd" value="logout">
            </form>
        </div>
    </nav>
</header>

<body>
<br>
<br>
<h3 style="text-align: center">Your balance is - ${requestScope.userBalance}</h3>

<div class="container">

<c:forEach items="${requestScope.userDeliveries}" var="delivery">
    <div class="card">
        <div class="box">
            <div class="content">
                <h2>0${delivery.id}</h2>
                <p>Size - ${delivery.size}. Weight - ${delivery.weight}</p>
                <p>Description - ${delivery.luggageType}</p>
                <p>Order was made ${delivery.startDate} and will be on ${delivery.deliveryDate}</p>
                <p>Delivery address - ${delivery.deliveryAddress}</p>
                <p>Route of delivery: ${delivery.route.startPoint} -- ${delivery.route.destinationPoint}</p>
                <p id="totalPrice${delivery.id}">TOTAL PRICE - ${delivery.totalPrice}</p>

                <c:choose>
                    <c:when test="${delivery.deliveryStatus.statusName eq 'PAY'}">
                        <form action="Luggage-delivery" method="post">
<%--                            onsubmit="return checkIfBalanceEnough(${delivery.id})"--%>
                            <input type="hidden" name="cmd" value="payOrder">
                            <input type="hidden" id="userBalance" value="${requestScope.userBalance}">
                            <input type="hidden" name="orderId" value="${delivery.id}">
                            <input type="submit" style="background-color: green"
                                    name="orderId" value="${delivery.deliveryStatus.statusName}">
                        </form>
<%--                        <script>--%>
<%--                            function checkIfBalanceEnough(deliveryId) {--%>
<%--                                const orderId = document.getElementsByName("orderId")[0].value;--%>
<%--                                const priceId = "totalPrice" + deliveryId.toString();--%>
<%--                                const price = document.getElementById(priceId).value;--%>
<%--                                    // .toString().substring(13, document.getElementById(priceId).toString().length);--%>
<%--                                // const totalPriceId = document.getElementById("totalPrice"+orderId.toString()).value;--%>
<%--                                // document.getElementById("userBalance").value--%>
<%--                                alert(price);--%>
<%--                                return false;--%>
<%--                                // var priceId = document.getElementsByName("orderId")[0];--%>
<%--                                //--%>
<%--                                // if (document.getElementById('userBalance').value < document.getElementById("totalPrice" + priceId).value) {--%>
<%--                                //     alert("Not enough balance.\nPlease, replenish it.")--%>
<%--                                //     return false;--%>
<%--                                // } else {--%>
<%--                                //     return true;--%>
<%--                                // }--%>
<%--                            }--%>
<%--                        </script>--%>
                    </c:when>
                    <c:when test="${delivery.deliveryStatus.statusName eq 'DECLINED'}">
                        <button style="background-color: red"
                                name="order" value="${delivery.id}">${delivery.deliveryStatus.statusName}</button>
                    </c:when>
                    <c:when test="${delivery.deliveryStatus.statusName eq 'IN PROGRESS'}">
                        <button style="background-color: chocolate"
                                name="order" value="${delivery.id}">${delivery.deliveryStatus.statusName}</button>
                    </c:when>
                    <c:otherwise>
                            <button style="background-color: blue"
                                    name="order" value="${delivery.id}">${delivery.deliveryStatus.statusName}</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    </c:forEach>
</div>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">


        <c:choose>
            <c:when test="${requestScope.currentPage eq 1}">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                    </li>
            </c:when>
            <c:when test="${requestScope.currentPage ne 1}">
                <li class="page-item">
                    <a class="page-link" href="Luggage-delivery?page=${requestScope.currentPage - 1}&cmd=user-room" tabindex="-1">Previous</a>
                </li>
            </c:when>
        </c:choose>

        <%--            <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
        <%--            <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
        <%--            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>

            <c:choose>
                <c:when test="${requestScope.currentPage eq requestScope.totalPages}">
                    <li class="page-item disabled">
                        <a class="page-link" href="#">
                            Next</a>
                    </li>
                </c:when>
                <c:when test="${requestScope.currentPage ne requestScope.totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="Luggage-delivery?page=${requestScope.currentPage + 1}&cmd=user-room">
                            Next</a>
                    </li>
                </c:when>
            </c:choose>

    </ul>
</nav>

<div class="form-popup" id="myForm">
    <form action="Luggage-delivery" class="form-container" method="post">
        <input type="hidden" name="cmd" value="balanceReplenishment">
        <h2>Balance replenishment</h2>

        <label for="balanceSum"><b>Enter balance sum:</b></label>
        <input type="number" min="100" step="0.01" id="balanceSum" placeholder="Enter sum" name="balance" required>

        <button type="submit" class="btn">Confirm</button>
        <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
    </form>
</div>

</body>
</html>