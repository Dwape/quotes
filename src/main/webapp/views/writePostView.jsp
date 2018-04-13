<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
</head>
<body>

<jsp:include page="_menuLoggedIn.jsp"></jsp:include>

<div style="width: 30%" class="container bg-primary text-white p-3 rounded">
    <form method="POST" action="${pageContext.request.contextPath}/writePost">
        <div class="form-group">
            <%--<input type="hidden" name="redirectId" value="${param.redirectId}" />--%>
            <label for="exampleInputUsername">Quote</label>
            <input type="text" name="quote" class="form-control form-control-sm" id="exampleInputUsername" aria-describedby="usernameHelp" placeholder="Enter quote" required>
            <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
        </div>
        <div class="form-group">
            <label for="postText">Additional text</label>
            <textarea class="form-control" id="postText" rows="5" name="text"></textarea>
        </div>
        <%--<div class="form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Check me out</label>
        </div>--%>
        <div class="form-group">
            <label for="book">Book</label>
            <input type="text" id="book" name="searchText" class="form-control form-control-sm" placeholder="Search for books" required>
            <input type="button" class="btn btn-danger" value="Search" onclick="search(this.form);"/>
            <input type="hidden" name="bookId">
        </div>
        <button type="submit" class="btn btn-danger">Post</button>
    </form>
</div>
<script src="../js/bookQuery.js"></script>

<jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
