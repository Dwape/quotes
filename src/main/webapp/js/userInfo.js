function findUserPosts(){
    var username = document.getElementById("username").value;
    $.ajax({
        url: "/userPosts?username=" + username,
        success: function(result){
            var json = JSON.parse(result);
            displayUserPosts(json);
            loadFilters(); //shows the filters
        }
    });
}

function displayUserPosts(json){
    var parent = document.getElementById("postColumn");
    for (var i=0; i < json.length; i++){
        var post = createPost(json[i]);
        parent.appendChild(post);
    }
}