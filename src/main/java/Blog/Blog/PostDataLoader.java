package Blog.Blog;

import Blog.Blog.Entity.Post;
import Blog.Blog.Repository.PostRepository;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostDataLoader implements CommandLineRunner {

    private PostRepository postRepository;

    public PostDataLoader(PostRepository postRepository){
        this.postRepository = postRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Post post1 = new Post(null , "First Post" , "Der erste Post bin Ich" , new Date());
        Post post2 = new Post(null , "Second Post" , "Der zweite Post bin Ich" , new Date());

        postRepository.save(post1);
        postRepository.save(post2);

    }
}
