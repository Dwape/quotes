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
        <button type="submit" class="btn btn-danger">Post</button>
    </form>
    <div class="form-group">
        <label for="form-id">Book</label>
        <form id="form-id" method="GET">
            <input type="text" name="searchText" class="form-control form-control-sm" placeholder="Search for books, authors, categories and more.." required>
            <input type="button" class="btn btn-danger" value="Search" onclick="search(this.form);"/>
        </form>
    </div>
</div>
<script>
    function search(form){
        var searchTerm = form.searchText.value;
        httpGet('https://www.googleapis.com/books/v1/volumes?q=' + searchTerm + '&projection=lite&orderBy=relevance&langRestrict=en', form);
    }

    function httpGet(url, form){
        fetch(url)
            .then(
                function(response) {
                    if (response.status !== 200) {
                        console.log('Looks like there was a problem. Status Code: ' +
                            response.status);
                        return;
                    }
                    // Examine the text in the response
                    response.json().then(function(data) {
                        console.log(data);
                        var author = JSON.stringify(data.items[0].volumeInfo.authors[0], null, 2);
                        var title = JSON.stringify(data.items[0].volumeInfo.title, null, 2);
                        var id = JSON.stringify(data.items[0].id, null, 2);
                        form.searchText.value = title + ' by ' + author;
                        //alert(str1 + str2 + id);
                    });
                }
            )
            .catch(function(err) {
                console.log('Fetch Error :-S', err);
            });
    }
</script>

<jsp:include page="bootstrapBody.jsp"></jsp:include>
</body>
</html>
