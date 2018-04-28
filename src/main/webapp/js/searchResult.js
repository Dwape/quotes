var filters = [["author", 0], ["title", 0]]; //maybe the active option could be saved here directly.

$(document).ready(function() {
    addCategory("author");
    addCategory("title");
    addOptions("author", "#authorList");
    addOptions("title", "#titleList");
});

function addCategory(type){
    var title = document.getElementById(type + "Head");
    title.setAttribute("type", type);
    title.onclick = function(){
        showAll();
        unbold(title.type);
    }
}

function addOptions(type, selector){
    var list = buildList(type);
    var div = document.querySelector(selector);
    for (var i=0; i<list.length; i++){
        var option = document.createElement("li");
        option.setAttribute("class", type + "Option");
        option.innerHTML = list[i];
        //a different option needs to be created for each option.
        option.onclick = function(){
            //hide options that don't fulfill the condition
            hide(this.innerHTML, type);
            unbold(type);
            this.setAttribute("style", "font-weight:bold");
        };
        div.appendChild(option);
    }
}
//we could save the filtered value so that
function hide(option, type){
    var title = document.getElementById(type + "Head"); //the title of the filter type that is being used
    title.setAttribute("active", option); //sets "active" to the selected option
    var posts = document.getElementsByClassName("card");
    var list = document.getElementsByClassName(type);
    for (var i=0; i<posts.length; i++){
        if (list[i].value !== option){
            posts[i].style.display = "none";
        } else { //we should check if it fulfills conditions of other filters.
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

function buildList(type){
    var listRepeated = document.getElementsByClassName(type);
    var list = [];
    for (var i=0; i<listRepeated.length; i++){
        if (!list.includes(listRepeated[i].value)){
            list.push(listRepeated[i].value)
        }
    }
    return list;
}

function unbold(type){
    var list = document.getElementsByClassName(type + "Option");
    for (var i=0; i<list.length; i++){
        list[i].setAttribute("style", "font-weight:normal");
    }
}