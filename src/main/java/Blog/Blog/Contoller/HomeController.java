package Blog.Blog.Contoller;
import Blog.Blog.Entity.Post;
import Blog.Blog.Repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private PostRepository postRepository;

    public HomeController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping
    public String index(Model model){
        List<Post> posts = new ArrayList<>();
        posts = postRepository.findAll();
        model.addAttribute("posts" , posts);
        return "index";
    }
}
