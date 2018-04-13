function search(form){
    var searchTerm = form.searchText.value;
    httpGet('https://www.googleapis.com/books/v1/volumes?q=' + searchTerm + '&projection=lite&orderBy=relevance&langRestrict=en', form);
}

function httpGet(url, form){
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
                    var author = data.items[0].volumeInfo.authors[0];
                    var title = data.items[0].volumeInfo.title;
                    var id = data.items[0].id;
                    form.searchText.value = title + ' by ' + author;
                    form.bookId.value = id;
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
}

