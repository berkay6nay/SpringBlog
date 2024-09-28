package Blog.Blog.Contoller;

import Blog.Blog.Entity.Post;
import Blog.Blog.Repository.PostRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public String createPost(@ModelAttribute("post") Post post){
        post.setDate(new Date());
        postRepository.save(post);
        return "redirect:/";
    }

}
