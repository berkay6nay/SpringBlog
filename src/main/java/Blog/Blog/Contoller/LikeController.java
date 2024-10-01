package Blog.Blog.Contoller;
import Blog.Blog.Entity.Like;
import Blog.Blog.Entity.Post;
import Blog.Blog.Entity._User;
import Blog.Blog.Repository.LikeRepository;
import Blog.Blog.Repository.PostRepository;
import Blog.Blog.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/like")

public class LikeController {

    private LikeRepository likeRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public LikeController(LikeRepository likeRepository , PostRepository postRepository , UserRepository userRepository){
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;

    }

    @PostMapping
    public String likeAPost(@RequestParam("userId") String userId , @RequestParam("postId") String postId){
        Long userID = Long.getLong(userId);
        Long postID = Long.getLong(postId);
        Like like = new Like();
        Optional<Post> post = postRepository.findById(postID);
        Optional<_User> user = userRepository.findById(userID);
        if(post.isPresent() && user.isPresent()){
            _User userExisting = user.get();
            Post postExisting =  post.get();
            like.setPost(postExisting);
            like.setUser(userExisting);
            likeRepository.save(like);
            return "success";
        }
        else return "error";
    }

}
