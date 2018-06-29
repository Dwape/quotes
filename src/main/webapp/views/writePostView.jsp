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
                    <a class="badge badge-secondary" data-toggle="modal" data-target="#citeModal">Add extra info</a>
                </div>
                <input type="hidden" id="bookId" name="bookId">
                <input type="hidden" id="bookTitle" name="bookTitle">
                <input type="hidden" id="bookAuthor" name="bookAuthor">
                <div class="button-container d-flex justify-content-end">
                    <%--<button type="button" id="info-button" class="btn btn-lg btn-secondary" data-toggle="modal" data-target="#citeModal">
                        Add extra info
                    </button>--%>
                    <button type="submit" class="btn btn-lg btn-primary" id="sumbit-button" type="submit">Post</button>
                </div>

                <%--Modal--%>
                <div class="modal fade" id="citeModal" tabindex="-1" role="dialog" aria-labelledby="citeModalTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="citeModalTitle">Extra information</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group mb-0">
                                    <label for="postPage">Page number</label>
                                    <input type="text" name="postPage" class="form-control form-control-sm mb-2" id="postPage" placeholder="Enter the page number where others may find your quote">

                                    <label for="bookPublisher">Publisher</label>
                                    <input type="text" name="bookPublisher" class="form-control form-control-sm mb-2" id="bookPublisher" placeholder="Enter the publisher of the book">

                                    <label for="bookPlacePublished">Place Published</label>
                                    <input type="text" name="bookPlacePublished" class="form-control form-control-sm mb-2" id="bookPlacePublished" placeholder="Enter the place of publication of the book">

                                    <label for="bookPublishedDate">Date Published</label>
                                    <input type="text" name="bookPublishedDate" class="form-control form-control-sm mb-2" id="bookPublishedDate" placeholder="Enter year (YYYY) of publication of the book">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id="save-btn" data-dismiss="modal">Save</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- / Modal--%>

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

<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="../js/writePost.js"></script>

</body>
</html>
