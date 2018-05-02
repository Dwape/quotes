<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Results</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/resultView.css">
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

    <div class="container">
        <div class="row">
            <div class="col-3 wrapper" id="wrapper">
                <nav id="sidebar">
                    <!-- Sidebar Header -->
                    <div class="sidebar-header">
                        <h5 style="font-weight:bold">Filter results</h5>
                        <a onclick="clearFilters();" style="cursor: pointer">clear filters</a>
                    </div>
            <!-- Sidebar Links -->
                <ul class="authorList" id="authorList" style="list-style-type: none">
                    <li id="authorHead" class="clickable" style="font-weight:bold"><h5>Authors</h5></li>
                </ul>
                <ul class="titleList" id="titleList" style="list-style-type: none">
                    <li id="titleHead" class="clickable" style="font-weight:bold"><h5>Books</h5></li>
                </ul>
                </nav>

            </div>

            <div class="col-9" >
                <h3 class="mb-4">Search results for: "${q}"</h3>
                <h4 id="noResult" style="display: none">No posts found</h4>
                <c:forEach items="${posts}" var="post">
                    <div class="card mb-4" id="post" style="width: 40rem;">
                        <h5 class="card-header">"${post.quote}"</h5>
                        <div class="card-body">
                            <h6 class="card-subtitle mb-2 text-muted">from <a href="#" class="card-link">${post.book.title}</a> by<a href="#" class="card-link ml-1">${post.book.author}</a></h6>
                            <p class="card-text">${post.description}</p>
                            <div style="display: inline-flex">
                                <footer class="blockquote-footer">posted by ${post.user.username} on ${post.datePosted}</footer>
                            </div>
                        </div>
                    </div>
                    <input type="shown" style="display: none" id="bookTitle" class="title" name="bookTitle" value="${post.book.title}">
                    <input type="shown" style="display: none" id="bookAuthor" class="author" name="bookAuthor" value="${post.book.author}">
                </c:forEach>
            </div>
        </div>
    </div>

<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="../js/searchResult.js"></script>
</body>
</html>
