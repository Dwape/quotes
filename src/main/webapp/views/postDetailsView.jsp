<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body onload="getCommentArray();">

<c:choose>
    <c:when test="<%=request.getRemoteUser() != null%>">
        <jsp:include page="_menuLoggedIn.jsp"></jsp:include>
    </c:when>
    <c:otherwise>
        <jsp:include page="_menu.jsp"></jsp:include>
    </c:otherwise>
</c:choose>

<div class="card mb-4" style="width: 40rem;">
    <h5 class="card-header">"${quote}"</h5>
    <div class="card-body pb-2">
        <h6 class="card-subtitle mb-2 text-muted">from <a href="#" class="card-link">${bookTitle}</a> by<a href="#" class="card-link ml-1">${bookAuthor}</a></h6>
        <p class="card-text">${text}</p>

        <footer class="blockquote-footer">posted by ${postedBy} on ${datePosted.toLocaleString()}</footer>
    </div>
</div>
<c:choose>
    <c:when test="<%=request.getRemoteUser() != null%>">
        <form method="POST" autocomplete="off" action="${pageContext.request.contextPath}/postDetails">
            <div class="form-group mt-4">
                <label for="postText">Write a comment</label>
                <textarea class="form-control" id="postText" rows="3" name="text"></textarea>
            </div>
            <input type="hidden" name="idPost" value="${id}">
            <button type="submit" class="btn btn-danger">Submit</button>
        </form>
    </c:when>
</c:choose>
<span style="display: none" id="commentsJson">${commentsJson}</span>
<div id="comments"></div>
<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="../js/postDetails.js"></script>
</body>
</html>
