<nav class="navbar navbar-expand navbar-dark bg-dark mb-5" style="background-color: #212020;">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
        <%--<img src="${pageContext.request.contextPath}/assets/images/logoTemp.jpg" width="30" height="30" class="d-inline-block align-top" alt="">--%>
        <i class="fas fa-book mr-2"></i>
        Quotes
    </a>

    <div class="container-fluid d-flex justify-content-end">
        <i class="fas fa-user" style="color: white"></i>

        <div class="dropdown mr-2">
            <a class="nav-link dropdown-toggle" style="color: white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <%=request.getRemoteUser()%>
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/secure/manageAccount">Manage Account</a>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/secure/userInfo">User Info</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
            </div>
        </div>

        <a class="btn btn-outline-light my-2 my-sm-0" href="${pageContext.request.contextPath}/writePost" role="button">Post</a>
    </div>
</nav>
