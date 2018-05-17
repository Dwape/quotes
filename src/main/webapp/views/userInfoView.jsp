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
    <div class="container ml-5 mb-4">
        <h3>Hello <%=request.getRemoteUser()%>!</h3>

        <b>Manage your posts:</b>
    </div>
    <%--Gender: ${loginedUser.gender } <br />--%>
    <div class="container">
        <c:forEach items="${posts}" var="post">
            <div class="card mb-4" style="width: 40rem;">
                <h5 class="card-header">"${post.quote}"</h5>
                <div class="card-body pb-2">
                    <h6 class="card-subtitle mb-2 text-muted">from <a href="#" class="card-link">${post.book.title}</a> by<a href="#" class="card-link ml-1">${post.book.author}</a></h6>
                    <p class="card-text">${post.description}</p>
                    <div class="container">
                        <div class="row justify-content-between">
                            <div class="col-6 p-0" style="height: 20px"><footer class="blockquote-footer">posted by ${post.user.username} on ${post.datePosted.toLocaleString()}</footer></div>
                            <div class="col-1 p-0"><a href="${pageContext.request.contextPath}/editPost?id=${post.id}" class="card-link">Edit</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>