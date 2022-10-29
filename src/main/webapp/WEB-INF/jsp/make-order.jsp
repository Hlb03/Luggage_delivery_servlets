<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Luggage delivery</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/carousel/">

    <script type="text/javascript">
        <%@include file="/WEB-INF/js/restict-date.js"%>
        <%@include file="/WEB-INF/js/radio-check.js"%>
    </script>

    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
            background: linear-gradient(135deg, #71b7e6, #9b59b6);
        }

        .container {
            max-width: 700px;
            width: 100%;
            background-color: #fff;
            padding: 25px 30px;
            border-radius: 5px;
            box-shadow: 0 5px 10px rgba(0, 0, 0, 0.15);
        }

        .container .title {
            font-size: 25px;
            font-weight: 500;
            position: relative;
        }

        .container .title::before {
            content: "";
            position: absolute;
            left: 0;
            bottom: 0;
            height: 3px;
            width: 30px;
            border-radius: 5px;
            background: linear-gradient(135deg, #71b7e6, #9b59b6);
        }

        .content form .user-details {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            margin: 20px 0 12px 0;
        }

        form .user-details .input-box {
            margin-bottom: 15px;
            width: calc(100% / 2 - 20px);
        }

        form .input-box span.details {
            display: block;
            font-weight: 500;
            margin-bottom: 5px;
        }

        .user-details .input-box input {
            height: 45px;
            width: 100%;
            outline: none;
            font-size: 16px;
            border-radius: 5px;
            padding-left: 15px;
            border: 1px solid #ccc;
            border-bottom-width: 2px;
            transition: all 0.3s ease;
        }

        .user-details .input-box input:focus,
        .user-details .input-box input:valid {
            border-color: #9b59b6;
        }

        form .gender-details .gender-title {
            font-size: 20px;
            font-weight: 500;
        }

        form .category {
            display: flex;
            width: 80%;
            margin: 14px 0;
            justify-content: space-between;
        }

        form .category label {
            display: flex;
            align-items: center;
            cursor: pointer;
        }

        form .category label .dot {
            height: 18px;
            width: 18px;
            border-radius: 50%;
            margin-right: 10px;
            background: #d9d9d9;
            border: 5px solid transparent;
            transition: all 0.3s ease;
        }

        #dot-1:checked ~ .category label .one,
        #dot-2:checked ~ .category label .two,
        #dot-3:checked ~ .category label .three {
            background: #9b59b6;
            border-color: #d9d9d9;
        }

        form input[type="radio"] {
            display: none;
        }

        form .button {
            height: 45px;
            margin: 35px 0
        }

        form .button input {
            height: 100%;
            width: 100%;
            border-radius: 5px;
            border: none;
            color: #fff;
            font-size: 18px;
            font-weight: 500;
            letter-spacing: 1px;
            cursor: pointer;
            transition: all 0.3s ease;
            background: linear-gradient(135deg, #71b7e6, #9b59b6);
        }

        form .button input:hover {
            /* transform: scale(0.99); */
            background: linear-gradient(-135deg, #71b7e6, #9b59b6);
        }

        @media (max-width: 584px) {
            .container {
                max-width: 100%;
            }

            form .user-details .input-box {
                margin-bottom: 15px;
                width: 100%;
            }

            form .category {
                width: 100%;
            }

            .content form .user-details {
                max-height: 300px;
                overflow-y: scroll;
            }

            .user-details::-webkit-scrollbar {
                width: 5px;
            }
        }

        @media (max-width: 459px) {
            .container .content .category {
                flex-direction: column;
            }
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/Luggage-delivery">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/Luggage-delivery?cmd=make-order">Make order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#">View my orders</a>
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0" action="Luggage-delivery"
                  style="position: relative; left: 1050px">
                <c:choose>
                    <c:when test="${sessionScope.user eq null}">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign in</button>
                        <input type="hidden" name="cmd" value="authorize"><br>
                    </c:when>
                    <c:when test="${sessionScope.user ne null}">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
                    </c:when>
                </c:choose>
            </form>
        </div>
    </nav>
</header>
<!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  -->
<body>
<div class="container">
    <div class="title">ORDER</div>
    <div class="content">
        <form action="Luggage-delivery" method="post">
            <input type="hidden" name="cmd" value="order-process">
            <div class="user-details">
                <div class="input-box">
                    <span class="details">Size</span>
                    <input type="number" name="luggage-size" min="10" step="0.01" placeholder="Enter luggage volume" required>
                </div>
                <div class="input-box">
                    <span class="details">Type</span>
                    <input type="text" name="luggage-type" placeholder="Enter category" required>
                </div>
                <div class="input-box">
                    <span class="details">Weight</span>
                    <input type="number" name="luggage-weight" min="1.00" step="0.01" placeholder="Enter weight" required>
                </div>
                <div class="input-box">
                    <span class="details">Choose the route</span>
                    <select name="routeId">
                        <optgroup label="START POINT - FINAL POINT">
                            <c:forEach items="${requestScope.allRoutes}" var="route">
                                <option value="${route.id}">${route.startPoint} - ${route.destinationPoint}</option>
                            </c:forEach>
                        </optgroup>
                    </select>
<%--                    <input type="text" placeholder="Confirm your password" required>--%>
                </div>
                <div class="input-box">
                    <span class="details">Delivery date</span>
                    <input type="date" name="luggage-del-date" placeholder="Enter the date" id="minDate" min="2022-10-25" >
                </div>
                <div class="input-box">
                    <span class="details">Address</span>
                    <input type="text" name="delivery-address" placeholder="Enter address" required>
                </div>
            </div>
            <div class="gender-details">
                <input type="radio" name="empty" checked id="dot-1">
                <input type="radio" name="fragile" id="dot-2">
                <span class="gender-title">Special options</span>
                <div class="category">
                    <label for="dot-1">
                        <span class="dot one"></span>
                        <span class="gender">None</span>
                    </label>
                    <label for="dot-2">
                        <span class="dot two"></span>
                        <span class="gender">Fragile</span>
                    </label>
                </div>
            </div>
            <input type="hidden" name="radioResult">
            <div class="button">
                <input type="submit" value="Make an order">
            </div>
        </form>
    </div>
</div>
</body>
</html>