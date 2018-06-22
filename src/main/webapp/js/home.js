function getPopularPosts(){
    $.ajax({
        url: "/popularPost",
        success: function(result){
            var json = JSON.parse(result);
            displayPosts(json);
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
    // postStructure.text = post.quote;
    //postStructure.setAttribute("class", "list-group-item");
    postStructure.href = postStructure.href + post.id;
    postStructure.setAttribute("id", "post" + post.id);
    postStructure.querySelector("#quote").innerText = post.quote;
    postStructure.querySelector("#score").innerText = post.score;
    //postStructure.querySelector("#info").innerHTML = "from <a href=\"https://books.google.com/ebooks?id="+ post.idBook +"\" class=\"card-link\">" + post.bookTitle + "</a> by<a href=\"https://en.wikipedia.org/wiki/"+ post.bookAuthor + "\" class=\"card-link ml-1\">" + post.bookAuthor+ "</a>";
    //var date = new Date(post.datePosted);
    //postStructure.querySelector("#footer").innerText = "posted by " + post.postedBy + " on " + date.toLocaleString();

    postStructure.querySelector("#bookTitle").innerText = post.bookTitle;
    //postStructure.querySelector("#bookTitle").setAttribute("class", "title");
    postStructure.querySelector("#bookAuthor").innerText = post.bookAuthor;
    //.querySelector("#bookAuthor").setAttribute("class", "author");*/
    postStructure.setAttribute("style", "display: block");

    return postStructure;
}