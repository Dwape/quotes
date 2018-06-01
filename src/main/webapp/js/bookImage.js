function findImage(idBook){

    document.getElementById("imageLink").href = "https://books.google.com/ebooks?id=" + idBook;

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

