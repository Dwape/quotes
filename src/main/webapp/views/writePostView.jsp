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

<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="card boxx mb-4" style="width: 40rem;">
            <form method="POST" autocomplete="off" action="${pageContext.request.contextPath}/writePost">
                <div class="card-header">
                    <div class="form-group mb-0">
                        <label for="exampleInputUsername">Quote</label>
                        <input type="text" name="quote" class="form-control form-control-sm" id="exampleInputUsername" aria-describedby="usernameHelp" placeholder="Enter quote" required>
                    </div>
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <label for="postText">Ask a question or make a statement!</label>
                        <textarea class="form-control" id="postText" rows="5" name="text"></textarea>
                    </div>
                    <div class="form-group ui-widget mb-0">
                        <div class="container p-0 autocomplete">
                            <label for="book">Book</label>
                            <input pattern=".{3,}" type="text" id="book" name="searchText" class="form-control form-control-sm" placeholder="Search for books" required>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="bookId" name="bookId">
                <input type="hidden" id="bookTitle" name="bookTitle">
                <input type="hidden" id="bookAuthor" name="bookAuthor">
                <%--<button type="submit" class="btn btn-outline-primary">Post</button>--%>
                <div class="button-container">
                    <button type="submit" class="btn btn-lg btn-primary" id="sumbit-button" type="submit">Post</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--<div class="container">
    <div class="row">
        <div class="container"><i id="watermark-icon" class="fas fa-book"></i></div>
        <h1>Quotes</h1>
    </div>
</div>--%>

<%--<div style="width: 30%" class="container bg-primary text-white p-3 rounded">
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
        &lt;%&ndash;<div class="form-group" id="bookSearchBar">
            <select class="form-control form-control-sm">
                <option value="default">Select a book</option>
            </select>
        </div>&ndash;%&gt;
        <input type="hidden" id="bookId" name="bookId">
        <input type="hidden" id="bookTitle" name="bookTitle">
        <input type="hidden" id="bookAuthor" name="bookAuthor">
        <button type="submit" class="btn btn-danger">Post</button>
    </form>
</div>--%>
<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/writePost.js"></script>

</body>
</html>
