//check if non used json can be removed after another search is done.
function search(){
    var searchTerm = document.getElementById("book").value;
    httpGet('https://www.googleapis.com/books/v1/volumes?q=' + searchTerm + '&projection=lite&orderBy=relevance&langRestrict=en&maxResults=5');
}

function httpGet(url){
    fetch(url)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                // Examine the text in the response
                response.json().then(function(data) {
                    console.log(data);
                    dropdown(data);
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}

function dropdown(data){
    var div = document.querySelector("#bookSearchBar"),
        frag = document.createDocumentFragment(),
        select = document.createElement("select");

    //bootstrap
    select.setAttribute("class", "form-control form-control-sm");

    //removes previous options, if there are any.
    while(div.firstChild){
        div.removeChild(div.firstChild);
    }

    select.onchange = function(){
        var selected = select.selectedIndex;
        document.getElementById("bookId").value = data.items[selected].id;
    };
    //no results are found.
    if (data.totalItems === 0) {
        select.options.add(new Option('No books found'));
    } else {
        //default option, if book selection is not changed.
        document.getElementById("bookId").value = data.items[0].id;
    }

    var i;
    for (i = 0; i < 5 && i < data.totalItems; i++){
        var authors = data.items[i].volumeInfo.authors;
        var author;
        //some books don't have authors, they could also just be ignored.
        if (authors === undefined){
            author = 'Anonymous';
        } else {
            author = data.items[i].volumeInfo.authors[0];
        }
        var title = data.items[i].volumeInfo.title;
        select.options.add( new Option(title + ' by ' + author));
    }
    frag.appendChild(select);
    div.appendChild(frag);
}


