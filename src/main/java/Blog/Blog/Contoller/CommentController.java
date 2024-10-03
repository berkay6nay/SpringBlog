package Blog.Blog.Contoller;
import Blog.Blog.Entity.Comment;
import Blog.Blog.Entity.Post;
import Blog.Blog.Entity._User;
import Blog.Blog.Repository.CommentRepository;
import Blog.Blog.Repository.PostRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private  CommentRepository commentRepository;
    private PostRepository postRepository;
    public CommentController(CommentRepository commentRepository , PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("/makeComment")
    public String makeAComment(@RequestParam("postId") String postId , @RequestParam("commentBody") String commentBody ,  @AuthenticationPrincipal _User user){
        Long postID = Long.parseLong(postId);
        Optional<Post> post = postRepository.findById(postID);
        if(post.isPresent()){
            Comment comment = new Comment();
            Post postToParse = post.get();
            comment.setPost(postToParse);
            comment.setUser(user);
            if(commentBody.length() > 1){
                comment.setCommentBody(commentBody);
                commentRepository.save(comment);
                return "success";
            }
            else return "commentBodyBlank";
        }
        else{
            return "error";
        }
    }


}
