package Blog.Blog.Contoller;
import Blog.Blog.Entity.Like;
import Blog.Blog.Entity.Post;
import Blog.Blog.Entity._User;
import Blog.Blog.Repository.LikeRepository;
import Blog.Blog.Repository.PostRepository;
import Blog.Blog.Repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeController {

    private LikeRepository likeRepository;
    private PostRepository postRepository;

    public LikeController(LikeRepository likeRepository , PostRepository postRepository , UserRepository userRepository){
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public String likeAPost(@RequestParam("postId") String postId , @AuthenticationPrincipal _User user){
        Long userID = user.getId();
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

}
