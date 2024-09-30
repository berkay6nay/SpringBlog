package Blog.Blog.Contoller;
import Blog.Blog.Entity.Post;
import Blog.Blog.Entity._User;
import Blog.Blog.Repository.PostRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {

    private PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/createPost")
    public String createPost(Model model){
        Post post = new Post();
        model.addAttribute("post" , post);
        return "createPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute("post") Post post , @AuthenticationPrincipal _User user , Model model){
        if(post.getBody().length() < 100){
            model.addAttribute("errorMessage" , "Post should be longer than 100 characters");
            return "createPost" ;
        }
        else if(post.getTitle().length() < 10){
            model.addAttribute("errorMessage"  , "Title should be longer than at least 10 characters");
            return "createPost" ;
        }
        post.setDate(new Date());
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("postDetail/{id}")
    public String postDetail(Model model , @PathVariable("id") Long id ){
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            Post postToParse = post.get();
            model.addAttribute("post" , postToParse);
            return "postDetail";
        }
        else{
            return "error";
        }
    }
}
