<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/homeView.css" type="text/css">
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
    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
