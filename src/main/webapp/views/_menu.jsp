<nav class="navbar navbar-expand navbar-dark bg-dark mb-5" style="background-color: #212020;">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
        <%--<img src="${pageContext.request.contextPath}/assets/images/logoTemp.jpg" width="30" height="30" class="d-inline-block align-top" alt="">--%>
        <i class="fas fa-book mr-2"></i>
        Quotes
    </a>
    <%--<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>--%>

    <div class="container-fluid d-flex justify-content-end">
        <%--<ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/index">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul>--%>
        <a class="btn btn-outline-light my-2 my-sm-0" href="${pageContext.request.contextPath}/login" role="button">Login</a>
        <span class="mx-2" style="color: white">or</span>
        <a class="btn btn-outline-light my-2 my-sm-0" href="${pageContext.request.contextPath}/register" role="button">Sign up</a>
    </div>
</nav>
