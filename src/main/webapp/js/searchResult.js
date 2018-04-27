$(document).ready(function() {
    addCategory();
    addOptions();
});

function addCategory(){
    var title = document.getElementById("authorHead");
    title.onclick = function(){
        showAll();
        unbold();
    }
}

function addOptions(){
    var authors = authorsList();
    var div = document.querySelector("#authorList");
    for (var i=0; i<authors.length; i++){
        var option = document.createElement("li"); //changed from li
        option.setAttribute("class", "authorOption");
        option.innerHTML = authors[i];
        //a different option needs to be created for each option.
        option.onclick = function(){
            hide(this.innerHTML);
            unbold();
            this.setAttribute("style", "font-weight:bold");
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

function showAll(){
    var posts = document.getElementsByClassName("card");
    for (var i=0; i<posts.length; i++){
        posts[i].style.display = "block";
    }
}

function authorsList(){
    var authorsRepeated = document.getElementsByClassName("author");
    var authors = [];
    for (var i=0; i<authorsRepeated.length; i++){
        if (!authors.includes(authorsRepeated[i].value)){
            authors.push(authorsRepeated[i].value)
        }
    }
    return authors;
}

function unbold(){
    var authors = document.getElementsByClassName("authorOption");
    for (var i=0; i<authors.length; i++){
        authors[i].setAttribute("style", "font-weight:normal");
    }
}