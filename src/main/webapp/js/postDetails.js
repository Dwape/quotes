var replyIdOpen;

function getCommentArray(){
    //var comments = document.getElementById("commentsJson");
    //var commentsString = comments.innerHTML; //innerText?
    //var commentsJson = JSON.parse(commentsString);
    var idPost = document.getElementById("idPost").value;
    //$.get("/postJson", postId, displayIndependentComments(data));
    $.ajax({
        url: "/comments?id=" + idPost,
        success: function(result){
            displayIndependentComments(result);
        }
    });
    //console.log(response.responseText);
    //console.log('<%= Session["UserName"] %>');
    //displayIndependentComments(commentsJson)
}

function displayIndependentComments(json){
    for(var i=0; i < json.length; i++){
        if(!json[i].hasParent){
            var comment = createComment(json[i]); //check if the last parameter is necessary
            displayComments(json[i], comment);
            document.getElementById("comments").appendChild(comment);
        }
    }
}

function displayComments(json, parent){
    if (json.commentArray.length === 0) return;
    for (var i=0; i < json.commentArray.length; i++){
        var comment = createComment(json.commentArray[i]);
        parent.appendChild(comment);
        displayComments(json.commentArray[i], comment);
    }
}

function createComment(comment){
    var commentStructure = document.getElementById("genericComment").cloneNode(true);
    commentStructure.setAttribute("id", "actualComment");
    var level = 0;
    if (comment.hasParent) level = 1;
    commentStructure.setAttribute("style", "display: block; margin-left: " + level*50 + "px;");
    commentStructure.querySelector("#description").innerText = comment.description;
    var date = new Date(comment.datePosted); //check how to correct date format.
    commentStructure.querySelector("#footer").innerText = "posted by " + comment.username + " on " + date.toLocaleString(); //date need to be parsed.
    commentStructure.querySelector("#idParent").value = comment.id;
    commentStructure.querySelector("#replyForm").setAttribute("id", "form" + comment.id);
    if (document.getElementById("user").innerText !== "null") {
        commentStructure.querySelector("#replyLink").setAttribute("onclick", "showReplyWindow(" + comment.id + ");"); //check if we can set onclick with js
    } else {
        commentStructure.querySelector("#replyLink").setAttribute("style", "display: none");
    }
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