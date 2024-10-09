package Blog.Blog.Contoller;
import Blog.Blog.Entity.Comment;
import Blog.Blog.Entity.Like;
import Blog.Blog.Entity.Post;
import Blog.Blog.Entity._User;
import Blog.Blog.Repository.CommentRepository;
import Blog.Blog.Repository.LikeRepository;
import Blog.Blog.Repository.PostRepository;
import Blog.Blog.Repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {

    private PostRepository postRepository;
    private LikeRepository likeRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public PostController(PostRepository postRepository , LikeRepository likeRepository , CommentRepository commentRepository , UserRepository userRepository){
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
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
    public String postDetail(Model model , @PathVariable("id") Long id , @AuthenticationPrincipal _User user){
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()){
            Post postToParse = post.get();
            Optional<Like> like = Optional.ofNullable(likeRepository.getLikeByUserIdAndPostId(postToParse.getId(), user.getId()));
            Integer numberOfLikes = likeRepository.getLikesByPostId(postToParse.getId()).size();
            boolean hasUserLikedThePost = false;
            Long authorOfThePost = postToParse.getUser().getId();
            Long loggedInUser = user.getId();
            List<Comment> comments = commentRepository.getCommentsByPostId(postToParse.getId());
            Integer numberOfComments = comments.size();

            if(like.isPresent()){
                hasUserLikedThePost = true;
                model.addAttribute("like" , like.get());
            }

            model.addAttribute("post" , postToParse);
            model.addAttribute("hasUserLikedThePost" , hasUserLikedThePost);
            model.addAttribute("numberOfLikes" , numberOfLikes);
            model.addAttribute("authorOfThePost" , authorOfThePost);
            model.addAttribute("loggedInUser" , loggedInUser);
            model.addAttribute("comments" , comments);
            model.addAttribute("numberOfComments" , numberOfComments);

            return "postDetail";
        }
        else{
            return "error";
        }
    }

    @GetMapping("posts/{id}")
    public String posts(@PathVariable("id") Long userId , Model model , @AuthenticationPrincipal _User user){
        List<Post> posts = postRepository.getPostsByUserId(userId);
        Boolean allowPostDeletion = user.getId().equals(userId);

        Optional<_User> ownerOfPage = userRepository.findById(userId);
        if(ownerOfPage.isPresent()){
            String owner = ownerOfPage.get().getUsername();
            model.addAttribute("owner" , owner);
        }

        model.addAttribute("posts" , posts);
        model.addAttribute("allowPostDeletion" , allowPostDeletion);

        return "posts";
    }

}
