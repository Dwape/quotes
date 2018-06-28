function loadView(){
    getPost();
}

//returns the post
function getPost(){
    var urlString = window.location.href;
    var url = new URL(urlString);
    var idPost = url.searchParams.get("id");
    $.ajax({
        url: "/getPost?id=" + idPost,
        success: function(result){
            displayPost(result);
            displayIndependentComments(result.comments);
        }
    });
}

var post;

function displayPost(post){
    this.post = post;
    var postView = document.getElementById("post");
    postView.querySelector("#postQuote").innerText = post.quote;
    postView.querySelector("#postInfo").innerHTML = "from <a href=\"https://books.google.com/ebooks?id="+ post.idBook +"\" class=\"card-link\">" + post.bookTitle + "</a> by<a href=\"https://en.wikipedia.org/wiki/"+ post.bookAuthor + "\" class=\"card-link ml-1\">" + post.bookAuthor+ "</a>";
    //document.getElementsByClassName("twitter-share-button")[0].setAttribute("data-text", post.quote);
    postView.querySelector("#postDescription").innerHTML = processMentions(post.description);
    var date = new Date(post.datePosted);
    postView.querySelector("#postFooter").innerText = "posted by " + post.postedBy + " on " + date.toLocaleString();
    postView.querySelector("#score-post").innerText = post.score;
    var upvote = document.getElementById("upvote-post");
    var downvote = document.getElementById("downvote-post");

    /*page = post.page;
    publisher = post.publisher;
    datePublished = post.datePublished;*/

    findImage(post.idBook); //display the book's image.

    if (document.getElementById("user").innerText !== "null"){
        upvote.onclick = function() {
            votePost(true);
        };
        downvote.onclick = function() {
            votePost(false);
        };
        postVoted(post);
        setupPostReply();
    } else {
        upvote.disabled = true;
        downvote.disable = true;
    }

    //disable copy icon
    if(noExtraInfo()){
        var icon = document.getElementById('copy');
        icon.setAttribute('data-content','Copy to clipboard unavailable');
        icon.children[0].setAttribute('hidden','');
        icon.children[1].removeAttribute('hidden');
    }
}

function postVoted(post){
    var upvote = document.getElementById("upvote-post");
    var downvote = document.getElementById("downvote-post");
    //var vote = document.getElementById("vote");
    if (post.loggedUserVote !== null){
        if (post.loggedUserVote === true){
            upvote.setAttribute("style", "color: blue");
        } else {
            downvote.setAttribute("style", "color: red");
        }
    }
}

function setupPostReply(){
    var replyButton = document.getElementById("submitPostReply");
    replyButton.onclick = function(){
        var postId = document.getElementById("replyPostId");
        var replyText = document.getElementById("replyText");
        writeReply(undefined, postId.value, replyText.value);
        replyText.value = "";
    }
}

function displayIndependentComments(json){
    for(var i=0; i < json.length; i++){
        if(!json[i].hasParent){
            var comment = createComment(json[i]);
            displayComments(json[i], comment);
            document.getElementById("comments").appendChild(comment);
        }
    }
}

function displayComments(json, parent){
    if (json.commentArray.length === 0) return;
    for (var i=0; i < json.commentArray.length; i++){
        var comment = createComment(json.commentArray[i], json.id);
        parent.appendChild(comment);
        displayComments(json.commentArray[i], comment);
    }
}

function createComment(comment, idParent){
    var commentStructure = document.getElementById("genericComment").cloneNode(true);
    commentStructure.setAttribute("id", "comment" + comment.id); //check if it is ok
    var level = 0;
    if (comment.hasParent) level = 1;
    commentStructure.setAttribute("style", "display: block; margin-left: " + level*50 + "px;");
    commentStructure.querySelector("#description").innerHTML = processMentions(comment.description); //showing mentions as links
    var date = new Date(comment.datePosted); //check how to correct date format.
    commentStructure.querySelector("#footer").innerText = "posted by " + comment.username + " on " + date.toLocaleString(); //date need to be parsed.
    commentStructure.querySelector("#idParent").value = idParent; //is this necessary
    commentStructure.querySelector("#idComment").value = comment.id; //is this necessary

    var username = document.getElementById("user").innerText;

    commentStructure.querySelector("#replyForm").setAttribute("id", "form" + comment.id);

    var upvote = commentStructure.querySelector("#upvote-comment");
    upvote.setAttribute("id", "upvote" + comment.id);

    var downvote = commentStructure.querySelector("#downvote-comment");
    downvote.setAttribute("id", "downvote" + comment.id);

    if (username !== "null") {
        //upvote = document.getElementById("upvote" + comment.id);
        upvote.onclick = function() {
            var upvote = document.getElementById("upvote" + comment.id); //this
            var downvote = document.getElementById("downvote" + comment.id);
            if (!upvote.disabled){ //this
                var score = document.getElementById("score" + comment.id);
                upvote.disabled = true;
                downvote.disabled = true;
                paintVote(true, upvote, downvote, score);
                saveVote(true, -1, comment.id);
            }
        };

        //downvote = document.getElementById("downvote" + comment.id);
        downvote.onclick = function() {
            var upvote = document.getElementById("upvote" + comment.id);
            var downvote = document.getElementById("downvote" + comment.id); //this
            if (!downvote.disabled){ //this
                var score = document.getElementById("score" + comment.id);
                upvote.disabled = true;
                downvote.disabled = true;
                paintVote(false, upvote, downvote, score);
                saveVote(false, -1, comment.id);
            }
        };

        var replyLink = commentStructure.querySelector("#replyLink");
        replyLink.onclick = function(){
            showReplyWindow(comment.id);
            var replyText = commentStructure.querySelector("#commentText");
            replyText.focus();
        };
        var replyButton = commentStructure.querySelector("#submitReply");
        replyButton.onclick = function(){
            var replyText = commentStructure.querySelector("#commentText");
            var idPost = commentStructure.querySelector("#idPost");
            if (replyText.value !== ""){
                writeReply(comment.id, idPost.value, replyText.value);
                showReplyWindow(replyIdOpen); //hides reply text-box
                replyText.value = "";
            }
        };
        if (comment.loggedUserVote !== null){
            if (comment.loggedUserVote === true){
                upvote.setAttribute("style", "color: blue");
            } else {
                downvote.setAttribute("style", "color: red");
            }
        }
    } else {
        //if user is not logged in
        commentStructure.querySelector("#replyLink").setAttribute("style", "display: none");
        upvote.disabled = true;
        downvote.disabled = true;
    }
    //add hideComment to collapse button.
    var collapse = commentStructure.querySelector("#collapse");
    collapse.onclick = function(){
        collapseComment(comment.id);
        //this.setAttribute("class", "fas fa-plus");
    };
    var score = commentStructure.querySelector("#score");
    score.setAttribute("id", "score" + comment.id);
    score.innerText = comment.score;
    return commentStructure;
}

//Changes the color of a comment to show it is the latest one.
//Fades to the original color after the specified time in millis.
function changeColor(comment, commentHighlight){
    $(commentHighlight).fadeTo(5000, 0).queue(function(){
        comment.setAttribute("style", "margin-left: " + comment.style.marginLeft);
        $(commentHighlight).remove() //should delete it
    });
}

function collapseComment(id){
    var comment = document.getElementById("comment" + id);
    var parent = comment.parentElement;
    var commentBanner = document.getElementById("genericBanner").cloneNode(true);
    var bannerText = comment.querySelector("#footer").innerText;
    commentBanner.setAttribute("id", "banner" + id);
    commentBanner.querySelector("#bannerFooter").innerText = "Comment " + bannerText;
    parent.insertBefore(commentBanner, comment);
    if (parent.id === "comments"){
        commentBanner.setAttribute("style", "display: block;");
    } else {
        commentBanner.setAttribute("style", "display: block; margin-left: " + 50 + "px;");
    }
    var expand = commentBanner.querySelector("#expand");
    expand.onclick = function(){
        expandComment(id);
    };
    $(comment).hide();
}

function expandComment(id){
    var comment = document.getElementById("comment" + id);
    $(comment).show();
    var banner = document.getElementById("banner" + id);
    $(banner).remove();
}

function votePost(isUpVote){
    var upvote = document.getElementById("upvote-post");
    var downvote = document.getElementById("downvote-post");
    if (!upvote.disabled && !downvote.disabled){
        //the logic should be extended for removing color.
        var postId = document.getElementById("idPost").value;
        //add vote painting method passing the elements as parameters, so that it can be reused
        upvote = document.getElementById("upvote-post");
        downvote = document.getElementById("downvote-post");
        upvote.disabled = true;
        downvote.disabled = true;
        var score = document.getElementById("score-post");
        paintVote(isUpVote, upvote, downvote, score); //check if this works
        saveVote(isUpVote, postId, -1); //no comment
    }
}

//could be changed to add and remove classes.
//also increases the score
function paintVote(isUpVote, upvote, downvote, score){
    if (isUpVote) { //user is upvoting
        if (upvote.style.color === "blue"){
            upvote.setAttribute("style", "color: black");
            score.innerText = Number(score.innerText)-1;
        } else {
            upvote.setAttribute("style", "color: blue");
            if (downvote.style.color === "red") score.innerText = Number(score.innerText)+2;
            else score.innerText = Number(score.innerText)+1;
        }
        downvote.setAttribute("style", "color: black");
    } else { //user is downvoting
        if (downvote.style.color === "red"){
            downvote.setAttribute("style", "color: black");
            score.innerText = Number(score.innerText)+1;
        } else {
            if (upvote.style.color === "blue") score.innerText = Number(score.innerText)-2;
            else score.innerText = Number(score.innerText)-1;
            downvote.setAttribute("style", "color: red");
        }
        upvote.setAttribute("style", "color: black");
    }
}

function saveVote(isPositive, postID, commentID){
    $.ajax({
        method: "POST",
        url: "/postDetailsVote",
        data: { isPositive: isPositive, idPost: postID, idComment: commentID },
        success: function(){
            if (commentID !== -1){
                document.getElementById("upvote" + commentID).disabled = false;
                document.getElementById("downvote" + commentID).disabled = false;
            } else {
                document.getElementById("upvote-post").disabled = false;
                document.getElementById("downvote-post").disabled = false;
            }
        }
    });
}

function copyToClipboard() {

    /* Get the text field */
    var text = this.post.bookAuthor + ", " + this.post.bookTitle + " (" + this.post.placePublished + ": " + this.post.publisher + ", " + this.post.datePublished + "), " + this.post.page +".";

    if (window.clipboardData && window.clipboardData.setData) {
        // IE specific code path to prevent textarea being shown while dialog is visible.
        return clipboardData.setData("Text", text);

    } else if (document.queryCommandSupported && document.queryCommandSupported("copy")) {
        var textarea = document.createElement("textarea");
        textarea.textContent = text;
        textarea.style.position = "fixed";  // Prevent scrolling to bottom of page in MS Edge.
        document.body.appendChild(textarea);
        textarea.select();
        try {
            return document.execCommand("copy");  // Security exception may be thrown by some browsers.
        } catch (ex) {
            console.warn("Copy to clipboard failed.", ex);
            return false;
        } finally {
            document.body.removeChild(textarea);
        }
    }
}

/*$(document).ready(function(){
    $('[data-toggle="popover"]').popover();
});*/

$('[data-toggle="popover"]').popover({'trigger':'hover'});

function noExtraInfo() {
    return this.post.datePublished === 'unknown' || this.post.publisher === 'unknown' || this.post.page === "" || this.post.placePublished === "";
}
