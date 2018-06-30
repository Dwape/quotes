<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/resultView.css">
    <link rel="stylesheet" href="../css/searchResult.css">
    <link rel="stylesheet" href="../css/userInfo.css">
</head>
<body onload="findUserPosts();">

    <input type="hidden" id="username" value="${username}">

    <c:choose>
        <c:when test="<%=request.getRemoteUser() != null%>">
            <jsp:include page="_menuLoggedIn.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="_menu.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>

    <%--Gender: ${loginedUser.gender } <br />--%>

    <div class="container" id="postColumn">
        <h3 class="mb-4">User: "${username}"</h3>
        <h4 id="noResult" style="display: none">This user hasn't posted anything yet</h4>
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

<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="../js/jquery/jquery-3.3.1.min.js"></script>
<script src="../js/userInfo.js"></script>
<script src="../js/resultPosts.js"></script>
</body>
</html>