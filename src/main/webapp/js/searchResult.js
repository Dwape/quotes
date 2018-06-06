var filters = [["author", "", 0], ["title", "", 0]]; //maybe the active option could be saved here directly.

function loadFilters(){
    if (!noResult()){
        addCategory("author");
        addCategory("title");
        addOptions("author", "#authorList");
        addOptions("title", "#titleList");
        showResultCount();
    }
}

function addCategory(type){
    var title = document.getElementById(type + "Head");
    title.setAttribute("type", type);
    title.onclick = function(){
        filters[typeToIndex(type)][2] = 0; //change it to 0 so that filters are not applied.
        showAll();
        checkCondition();
        unbold(title.type);
        showResultCount();
    }
}

function addOptions(type, selector){
    var list = buildList(type);
    var div = document.querySelector(selector);
    for (var i=0; i<list.length; i++){
        var option = document.createElement("li");
        option.setAttribute("class", type + "Option clickable");
        option.innerHTML = list[i];
        option.setAttribute("type", list[i]);
        var span = document.createElement("span");
        span.innerHTML = " (" + 0 + ")";
        span.setAttribute("class", "number");
        option.appendChild(span);
        //a different option needs to be created for each option.
        option.onclick = function(){
            //hide options that don't fulfill the condition
            filters[typeToIndex(type)][1] = this.type; //FIX
            filters[typeToIndex(type)][2] = 1;
            showAll();
            checkCondition();
            unbold(type);
            showResultCount();
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
    var posts = document.getElementsByClassName("card boxx mb-4"); //check
    var list = document.getElementsByClassName(type);

    //new filters need to be added here
    var authorList = document.getElementsByClassName("author");
    var titleList = document.getElementsByClassName("title");


    for (var i=0; i<posts.length; i++){
        if (list[i].value !== option){
            posts[i].style.display = "none";
            authorList[i].type = "hidden"; //check
            titleList[i].type = "hidden"; //check
        }
    }
}

function showAll(){
    var posts = document.getElementsByClassName("card boxx mb-4");
    for (var i=0; i < filters.length; i++){
        var list = document.getElementsByClassName(filters[i][0]);
        for (var j=0; j < list.length; j++){
            list[j].type = "shown";
        }
    }
    for (i=0; i<posts.length; i++){
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
    var posts = document.getElementsByClassName("card boxx mb-4");
    if (posts.length < 1){
        var alert = document.getElementById("noResult");
        alert.style.display = "block";
    }
    return posts.length < 1;
}

function showResultCount(){
    for (var i=0; i < filters.length; i++){
        var type = filters[i][0];
        var list = document.getElementsByClassName(type); //check if the post is hidden to add it to the list?
        var options = document.getElementsByClassName(type + "Option clickable");
        for (var j=0; j < options.length; j++){
            var number = 0;
            for (var k=0; k < list.length; k++){
                if (list[k].value === options[j].type && list[k].type !== "hidden"){
                    number++;
                }
            }
            var numberSpan = options[j].getElementsByClassName("number");
            numberSpan[0].innerHTML = " (" + number + ")";
        }
    }
}

function clearFilters(){
    showAll();
    for (var i=0; i < filters.length; i++){
        unbold(filters[i][0]);
        filters[i][2] = 0;
    }
    showResultCount();
}