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