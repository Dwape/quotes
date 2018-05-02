var filters = [["author", "", 0], ["title", "", 0]]; //maybe the active option could be saved here directly.

$(document).ready(function() {
    if (!noResult()){
        addCategory("author");
        addCategory("title");
        addOptions("author", "#authorList");
        addOptions("title", "#titleList");
    }
});

function addCategory(type){
    var title = document.getElementById(type + "Head");
    title.setAttribute("type", type);
    title.onclick = function(){
        filters[typeToIndex(type)][2] = 0; //change it to 0 so that filters are not applied.
        showAll();
        checkCondition();
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
            filters[typeToIndex(type)][1] = this.innerHTML; //FIX
            filters[typeToIndex(type)][2] = 1; //FIX
            showAll();
            checkCondition();
            unbold(type);
            this.setAttribute("style", "font-weight:bold");
        };
        div.appendChild(option);
    }
}

function checkCondition(){
    for (var i=0; i<filters.length; i++){
        if (filters[i][2] === 1){
            hide(filters[i][1], filters[i][0]);
        }
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

function typeToIndex(type){
    for (var i=0; i<filters.length; i++){
        if (filters[i][0] === type) {
            return i;
        }
    }
}

function noResult(){
    var posts = document.getElementsByClassName("card");
    if (posts.length < 1){
        var alert = document.getElementById("noResult");
        alert.style.display = "block";
    }
    return posts.length < 1;
}
/*

$("#post").click(function() {
    window.location = $(this).find("a").attr("href");
    return false;
});*/
