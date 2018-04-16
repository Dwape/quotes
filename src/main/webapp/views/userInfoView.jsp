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
                <h6 class="card-subtitle mb-2 text-muted">from <a href="#" class="card-link">${post.book.title}</a> by<a href="#" class="card-link ml-1">${post.book.author}</a></h6>
                <p class="card-text">${post.description}</p>
                <div style="display: inline-flex">
                    <footer class="blockquote-footer">posted by ${post.user.username} on ${post.datePosted}</footer>
                    <a href="${pageContext.request.contextPath}/editPost?id=${post.id}" class="card-link ml-1 float-right">Edit</a>
                    <a href="${pageContext.request.contextPath}/deletePost?id=${post.id}" class="card-link ml-1 float-right" style="color:red">Delete</a>
                </div>
            </div>
        </div>
        <br>
    </c:forEach>

    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>