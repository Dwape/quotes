//check if non used json can be removed after another search is done.

/*$(function () {
    var value = $('#book').val();
    if ( value.length > 3 && value != "Search for books" ){
        search();
    }
});*/

/*
$('#book').each(function() {
    var elem = $(this);
    var oldTime = new Date().getTime();

    // Save current value of element
    elem.data('oldVal', elem.val());

    // Look for changes in the value
    elem.bind("propertychange change click keyup input paste", function(event){
        // If value has changed...
        if (elem.data('oldVal') != elem.val()) {
            // Updated stored value
            elem.data('oldVal', elem.val());

            var newTime = new Date().getTime();
            // Do action
            console.log(newTime-oldTime);
            if (newTime-oldTime >= 500 && elem.val().length > 3){
                oldTime = newTime;
                search();
            }
        }
    });
});
*/



//jQuery autocomplete
/*$(function () {

    $( "#book" ).autocomplete({
        source: function (request, response) {
            var searchTerm = document.getElementById("book").value;
            if (searchTerm.length >= 3){
                return httpGet('https://www.googleapis.com/books/v1/volumes?q=' + searchTerm + '&projection=lite&orderBy=relevance&langRestrict=en&maxResults=5');
            }
        }
    });
});*/
var result = [];
autocomplete(document.getElementById("book"));

function autocomplete(inp) {
    /*the autocomplete function takes two arguments,
    the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    /*execute a function when someone writes in the text field:*/
    inp.addEventListener("input", function (e) {
        search();
        currentFocus = -1;
    });
    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function(e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x) x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
            increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x,currentFocus);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
            decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x,currentFocus);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
                /*and simulate a click on the "active" item:*/
                if (x) x[currentFocus].click();
            }
        }
    });

    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}

function addActive(x,currentFocus) {
    /*a function to classify an item as "active":*/
    if (!x) return false;
    /*start by removing the "active" class on all items:*/
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    /*add class "autocomplete-active":*/
    x[currentFocus].classList.add("autocomplete-active");
}
function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
    for (var i = 0; i < x.length; i++) {
        x[i].classList.remove("autocomplete-active");
    }
}
function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
        if (elmnt != x[i] && elmnt != document.getElementById("book")) {
            x[i].parentNode.removeChild(x[i]);
        }
    }
}

function search(){
    var searchTerm = document.getElementById("book").value;
    if (searchTerm.length >= 3){
        httpGet('https://www.googleapis.com/books/v1/volumes?q=' + searchTerm + '&projection=lite&orderBy=relevance&langRestrict=en&maxResults=5');
    }
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
                    drop(data);

                    var inp = document.getElementById("book");
                    var a, b, i, val = inp.value;
                    /*close any already open lists of autocompleted values*/
                    closeAllLists();
                    /*create a DIV element that will contain the items (values):*/
                    a = document.createElement("DIV");
                    a.setAttribute("id", this.id + "autocomplete-list");
                    a.setAttribute("class", "autocomplete-items");
                    /*append the DIV element as a child of the autocomplete container:*/
                    inp.parentNode.appendChild(a);
                    /*for each item in the array...*/
                    for (i = 0; i < result.length; i++) {
                        /*check if the item starts with the same letters as the text field value:*/

                        /*create a DIV element for each matching element:*/
                        b = document.createElement("DIV");
                        /*make the matching letters bold:*/
                        b.innerHTML = "<strong>" + result[i].substr(0, val.length) + "</strong>";
                        b.innerHTML += result[i].substr(val.length);
                        /*insert a input field that will hold the current array item's value:*/
                        b.innerHTML += "<input type='hidden' value='" + result[i] + "'>";
                        /*execute a function when someone clicks on the item value (DIV element):*/
                        b.addEventListener("click", function(e) {
                            /*insert the value for the autocomplete text field:*/
                            inp.value = this.getElementsByTagName("input")[0].value;
                            /*close the list of autocompleted values,
                            (or any other open lists of autocompleted values:*/
                            closeAllLists();
                        });
                        a.appendChild(b);

                    }

                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}

function drop(data) {
    //each book is represented as an array, with all books being stored in an array.
    var books = [];

    var availableTags = [];

    var i;
    for (i = 0; i < 5 && i < data.totalItems; i++){
        var book = [];
        book[0] = data.items[i].id;
        book[1] = data.items[i].volumeInfo.title;
        //some books don't have authors, they could also just be ignored.
        var authors = data.items[i].volumeInfo.authors;
        if (authors === undefined){
            book[2] = 'Anonymous';
        } else {
            book[2] = data.items[i].volumeInfo.authors[0];
        }
        books.push(book);
        /*select.options.add( new Option(book[1] + ' by ' + book[2]));*/
        availableTags.push(book[1] + ' by ' + book[2]);
    }

    //no results are found.
    if (data.totalItems === 0) {
        /*select.options.add(new Option('No books found'));*/
        availableTags.push('No books found')
    } else {
        //default option, if book selection is not changed.
        document.getElementById("bookId").value = books[0][0];
        document.getElementById("bookTitle").value = books[0][1];
        document.getElementById("bookAuthor").value = books[0][2];
    }

    result = availableTags;
}

/*
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

    //each book is represented as an array, with all books being stored in an array.
    var books = [];

    select.onchange = function(){
        var selected = select.selectedIndex;
        document.getElementById("bookId").value = books[selected][0];
        document.getElementById("bookTitle").value = books[selected][1];
        document.getElementById("bookAuthor").value = books[selected][2];
    };

    var i;
    for (i = 0; i < 5 && i < data.totalItems; i++){
        var book = [];
        book[0] = data.items[i].id;
        book[1] = data.items[i].volumeInfo.title;
        //some books don't have authors, they could also just be ignored.
        var authors = data.items[i].volumeInfo.authors;
        if (authors === undefined){
            book[2] = 'Anonymous';
        } else {
            book[2] = data.items[i].volumeInfo.authors[0];
        }
        books.push(book);
        select.options.add( new Option(book[1] + ' by ' + book[2]));
    }
    //no results are found.
    if (data.totalItems === 0) {
        select.options.add(new Option('No books found'));
    } else {
        //default option, if book selection is not changed.
        document.getElementById("bookId").value = books[0][0];
        document.getElementById("bookTitle").value = books[0][1];
        document.getElementById("bookAuthor").value = books[0][2];
    }

    frag.appendChild(select);
    div.appendChild(frag);
}
*/
