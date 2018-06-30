<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/postDetails.css" type="text/css">
    <meta name="twitter:card" content="summary">
    <meta name="twitter:title" content="Quotes: book quotes discussion" />
    <meta name="twitter:description" content="Find millions of quotes explained." />
    <meta name="twitter:image" content="https://i.imgur.com/B3vHzpN.png" />
</head>
<body onload="loadView();">

    <c:choose>
        <c:when test="<%=request.getRemoteUser() != null%>">
            <jsp:include page="_menuLoggedIn.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
            <jsp:include page="_menu.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>

    <div class="container">
        <p style="display: none" id="user"><%=request.getRemoteUser()%></p>
        <div class="row">
            <div class="col-auto">
                <a id="imageLink" href="">
                    <img id="bookImage" style="border-radius: 3%;" height="178" width="128" src="../assets/images/Image-not-available.jpg">
                </a>
            </div>
            <div class="col-7" >
                <div class="card mb-4" id="post" style="width: 40rem;">
                    <div class="card-header row  mx-0 bg-dark" >
                        <div class="col-1 p-0">
                            <span id="score-post" style="font-size: large"></span>
                            <i class="fas fa-fire" style="transform: scale(1.2)"></i>
                        </div>
                        <h5 class="col-11" id="postQuote"></h5>
                    </div>
                    <div class="card-body pb-2">
                        <h6 class="card-subtitle mb-2 text-muted" id="postInfo"></h6>
                        <p class="card-text" id="postDescription"></p>
                        <div class="row d-flex justify-content-between mx-0">
                            <div>
                                <i id="upvote-post" style="color: black" class="fas fa-arrow-circle-up"></i>
                                <i id="downvote-post" style="color: black" class="fas fa-arrow-circle-down mr-2"></i>
                                <a tabindex="0" class="remove-blue" role="button" data-toggle="popover"
                                   data-trigger="focus" data-content="Copy to clipboard" id="copy">
                                    <i class="fas fa-clipboard" onclick="copyToClipboard()"></i>
                                    <i class="fas fa-clipboard disabled-icon" hidden></i>
                                </a>

                                <a class="twitter-share-button" href="https://twitter.com/intent/tweet?text=">Tweet</a>
                            </div>
                            <footer class="blockquote-footer" id="postFooter"></footer>

                            <input type="hidden" id="vote" value="${vote}">
                            <input type="hidden" name="idPost" value="${id}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:choose>
            <c:when test="<%=request.getRemoteUser() != null%>">
                <div class="form-group">
                    <textarea class="form-control" id="replyText" maxlength="5000" rows="4" style="width: 50rem;" name="text" placeholder="Write a comment"></textarea>
                </div>
                <input type="hidden" name="idPost" id="replyPostId" value="${id}">
                <small id="shortcutInfo" class="form-text text-muted mb-3">Tip: you can press Ctrl + Enter to submit your comment</small>
                <button type="submit" id="submitPostReply" class="btn btn-dark mb-3">Submit</button>
            </c:when>
        </c:choose>
        <div id="comments"></div>

        <div id="genericComment" class="comment" style="display: none;">
            <div class="card boxx mb-4" style="width: 40rem;" id="cardComment">
                <div class="card-body">
                    <div class="row m-0" id="commentContainer">
                        <div class="col-1 p-0">
                            <div class="col-12 p-0">
                                <span id="score">0</span>
                                <i class="fas fa-fire" style="color: black" id="fire-icon"></i>
                            </div>
                            <div class="col-12 p-0"><i id="upvote-comment" style="color: black" class="fas fa-arrow-circle-up"></i></div>
                            <div class="col-12 p-0"><i id="downvote-comment" style="color: black" class="fas fa-arrow-circle-down"></i></div>
                        </div>
                        <div class="col-11 p-0">
                            <div class="row m-0 justify-content-between">
                                <div class="col-12 p-0 d-flex justify-content-between">
                                    <p class="card-text" id="description"></p>
                                    <i id="collapse" style="cursor: pointer" class="fas fa-angle-up"></i>
                                </div>
                                <div class="col-12 p-0 d-flex justify-content-between align-items-end">
                                    <a id="replyLink" style="cursor: pointer" class="card-link">Reply</a>
                                    <footer class="blockquote-footer" id="footer"></footer>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="replyForm" style="display: none">
                <div class="form-group">
                    <textarea class="form-control" id="commentText" maxlength="5000" rows="3" style="width: 40rem;" name="text" placeholder="Reply to the comment"></textarea>
                </div>
                <input type="hidden" name="idPost" id="idPost" value="${id}">
                <input type="hidden" name="idParent" id="idParent" value="${comment.id}">
                <input type="hidden" name="idParent" id="idComment">
                <button type="submit" id="submitReply" class="btn btn-dark mb-3">Submit</button>
            </div>
        </div>
    </div>

    <div id="genericBanner" class="banner" style="display: none">
        <div class="card boxx mb-4" style="width: 40rem;" id="cardBanner">
            <div class="card-body">
                <div class="container" id="bannerContainer">
                    <div class="row justify-content-between">
                        <div class="col-7 p-0" style="height: 20px"><footer class="blockquote-footer" id="bannerFooter"></footer></div>
                        <div class="col-auto p-0 d-flex justify-content-between">
                            <i id="expand" style="cursor: pointer" class="fas fa-angle-down"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="../js/postDetails.js"></script>
<script src="../js/writeComment.js"></script>
<script src="../js/shortcut.js"></script>
<script src="../js/bookImage.js"></script>
<script src="../js/twitterButton.js"></script>
<script src="../js/jquery/jquery-3.3.1.min.js"></script>
</body>
</html>
