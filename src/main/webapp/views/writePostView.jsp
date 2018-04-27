<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Write Post</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="../css/writePost.css">
</head>
<body>

<jsp:include page="_menuLoggedIn.jsp"></jsp:include>

<div style="width: 30%" class="container bg-primary text-white p-3 rounded">
    <form method="POST" autocomplete="off" action="${pageContext.request.contextPath}/writePost">
        <div class="form-group">
            <label for="exampleInputUsername">Quote</label>
            <input type="text" name="quote" class="form-control form-control-sm" id="exampleInputUsername" aria-describedby="usernameHelp" placeholder="Enter quote" required>
        </div>
        <div class="form-group">
            <label for="postText">Additional text</label>
            <textarea class="form-control" id="postText" rows="5" name="text"></textarea>
        </div>
        <div class="form-group ui-widget">
            <div class="container p-0 autocomplete">
                <label for="book">Book</label>
                <input pattern=".{3,}" type="text" id="book" name="searchText" class="form-control form-control-sm" placeholder="Search for books" required>
            </div>
        </div>
        <%--<div class="form-group" id="bookSearchBar">
            <select class="form-control form-control-sm">
                <option value="default">Select a book</option>
            </select>
        </div>--%>
        <input type="hidden" id="bookId" name="bookId">
        <input type="hidden" id="bookTitle" name="bookTitle">
        <input type="hidden" id="bookAuthor" name="bookAuthor">
        <button type="submit" class="btn btn-danger">Post</button>
    </form>
</div>
<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/writePost.js"></script>

</body>
</html>
