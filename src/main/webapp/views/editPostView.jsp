<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

<jsp:include page="_menuLoggedIn.jsp"></jsp:include>

<div style="width: 30%" class="container bg-primary text-white p-3 rounded">
    <form method="POST" action="${pageContext.request.contextPath}/editPost">
        <div class="form-group">
            <label for="exampleInputUsername">Quote</label>
            <input type="text" name="quote" class="form-control form-control-sm" id="exampleInputUsername" aria-describedby="usernameHelp" value="${quote}" readonly>
        </div>
        <div class="form-group">
            <label for="postText">Additional text</label>
            <textarea class="form-control" id="postText" rows="5" name="text">${text}</textarea>
        </div>
        <div class="form-group">
            <label for="book">Book</label>
            <h6 id="book">${bookTitle} by ${bookAuthor}</h6>
        </div>
        <input type="hidden" id="postId" name="postId" value="${id}">
        <button type="submit" class="btn btn-danger">Post</button>
    </form>
</div>
<script src="../js/bookQuery.js"></script>

<jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
