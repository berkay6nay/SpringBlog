package Blog.Blog;
import Blog.Blog.Entity.Post;
import Blog.Blog.Entity._User;
import Blog.Blog.Repository.PostRepository;
import Blog.Blog.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostDataLoader implements CommandLineRunner {

    private PostRepository postRepository;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public PostDataLoader(PostRepository postRepository , PasswordEncoder passwordEncoder , UserRepository userRepository){
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        _User user = new _User("username" , passwordEncoder.encode("password") , "example@gmail.com");
        _User user2 = new _User("username2", passwordEncoder.encode("password") , "example2@gmail.com");

        Post post1 = new Post(null , "First Post Of This App Am I" , "Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post.Attempt at creating a post." , new Date() , user2);
        Post post2 = new Post(null , "Second Post Of This App Am I" , "Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post. Attempt at creating a post.Attempt at creating a post." , new Date() , user);

        userRepository.save(user);
        userRepository.save(user2);

        postRepository.save(post1);
        postRepository.save(post2);

    }
}
