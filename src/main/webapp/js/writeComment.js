var replyIdOpen;

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

//replies will need to be read so that mentions can be supported.
function writeReply(idParent, idPost, replyText){
    //replyText = processMentions(replyText); //this should be done to save the HTML to the database
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

function processMentions(replyText){
    //iterate through the comment text to see if there are any mentions
    var newText = "";
    var username = ""; //reading a username
    for (var i=0; i < replyText.length; i++){
        var char = replyText.charAt(i);
        if (username.length > 0){
            if (char === ' ' || char === '.' || char === ','){ //should include any character that is not allowed in usernames
                newText += username.substr(1, username.length-1) + "\"> " + username + "</a>"; //remove the @
                newText += char;
                username = "";
            } else {
                username += char;
            }
        } else {
            if (char === '@'){
                username += '@';
                newText += "<a href=\"/userDetails?username="; //should add context path here?
            } else newText += char;
        }
    }
    if (username.length > 0) {
        newText += username.substr(1, username.length-1) + "\"> " + username + " </a>";
    }
    return newText;
}