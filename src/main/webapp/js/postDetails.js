function getCommentArray(){
    var comments = document.getElementById("commentsJson");
    var commentsString = comments.innerHTML; //innerText?
    var commentsJson = JSON.parse(commentsString);
    displayIndependentComments(commentsJson)
}

function displayIndependentComments(json){
    for(var i=0; i < json.length; i++){
        if(!json[i].hasParent){
            var comment = createComment(json[i], 0); //check if the last parameter is necessary
            displayComments(json[i], comment);
            document.getElementById("comments").appendChild(comment);
        }
    }
}

function displayComments(json, parent){
    if (json.commentArray.length === 0) return;
    for (var i=0; i < json.commentArray.length; i++){
        var comment = createComment(json.commentArray[i], 1); //check if level can be omitted in this method call
        parent.appendChild(comment);
        displayComments(json.commentArray[i], comment);
    }
}

function createComment(comment, level){
    var commentStructure = document.getElementById("genericComment").cloneNode(true);
    commentStructure.setAttribute("id", "actualComment");
    commentStructure.setAttribute("style", "display: block; margin-left: " + level*50 + "px;");
    commentStructure.querySelector("#description").innerText = comment.description;
    var date = new Date(comment.datePosted); //check how to correct date format.
    commentStructure.querySelector("#footer").innerText = "posted by " + comment.user.username + " on " + date.toLocaleString(); //date need to be parsed.
    commentStructure.querySelector("#idParent").value = comment.id;
    commentStructure.querySelector("#replyForm").setAttribute("id", "form" + comment.id);
    commentStructure.querySelector("#replyLink").setAttribute("onclick", "showReplyWindow(" + comment.id + ");");
    return commentStructure;
}

function showReplyWindow(id){
    var form = document.getElementById("form" + id);
    form.setAttribute("style", "display: block;");
}