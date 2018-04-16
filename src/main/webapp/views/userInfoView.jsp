<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

    <jsp:include page="_menuLoggedIn.jsp"></jsp:include>

    <h3>Hello: <%=request.getRemoteUser()%></h3>

    User Name: <b><%=request.getRemoteUser()%></b>
    <br />
    <%--Gender: ${loginedUser.gender } <br />--%>
    <c:forEach items="${posts}" var="post">
        <div class="card" style="width: 40rem;">
            <h5 class="card-header">"${post.quote}"</h5>
            <div class="card-body">
                <h6 class="card-subtitle mb-2 text-muted">from <a href="#" class="card-link">${post.book.author}</a> by<a href="#" class="card-link">${post.book.title}</a></h6>
                <p class="card-text">${post.description}</p>
                <footer class="blockquote-footer">posted by ${post.user.username} on ${post.datePosted}</footer>
            </div>
        </div>
        <br>
    </c:forEach>

    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>