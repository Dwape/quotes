<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Results</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/resultView.css">
    <link rel="stylesheet" href="../css/searchResult.css">
</head>
<body onload="findPosts();">
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
                <nav class="sticky-top" id="sidebar">
                    <!-- Sidebar Header -->
                    <div class="sidebar-header">
                        <h5 style="font-weight:bold">Filter results</h5>
                        <a onclick="clearFilters();" style="cursor: pointer">Clear filters</a>
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

            <div id="postColumn" class="col-9" >
                <h3 class="mb-4">Search results for: "${q}"</h3>
                <input type="hidden" id="searchTerm" value="${q}">
                <h4 id="noResult" style="display: none">No posts found</h4>
                <div class="" id="genericPost" style="width: 40rem; display: none">
                    <h5 class="card-header" id="quote"></h5>
                    <a href="${pageContext.request.contextPath}/postDetails?id=" id="redirect" class="divLink">
                    </a>
                    <div class="card-body">
                        <h6 class="card-subtitle mb-2 text-muted" id="info"></h6>
                        <p class="card-text" id="description"></p>
                        <div style="display: inline-flex">
                            <footer class="blockquote-footer" id="footer"></footer>
                        </div>
                    </div>
                    <input type="shown" style="display: none" id="bookTitle" class="" name="bookTitle" value="">
                    <input type="shown" style="display: none" id="bookAuthor" class="" name="bookAuthor" value="">
                </div>
            </div>
        </div>
    </div>

<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="../js/jquery/jquery-3.3.1.min.js"></script>
<script src="../js/searchResult.js"></script>
<script src="../js/resultPosts.js"></script>
</body>
</html>
