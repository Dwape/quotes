<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Post</title>
    <jsp:include page="bootstrapHead.jsp"></jsp:include>
    <link rel="stylesheet" href="../css/postDetails.css" type="text/css">
</head>
<body onload="getCommentArray();displayVote();">

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
        <div class="card mb-4" style="width: 40rem;">
            <h5 class="card-header">"${quote}"</h5>
            <div class="card-body pb-2">
                <h6 class="card-subtitle mb-2 text-muted">from <a href="#" class="card-link">${bookTitle}</a> by<a href="#" class="card-link ml-1">${bookAuthor}</a></h6>
                <p class="card-text">${text}</p>

                <footer class="blockquote-footer">posted by ${postedBy} on ${datePosted.toLocaleString()}</footer>
                <i id="upvote-post" style="color: black" class="fas fa-arrow-circle-up" onClick="votePost(true)"></i>
                <i id="downvote-post" style="color: black" class="fas fa-arrow-circle-down" onClick="votePost(false)"></i>
                <span id="score-post">${score}</span>
                <input type="hidden" name="idPost" value="${id}">
            </div>
        </div>
        <c:choose>
            <c:when test="<%=request.getRemoteUser() != null%>">
                <div class="form-group">
                    <textarea class="form-control" id="replyText" maxlength="5000" rows="3" style="width: 40rem;" name="text" placeholder="Write a comment"></textarea>
                </div>
                <input type="hidden" name="idPost" id="replyPostId" value="${id}">
                <button type="submit" id="submitPostReply" class="btn btn-danger">Submit</button>
            </c:when>
        </c:choose>
        <div id="comments"></div>

        <div id="genericComment" class="comment" style="display: none;">
            <div class="card boxx mb-4" style="width: 40rem;" id="cardComment">
                <div class="card-body">
                    <p class="card-text" id="description"></p>
                    <div class="container" id="commentContainer">
                        <div class="row justify-content-between">
                            <div class="col-6 p-0" style="height: 20px"><footer class="blockquote-footer" id="footer"></footer></div>
                            <div class="col-2 p-0">
                                <a id="replyLink" style="cursor: pointer" class="card-link">Reply</a>
                                <i id="collapse" style="cursor: pointer" class="fas fa-angle-up"></i>
                            </div>
                        </div>
                        <i id="upvote-comment" style="color: black" class="fas fa-arrow-circle-up"></i>
                        <i id="downvote-comment" style="color: black" class="fas fa-arrow-circle-down"></i>
                        <span id="score">0</span>
                    </div>
                </div>
            </div>
            <div id="replyForm" style="display: none">
                <div class="form-group">
                    <textarea class="form-control" id="commentText" maxlength="5000" rows="3" style="width: 40rem;" name="text" placeholder="Reply to the comment"></textarea>
                </div>
                <input type="hidden" name="idPost" id="idPost" value="${id}">
                <input type="hidden" name="idParent" id="idParent" value="${comment.id}">
                <button type="submit" id="submitReply" class="btn btn-danger">Submit</button>
            </div>
        </div>
    </div>

    <div id="genericBanner" class="banner" style="display: none">
        <div class="card boxx mb-4" style="width: 40rem;" id="cardBanner">
            <div class="card-body">
                <div class="container" id="bannerContainer">
                    <div class="row justify-content-between">
                        <div class="col-7 p-0" style="height: 20px"><footer class="blockquote-footer" id="bannerFooter"></footer></div>
                        <div class="col-1 p-0">
                            <i id="expand" style="cursor: pointer" class="fas fa-angle-down"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="bootstrapBody.jsp"></jsp:include>
<script src="../js/postDetails.js"></script>
<script src="../js/jquery/jquery-3.3.1.min.js"></script>
</body>
</html>
