function getCommentArray(){
    var comments = document.getElementById("commentsJson");
    var commentsString = comments.innerHTML; //innerText?
    var commentsJson = JSON.parse(commentsString);
    displayIndependentComments(commentsJson)
}

function displayIndependentComments(json){
    for(var i=0; i < json.length; i++){
        if(!json[i].hasParent){
            //var comment = document.createElement("div");
            //comment.innerHTML = json[i].description;
            var comment = createComment(json[i]);
            displayComments(json[i], comment, 1);
            document.getElementById("comments").appendChild(comment);
        }
    }
}

function displayComments(json, parent, level){
    if (json.commentArray.length === 0) return;
    for (var i=0; i < json.commentArray.length; i++){
        //var comment = document.createElement("div");
        //comment.setAttribute("style", "margin-left: " + level*20 + "px"); //we just need to change the style, not set the style.
        //comment.innerHTML = json.commentArray[i].description;
        var comment = createComment(json.commentArray[i], level); //check if level can be omitted in this method call
        parent.appendChild(comment);
        displayComments(json.commentArray[i], level+1);
    }
}

function createComment(comment, level){
    var commentStructure = document.getElementById("genericComment").cloneNode(true);
    commentStructure.setAttribute("id", "actualComment");
    commentStructure.setAttribute("style", "display: block; margin-left: " + level*50 + "px;");
    commentStructure.querySelector("#description").innerText = comment.description;
    commentStructure.querySelector("#footer").innerText = "posted by " + comment.user.username + " on " + comment.datePosted; //date need to be parsed.
    return commentStructure;
}