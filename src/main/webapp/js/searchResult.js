$(document).ready(function() {
    addOptions();
});

function addOptions(){
    var authors = document.getElementsByClassName("author");
    var div = document.querySelector("#authorList");
    for (var i=0; i<authors.length; i++){
        var option = document.createElement("li");
        option.innerHTML = authors[i].value;
        //a different option needs to be created for each option.
        option.onclick = function(){
            hide(this.innerHTML);
            //un-mark every active option
            //mark the active option
        };
        div.appendChild(option);
    }
}

function hide(author){
    //console.log("it is kinda working");
    var posts = document.getElementsByClassName("card");
    var authors = document.getElementsByClassName("author");
    for (var i=0; i<posts.length; i++){
        if (authors[i].value !== author){
            posts[i].style.display = "none";
        } else {
            posts[i].style.display = "block";
        }
    }
}

function authorList(){
    var authorsRepeated = document.getElementsByClassName("author");
    var authors = [];
    for (var i=0; i<authorsRepeated.length; i++){
        if (!authors.includes(authors[i].value)){
            authors.push(authors[i].value)
        }
    }
}