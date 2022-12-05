<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create report</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">

    <style>
        .image-background {
            background-image: url("/images/report-image.jpg");
            height: 757px;
        }

        .cascading-right {
            margin-right: -50px;
        }

        @media (max-width: 991.98px) {
            .cascading-right {
                margin-right: 0;
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
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/Luggage-delivery?cmd=manager-room">View customer
                        orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/Luggage-delivery?cmd=create-report">Reports</a>
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

<body>
<section class="text-center text-lg-start image-background">

    <div class="row g-0 align-items-center">
        <div class="col-lg-6 mb-5 mb-lg-0">
            <div class="card cascading-right" style="
            background: hsla(0, 0%, 100%, 0.55);
            backdrop-filter: blur(30px);">
                <div class="card-body p-5 shadow-5 text-center">
                    <h2 class="fw-bold mb-5">Day report</h2>

                    <form action="Luggage-delivery" method="post">
                        <input type="hidden" name="cmd" value="make-report">
                        <div class="form-outline mb-4">
                            <label class="form-label" for="form3Example3">Choose the date:</label>
                            <br>
                            <input type="date" id="form3Example3" name="report-date" class="form-control" required/>
                            <script>
                                var today = new Date().toISOString().split('T')[0];
                                document.getElementsByName("report-date")[0].setAttribute('min', today);
                            </script>
                        </div>

                        <input type="submit" class="btn btn-primary btn-block mb-4"
                               name="dateReport" value="Create report">
                        <%--                            Create report--%>
                        <%--                        </input>--%>

                    </form>
                </div>
            </div>
        </div>
        <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
        <div class="row g-0 align-items-center">
            <div class="col-lg-6 mb-5 mb-lg-0">
                <div class="card cascading-right" style="
            background: hsla(0, 0%, 100%, 0.55);
            backdrop-filter: blur(30px);">
                    <div class="card-body p-5 shadow-5 text-center">
                        <h2 class="fw-bold mb-5">Route report</h2>
                        <form action="Luggage-delivery" method="post">
                            <input type="hidden" name="cmd" value="make-report">
                            <div class="form-outline mb-4">
                                <label class="form-label" for="form3Example4">Choose the route:</label>
                                <br>
                                <select id="form3Example4" name="routeId">
                                    <optgroup label="START POINT - FINAL POINT">
                                        <c:forEach items="${requestScope.routes}" var="route">
                                            <option value="${route.id}">${route.startPoint}
                                                - ${route.destinationPoint}</option>
                                        </c:forEach>
                                    </optgroup>
                                </select>
                            </div>
                            <input type="submit" class="btn btn-primary btn-block mb-4"
                                   name="routeReport" value="Create report">

                        </form>
                    </div>
                </div>
            </div>
        </div>

<%--        <a href="../../../pdf_reports/statistics.pdf">--%>
        <c:if test="${requestScope.downloadPdf ne null}">
            <script>
                var link = document.createElement('a');
                link.setAttribute('download', "statistics.pdf");
                link.href = "../../../pdf_reports/statistics.pdf"
                document.body.appendChild(link);
                link.click();
                link.remove();
            </script>
        </c:if>

    </div>
</section>
</body>
</html>
