$("#likeButton").click(
    function(){

        var postId = $(this).attr("data-postId");

        console.log("postId" + postId);

        var formData = {
            postId : postId,
        };

         var token = $("meta[name='_csrf']").attr("content");
         var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax(
            {
                type : "POST",
                url : "/actions/like",
                data : formData,
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success : function(state){
                    if(state == "success"){
                        window.location.replace("/post/postDetail/" + postId);
                    }
                    else{
                        $(".likeSection>p.mesaj").html("Hata");
                    }
                }
            }
        );
    }
)

$("#dislikeButton").click(
    function(){

        var likeId = $(this).attr("data-likeId");
        var postId = $(this).attr("data-postId");

        console.log("likeId" + likeId);

        var formData = {
            likeId : likeId
        };

         var token = $("meta[name='_csrf']").attr("content");
         var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax(
            {
                type : "POST",
                url : "/actions/dislike",
                data : formData,
                beforeSend: function(xhr){
                    xhr.setRequestHeader(header, token);
                },
                success : function(state){
                    if(state == "success"){
                        window.location.replace("/post/postDetail/" + postId);
                    }
                    else{
                        $(".likeSection>p.mesaj").html("Hata");
                    }
                }
            }
        );
    }
)

$(".makeAComment").click(
    function(){
        console.log("Deneme");
        var commentBody = $(".commentBody").val();
        var postId = $(this).attr("data-postId");
        console.log("" + postId);
        console.log("" + commentBody);
        var formData = {
            postId : postId,
            commentBody : commentBody
        }
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax(
                {
                    type : "POST",
                    url : "/comment/makeComment",
                    data : formData,
                    beforeSend: function(xhr){
                        xhr.setRequestHeader(header, token);
                    },
                    success : function(state){
                        if(state == "success"){
                            window.location.replace("/post/postDetail/" + postId);
                        }
                        else{
                            console.log("An error occured");
                        }
                    }
                }
            );
    }
)

$("#deleteCommentButton").click(
    function(){
        console.log("Deneme");
        var commentId = $(this).attr("data-commentId");
        var postId = $(this).attr("data-postId");

        console.log("" + commentId);

        var formData = {
            commentId : commentId
        }
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

    $.ajax(
                {
                    type : "POST",
                    url : "/comment/deleteComment",
                    data : formData,
                    beforeSend: function(xhr){
                        xhr.setRequestHeader(header, token);
                    },
                    success : function(state){
                        if(state == "success"){
                            window.location.replace("/post/postDetail/" + postId);
                        }
                        else{
                            console.log("An error occured");
                        }
                    }
                }
            );
    }
)


