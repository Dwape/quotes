<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/homeView.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
</head>
<body>
    <c:choose>
        <c:when test="<%=request.getRemoteUser() != null%>">
            <jsp:include page="_menuLoggedIn.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="_menu.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>
    <h3 class="home-page">Home Page</h3>
    <%--<div style="width: 30%" class="container bg-primary text-white p-3 mb-5 rounded">
        <form method="POST" action="${pageContext.request.contextPath}/home">
            <input type="text" name="searchTerm" placeholder="Search for posts" required/>
            <button type="submit" id="sumbit-button" class="btn btn-danger">Search</button>
        </form>
    </div>--%>

    <div class="row justify-content-center" style="margin-left: 0;margin-right: 0">
        <div class="container col-6">
            <form method="POST" action="${pageContext.request.contextPath}/home" class="card card-sm">
                <div class="card-body row no-gutters align-items-center" style="padding: 12px 24px;">
                    <div class="col-auto">
                        <%--<i class="icon-magnifying-glass h4 text-body"></i>--%>
                        <i class="fas fa-search fa-lg"></i>
                    </div>
                    <!--end of col-->
                    <div id="input-borderless" class="col">
                        <input class="form-control form-control-lg form-control-borderless" name="searchTerm" type="text" placeholder="Search for posts" required>
                    </div>
                    <!--end of col-->
                    <div class="col-auto">
                        <button class="btn btn-lg btn-primary" id="sumbit-button" type="submit">Search</button>
                    </div>
                    <!--end of col-->
                </div>
            </form>
        </div>
        <!--end of col-->
    </div>
    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
