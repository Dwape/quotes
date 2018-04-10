<nav class="navbar navbar-expand navbar-light bg-light mb-5">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
        <%--<img src="${pageContext.request.contextPath}/assets/images/logoTemp.jpg" width="30" height="30" class="d-inline-block align-top" alt="">--%>
        <i class="fas fa-book mr-2"></i>
        Quotes
    </a>

    <div class="container-fluid d-flex justify-content-end">
        <i class="fas fa-user"></i>
        <div class="dropdown mr-5">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <%--${loginedUser.name}--%>Giansco
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/manageAccount">Manage Acct</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/userInfo">User Info</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
            </div>
        </div>
    </div>
</nav>
