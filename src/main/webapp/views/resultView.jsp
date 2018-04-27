<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Results</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
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

    <div class="wrapper" style="display: flex; align-items: stretch">

        <nav id="sidebar" style="min-width: 250px; max-width: 250px">
            <!-- Sidebar Header -->
            <div class="sidebar-header">
                <h4>Filters</h4>
            </div>

    <!-- Sidebar Links -->
    <ul class="authorList" id="authorList" style="list-style-type: none">
        <li id="authorHead" style="font-weight:bold"><h5>Authors</h5></li>
    </ul>
    </nav>

    </div>

    <c:forEach items="${posts}" var="post">
        <div class="card" id="post" style="width: 40rem;">
            <h5 class="card-header">"${post.quote}"</h5>
            <div class="card-body">
                <h6 class="card-subtitle mb-2 text-muted">from <a href="#" class="card-link">${post.book.title}</a> by<a href="#" class="card-link ml-1">${post.book.author}</a></h6>
                <p class="card-text">${post.description}</p>
                <div style="display: inline-flex">
                    <footer class="blockquote-footer">posted by ${post.user.username} on ${post.datePosted}</footer>
                </div>
            </div>
        </div>
        <input type="hidden" id="bookTitle" class="title" name="bookTitle" value="${post.book.title}">
        <input type="hidden" id="bookAuthor" class="author" name="bookAuthor" value="${post.book.author}">
        <br>
    </c:forEach>

<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="../js/searchResult.js"></script>
</body>
</html>
