function findPosts(){
    var searchTerm = document.getElementById("searchTerm").value;
    $.ajax({
        url: "/resultPosts?q=" + searchTerm,
        success: function(result){
            var json = JSON.parse(result);
            displayPosts(json);
            loadFilters(); //shows the filters
        }
    });
}

function displayPosts(json){
    var parent = document.getElementById("postColumn");
    for (var i=0; i < json.length; i++){
        var post = createPost(json[i]);
        parent.appendChild(post);
    }
}

function createPost(post){
    var postStructure = document.getElementById("genericPost").cloneNode(true);
    postStructure.setAttribute("class", "card boxx mb-4");
    postStructure.querySelector("#redirect").href = postStructure.querySelector("#redirect").href + post.id;
    postStructure.setAttribute("id", "post" + post.id);
    postStructure.querySelector("#quote").innerText = post.quote;
    postStructure.querySelector("#description").innerText = post.description;
    postStructure.querySelector("#info").innerHTML = "from <a href=\"https://books.google.com/ebooks?id="+ post.idBook +"\" class=\"card-link\">" + post.bookTitle + "</a> by<a href=\"https://en.wikipedia.org/wiki/"+ post.bookAuthor + "\" class=\"card-link ml-1\">" + post.bookAuthor+ "</a>";
    var date = new Date(post.datePosted);
    postStructure.querySelector("#footer").innerText = "posted by " + post.postedBy + " on " + date.toLocaleString();

    postStructure.querySelector("#bookTitle").value = post.bookTitle;
    postStructure.querySelector("#bookTitle").setAttribute("class", "title");
    postStructure.querySelector("#bookAuthor").value = post.bookAuthor;
    postStructure.querySelector("#bookAuthor").setAttribute("class", "author");
    postStructure.setAttribute("style", "width: 40rem; display: block");

    return postStructure;
}
/*

$('.stick-top').affix({
    offset: {top: 50}
});*/
