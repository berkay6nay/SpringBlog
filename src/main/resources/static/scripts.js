$("#likeButton").click(
    function(){
        console.log("deneme");
        var postId = $(this).attr("data-postId");
        var userId = $(this).attr("data-userId");

        console.log("postId" + postId);
        console.log("userId" + userId);

        var formData = {
            postId : postId,
            userId : userId
        };

         var token = $("meta[name='_csrf']").attr("content");
         var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax(
            {
                type : "POST",
                url : "/like",
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