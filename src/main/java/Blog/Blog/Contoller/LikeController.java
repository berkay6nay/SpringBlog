package Blog.Blog.Contoller;
import Blog.Blog.Entity.Like;
import Blog.Blog.Entity.Post;
import Blog.Blog.Entity._User;
import Blog.Blog.Repository.LikeRepository;
import Blog.Blog.Repository.PostRepository;
import Blog.Blog.Repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/actions")
public class LikeController {

    private LikeRepository likeRepository;
    private PostRepository postRepository;

    public LikeController(LikeRepository likeRepository , PostRepository postRepository){
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("/like")
    public String likeAPost(@RequestParam("postId") String postId , @AuthenticationPrincipal _User user){
        Long postID = Long.parseLong(postId);
        System.out.println("" + postID);
        Like like = new Like();
        Optional<Post> post = postRepository.findById(postID);
        if(post.isPresent()){
            Post postExisting =  post.get();
            like.setPost(postExisting);
            like.setUser(user);
            likeRepository.save(like);
            return "success";
        }
        else return "error";
    }

    @PostMapping("/dislike")
    public String dislikeAPost(@RequestParam("likeId") String likeID){
        try{
            Long likeId = Long.parseLong(likeID);
            likeRepository.deleteById(likeId);
            return "success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
