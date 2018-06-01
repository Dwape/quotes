$(document).keydown(function(evt){
    if (evt.enter && (evt.ctrlKey)){ //ctrl+enter cmd (or windows key) is browser dependent
        evt.preventDefault();
        if (document.getElementById("user").innerText !== "null"){
            reply();
        }
    }
});

function reply(){
    var replyText = document.activeElement;
    if (replyText !== undefined && (replyText.id === "replyText" || replyText.id === "commentText")) {
        var parent = document.activeElement.parentElement.parentElement;
        var idComment = undefined;
        var idPost = document.getElementById("replyPostId").value;
        if (parent.className !== "container") {
            idComment = parent.querySelector("#idComment").value; //check if this is correct.
            showReplyWindow(replyIdOpen); //hides reply text-box
        }
        if (replyText.value !== ""){
            writeReply(idComment, idPost, replyText.value);
            replyText.value = "";
        }
    }

}