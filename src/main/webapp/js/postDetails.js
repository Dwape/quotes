var replyIdOpen;

function getCommentArray(){
    var idPost = document.getElementById("idPost").value;
    $.ajax({
        url: "/comments?id=" + idPost,
        success: function(result){
            displayIndependentComments(result);
        }
    });
    if (document.getElementById("user").innerText !== "null"){
        setupPostReply();
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
    commentStructure.querySelector("#description").innerText = comment.description;
    var date = new Date(comment.datePosted); //check how to correct date format.
    commentStructure.querySelector("#footer").innerText = "posted by " + comment.username + " on " + date.toLocaleString(); //date need to be parsed.
    commentStructure.querySelector("#idParent").value = idParent; //is this necessary

    commentStructure.querySelector("#upvote-comment").onclick = function () {
        var commentID = commentStructure.getAttribute("id");
        $.ajax({
            method: "POST",
            url: "/commentVote",
            data: { isPositive: true, idComment: comment.id },
            success: function(result){
                paintUpVoteComment(result, commentID);
            }
        });
    };

    commentStructure.querySelector("#downvote-comment").onclick = function () {
        var commentID = commentStructure.getAttribute("id");
        $.ajax({
            method: "POST",
            url: "/commentVote",
            data: { isPositive: false, idComment: comment.id },
            success: function(result){
                paintDownVoteComment(result, commentID);
            }
        });
    };

    commentStructure.querySelector("#replyForm").setAttribute("id", "form" + comment.id);
    if (document.getElementById("user").innerText !== "null") {
        var replyLink = commentStructure.querySelector("#replyLink");
        replyLink.onclick = function(){
            showReplyWindow(comment.id);
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
        }
    } else {
        commentStructure.querySelector("#replyLink").setAttribute("style", "display: none");
    }
    //add hideComment to collapse button.
    var collapse = commentStructure.querySelector("#collapse");
    collapse.onclick = function(){
        collapseComment(comment.id);
        //this.setAttribute("class", "fas fa-plus");
    };
    return commentStructure;
}

function showReplyWindow(id){
    if (replyIdOpen === id){
        var form = document.getElementById("form" + replyIdOpen);
        form.setAttribute("style", "display: none;");
        replyIdOpen = undefined;
        return;
    }
    if (replyIdOpen !== undefined){
        var oldForm = document.getElementById("form" + replyIdOpen);
        oldForm.setAttribute("style", "display: none;");
    }
    form = document.getElementById("form" + id);
    form.setAttribute("style", "display: block;");
    replyIdOpen = id;
}

function writeReply(idParent, idPost, replyText){
    $.ajax({
        method: "POST",
        url: "/postDetails",
        data: { replyText: replyText, idPost: idPost, idParent: idParent },
        success: function(result){
            var comment = createComment(result, idParent);
            addNewComment(comment);
        }
    });
}

//when added comments will not be sorted by votes
//this can be fixed if we get all the children of the parent node and compare their score.
//the newest node could be shown at the top and it could change color to show it is new.
function addNewComment(comment){
    var idParent = comment.querySelector("#idParent").value;
    var parent;
    var commentHighlight = comment.cloneNode(true);
    var card = commentHighlight.querySelector("#cardComment");
    comment.setAttribute("style", "margin-left: " + comment.style.marginLeft + "; position: absolute;");
    card.setAttribute("class", "card boxx mb-4 bg-dark text-white");
    if (idParent !== "undefined"){
        parent = document.getElementById("comment" + idParent);
        //weird workaround (the comment has some children which are the comment in itself)
        parent.insertBefore(commentHighlight, parent.childNodes[4]);
        parent.insertBefore(comment, parent.childNodes[4]);
    } else {
        parent = document.getElementById("comments");
        parent.insertBefore(commentHighlight, parent.firstChild);
        parent.insertBefore(comment, parent.firstChild);
    }
    changeColor(comment, commentHighlight); //experimental
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

function displayVote() {
    var idPost = document.getElementById("idPost").value;
    $.ajax({
        url: "/postDetailsVote?id=" + idPost,
        success: function(result){
            var strings = result.split(" ");
            if (strings[0] === 'false') strings[1]==='true' ? paintUpVote('false'):paintDownVote('false');
        }
    });
}

function votePost(isUpVote){
    isUpVote ? paintUpVote("false"): paintDownVote("false");//so as to not have to wait for it to be colored
    var postId = document.getElementById("idPost").value;
    $.ajax({
        method: "POST",
        url: "/postDetailsVote",
        data: { isPositive: isUpVote, idPost: postId },
        success: function(result){
            isUpVote ? paintUpVote(result): paintDownVote(result);
        }
    });
}

function paintUpVote(result){
    $("#downvote-post").addClass("remove-vote").removeClass("down-vote");
    if(result === "true"){
        $("#upvote-post").addClass("remove-vote").removeClass("up-vote");
    }else{
        $("#upvote-post").addClass("up-vote").removeClass("remove-vote");
    }
}

function paintDownVote(result){
    $("#upvote-post").addClass("remove-vote").removeClass("up-vote");
    if(result === "true"){
        $("#downvote-post").addClass("remove-vote").removeClass("down-vote");
    }else{
        $("#downvote-post").addClass("down-vote").removeClass("remove-vote");
    }
}

function paintUpVoteComment(result,commentID) {
    var comment = document.getElementById(commentID);
    comment.querySelector("#downvote-comment").classList.remove("down-vote");
    comment.querySelector("#downvote-comment").classList.add("remove-vote");
    if(result === "true"){
        comment.querySelector("#upvote-comment").classList.remove("up-vote");
        comment.querySelector("#upvote-comment").classList.add("remove-vote");
        /*$("#upvote-post").addClass("remove-vote").removeClass("up-vote");*/
    }else{
        comment.querySelector("#upvote-comment").classList.remove("remove-vote");
        comment.querySelector("#upvote-comment").classList.add("up-vote");
        /*$("#upvote-post").addClass("up-vote").removeClass("remove-vote");*/
    }
}

function paintDownVoteComment(result,commentID) {
    var comment = document.getElementById(commentID);
    comment.querySelector("#upvote-comment").classList.remove("up-vote");
    comment.querySelector("#upvote-comment").classList.add("remove-vote");
    if(result === "true"){
        comment.querySelector("#downvote-comment").classList.remove("down-vote");
        comment.querySelector("#downvote-comment").classList.add("remove-vote");
        /*$("#upvote-post").addClass("remove-vote").removeClass("up-vote");*/
    }else{
        comment.querySelector("#downvote-comment").classList.remove("remove-vote");
        comment.querySelector("#downvote-comment").classList.add("down-vote");
        /*$("#upvote-post").addClass("up-vote").removeClass("remove-vote");*/
    }
}
/*
function voteComment(isUpVote){
    var commentID = document.
}*/
