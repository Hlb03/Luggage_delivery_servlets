<!DOCTYPE html>
<html>
<head>
    <title>User room</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">

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
            <form class="form-inline mt-2 mt-md-0" action="Luggage-delivery" method="post"
                  style="position: relative; left: 1050px">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Sign out</button>
                <input type="hidden" name="cmd" value="logout">
            </form>
        </div>
    </nav>
</header>


<body>
<h1 style="text-align: center">Hello in user personal cabinet</h1>
</body>
</html>