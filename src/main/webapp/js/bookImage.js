function findImage(){
    var idBook = document.getElementById("idBook").value;
    httpGet(idBook);
}

function httpGet(idBook){

    $.ajax({
        url: "https://www.googleapis.com/books/v1/volumes/"+ idBook,
        success: function(result){
            displayImage(result);
        }
    });
}

function displayImage(data){
    document.getElementById("bookImage").src = data.volumeInfo.imageLinks.smallThumbnail;
}

